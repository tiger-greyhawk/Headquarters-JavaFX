package name.timoshenko.communityhelper.client.controller.dialog;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import name.timoshenko.communityhelper.common.AllyTypeConstants;
import name.timoshenko.communityhelper.common.model.WorldAdminModel;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

@Service
public class SimpleFactionListDialogService implements FactionListDialogService {

    public String ShowCreateFactionDialogPattern(){
        TextInputDialog dialog = new TextInputDialog("Faction name");
        dialog.setTitle("Create faction");
        dialog.setHeaderText("Do you want create new faction?");
        dialog.setContentText("Please enter faction name:");

        Optional<String> result = dialog.showAndWait();
        return result.orElse("");
    }

    @Override
    public String ShowAllyFactionDialog(String factionNameToAlly){
        TextInputDialog dialog = new TextInputDialog("older allies");
        dialog.setTitle("Ally");
        dialog.setHeaderText("Do you want to ally with '"+factionNameToAlly+"'?");
        dialog.setContentText("Please enter note (required):");

        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()){
            return result.orElse("");
        }
        else return "";
    }

    @Override
    public Optional<Map<String, String>> ShowAllyFactionDialog1(String factionNameToAlly){
        Dialog<Map<String, String>> dialog = new Dialog<>();

        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        //ObservableList<String> typeAllyCombobox = FXCollections.observableArrayList();
        ObservableList<String> typeAllyCombobox = FXCollections.observableArrayList();

        for (Field allyType: AllyTypeConstants.class.getFields()

             ) {
            typeAllyCombobox.add(allyType.getName().toLowerCase());
        }
        //typeAllyCombobox.add(AllyTypeConstants.ALLY);
        //typeAllyCombobox.add(AllyTypeConstants.ENEMY);
        //typeAllyCombobox.add(AllyTypeConstants.NULLY);

        TextField factionNote = new TextField();
        factionNote.setPromptText("Note");
        ComboBox<String> comboBox = new ComboBox<>();
        comboBox.setItems(typeAllyCombobox);

        grid.add(new Label("Ally note:"), 0, 0);
        grid.add(factionNote, 1, 0);
        grid.add(new Label("Type of ally:"), 0, 1);
        grid.add(comboBox, 1, 1);

        dialog.getDialogPane().setContent(grid);

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == ButtonType.OK) {
                Map<String, String> result = new TreeMap<>();
                result.put("allyNote", factionNote.getText());
                result.put("allyType", comboBox.getSelectionModel().getSelectedItem());
                return result;
            }
            return null;
        });
        return dialog.showAndWait();
    }

    @Override
    public Optional<Map<String, String>> ShowCreateFactionDialog(List<WorldAdminModel> worlds) {
        Dialog<Map<String, String>> dialog = new Dialog<>();
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        TextField factionName = new TextField();
        factionName.setPromptText("Name");
        TextField slogan = new TextField();
        slogan.setPromptText("Slogan");

        ObservableList<String> worldCombobox = FXCollections.observableArrayList();

        for (WorldAdminModel world: worlds) {
            worldCombobox.add(world.getName());
        }

        ComboBox<String> comboBox = new ComboBox<>();
        comboBox.setItems(worldCombobox);

        grid.add(new Label("Faction name:"), 0, 0);
        grid.add(factionName, 1, 0);
        grid.add(new Label("Faction slogan:"), 0, 1);
        grid.add(slogan, 1, 1);
        grid.add(new Label("World:"), 0, 2);
        grid.add(comboBox, 1, 2);


        dialog.getDialogPane().setContent(grid);

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == ButtonType.OK) {
                Map<String, String> result = new TreeMap<>();
                result.put("factionName", factionName.getText());
                result.put("factionSlogan", slogan.getText());
                Long index = worlds.get(comboBox.selectionModelProperty().get().getSelectedIndex()).getId();
                //result.put("worldId", comboBox.getSelectionModel().getSelectedItem());
                result.put("worldId", index.toString());
                return result;
            }
            return null;
        });
        return dialog.showAndWait();
    }
}
