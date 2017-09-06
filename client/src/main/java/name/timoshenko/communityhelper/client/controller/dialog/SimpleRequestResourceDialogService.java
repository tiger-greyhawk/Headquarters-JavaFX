package name.timoshenko.communityhelper.client.controller.dialog;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import name.timoshenko.communityhelper.common.model.RequestResourceModel;

import java.util.*;

public class SimpleRequestResourceDialogService implements RequestResourceDialogService {
    @Override
    public Optional<RequestResourceModel> showCreateNewRequestDialog(RequestResourceModel result) {
        Dialog<RequestResourceModel> dialog = new Dialog<>();
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        ObservableList<String> typeCombobox = FXCollections.observableArrayList();

        List<String> types = new ArrayList<>();
        types.add("Storage");
        types.add("Banquets");
        types.add("Granary");
        types.add("Armory");
        for (String type: types) {
            typeCombobox.add(type);
        }

        ComboBox<String> comboBox = new ComboBox<>();
        comboBox.setItems(typeCombobox);

        TextField resourceName = new TextField();
        resourceName.setPromptText("Name");
        TextField resourceAmount = new TextField();
        resourceAmount.setPromptText("0");



        grid.add(new Label("Resource:"), 0, 0);
        grid.add(resourceName, 1, 0);
        grid.add(new Label("Amount:"), 0, 1);
        grid.add(resourceAmount, 1, 1);
        grid.add(new Label("Type:"), 0, 2);
        grid.add(comboBox, 1, 2);


        dialog.getDialogPane().setContent(grid);

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == ButtonType.OK) {
                //RequestResourceModel result = new RequestResourceModel();
                result.nameProperty().set(resourceName.getText());
                result.amountProperty().set(resourceAmount.getText());

                //result.put("resourceName", resourceName.getText());
                //result.put("resourceAmount", resourceAmount.getText());
                //Long index = worlds.get(comboBox.selectionModelProperty().get().getSelectedIndex()).getId();
                //result.put("worldId", comboBox.getSelectionModel().getSelectedItem());
                //result.put("resourceType", comboBox.getSelectionModel().getSelectedItem());
                return result;
            }
            return null;
        });
        return dialog.showAndWait();
    }
}
