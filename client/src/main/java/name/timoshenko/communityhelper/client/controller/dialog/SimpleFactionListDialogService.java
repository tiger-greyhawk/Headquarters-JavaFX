package name.timoshenko.communityhelper.client.controller.dialog;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import org.springframework.stereotype.Service;

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
    public Optional<Map<String, String>> ShowCreateFactionDialog() {
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

        grid.add(new Label("Faction name:"), 0, 0);
        grid.add(factionName, 1, 0);
        grid.add(new Label("Faction slogan:"), 0, 1);
        grid.add(slogan, 1, 1);

        dialog.getDialogPane().setContent(grid);

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == ButtonType.OK) {
                Map<String, String> result = new TreeMap<>();
                result.put("factionName", factionName.getText());
                result.put("factionSlogan", slogan.getText());
                return result;
            }
            return null;
        });
        return dialog.showAndWait();
    }
}
