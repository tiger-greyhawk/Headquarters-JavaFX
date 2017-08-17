package name.timoshenko.communityhelper.client.controller;

import com.canoo.platform.client.ClientContext;
import com.canoo.platform.client.ControllerActionException;
import com.canoo.platform.client.Param;
import com.canoo.platform.client.javafx.FXBinder;
import com.canoo.platform.client.javafx.binding.FXWrapper;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import name.timoshenko.communityhelper.common.Constants;
import name.timoshenko.communityhelper.common.model.FactionListWindowModel;
import name.timoshenko.communityhelper.common.model.FactionModel;
import name.timoshenko.communityhelper.common.model.PlayerModel;

import java.net.URL;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

/**
 *
 */
public class FactionListView extends StagedFXMLViewBinder<FactionListWindowModel> {


    @FXML
    private TextField factionFilterPattern;
    @FXML
    private TableView<FactionModel> factionTableView;
    @FXML
    private TableView<PlayerModel> factionPlayersTableView;
    @FXML
    private TableColumn<FactionModel, String> factionTableNameColumn;
    @FXML
    private TableColumn<FactionModel, String> factionTableOwnerColumn;
    @FXML
    private TableColumn<FactionModel, String> factionTableAllyColumn;
    @FXML
    private TableColumn<PlayerModel, Long> factionPlayersTableIdColumn;
    @FXML
    private TableColumn<PlayerModel, Long> factionPlayersTableUserIdColumn;
    @FXML
    private TableColumn<PlayerModel, String> factionPlayersTableNickColumn;
    @FXML
    private Button deleteFactionButton;
    @FXML
    private Button createFactionButton;
    @FXML
    private Button allyFactionButton;
    @FXML
    private TextField sloganProperty;

    public FactionListView(ClientContext clientContext, String controllerName, URL fxmlLocation, Stage stage) {
        super(clientContext, controllerName, fxmlLocation, stage);
    }

    @Override
    public void init() {
        // изначально окно не показано
        getStage().hide();
        // при изменении значения переменной показывать или скрывать окно
        getModel().windowVisibleProperty().onChanged(v -> {
            if (Boolean.TRUE.equals(v.getNewValue())) {
                getStage().setOnShowing(event -> invoke(Constants.SHOW_EVENT));
                getStage().show();
            } else  {
                getStage().hide();
            }
        });
        // при закрытии окна -- выставить переменную в false
        getStage().setOnCloseRequest(event -> getModel().windowVisibleProperty().set(false));

        factionTableNameColumn.setCellValueFactory(e -> FXWrapper.wrapObjectProperty(e.getValue().nameProperty()));
        factionTableOwnerColumn.setCellValueFactory(e -> FXWrapper.wrapObjectProperty(e.getValue().ownerNameProperty()));
        factionTableAllyColumn.setCellValueFactory(e -> FXWrapper.wrapObjectProperty(e.getValue().typeAllyProperty()));

        factionPlayersTableIdColumn.setCellValueFactory(e->FXWrapper.wrapObjectProperty(e.getValue().idProperty()));
        factionPlayersTableUserIdColumn.setCellValueFactory(e->FXWrapper.wrapObjectProperty(e.getValue().userIdProperty()));
        factionPlayersTableNickColumn.setCellValueFactory(e->FXWrapper.wrapObjectProperty(e.getValue().nicknameProperty()));

        FXBinder.bind(factionFilterPattern.textProperty())
                .bidirectionalTo(getModel().filterProperty());
        FXBinder.bind(factionTableView.getItems())
                .bidirectionalTo(getModel().factionsProperty());
        FXBinder.bind(factionPlayersTableView.getItems())
                .bidirectionalTo(getModel().playersProperty());

        // looks ugly but when init method runs, currentUserModelProperty is returning "null",
        // causing NullPointerException.
        // it will be initialized with an object once and only once (in LoginView) and will be attached at that exact time.
        //getModel().selectedFactionProperty().onChanged(valueChangeEvent -> FXBinder.bind(sloganProperty.textProperty())
                //.bidirectionalTo(getModel().selectedFactionProperty().get().sloganProperty()));

        //getModel().factionsProperty().onChanged(listChangeEvent -> FXBinder.bind(factionTableView.getItems())
        //        .bidirectionalTo(getModel().factionsProperty()));

        //if (1==1) throw new RuntimeException("переделать код ниже");

        //Param param2 = new Param("factionId", factionTableView.getSelectionModel().getSelectedItem().getId());
        //if (factionTableView.getSelectionModel().getSelectedItem() != null)
        //    System.out.println("null in selected item");

        allyFactionButton.setOnAction(event -> {
            String allyNote = ShowAllyFactionDialog(getModel().selectedFactionProperty().get().getName());
            if (allyNote.isEmpty()) return;
            Param allyNoteParam = new Param("note", allyNote);
            invoke(Constants.SET_ALLY_FACTION_EVENT, allyNoteParam);
        });

        /*createFactionButton.setOnAction(event -> {
            String factionName = ShowCreateFactionDialog();
            if (factionName.isEmpty()) return;
            Param factionNameParam = new Param("factionName", factionName);
            invoke(Constants.CREATE_FACTION_EVENT, factionNameParam);
        });*/

        createFactionButton.setOnAction(event -> {
            Map<String, String> factionName = ShowCreateFactionDialog1().orElse(new TreeMap<>());
            if (factionName.get("factionName").isEmpty()) return;
            Param factionNameParam = new Param("factionName", factionName.get("factionName"));
            Param factionSloganParam = new Param("factionSlogan", factionName.get("factionSlogan"));
            invoke(Constants.CREATE_FACTION_EVENT, factionNameParam, factionSloganParam);
        });

        deleteFactionButton.setOnAction(e -> invoke(Constants.DELETE_FACTION_EVENT));
        deleteFactionButton.setDisable(true);
        FXBinder.bind(deleteFactionButton.disableProperty())
                .to(getModel().cannotDeleteCurrentFactionProperty());



        factionTableView.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    if (newValue != oldValue) {
                        getModel().selectedFactionProperty().set(newValue);
                        sloganProperty.setText(getModel().selectedFactionProperty().get().getSlogan());
                    }
                });

    }

    private String ShowCreateFactionDialog(){
        TextInputDialog dialog = new TextInputDialog("Faction name");
        dialog.setTitle("Create faction");
        dialog.setHeaderText("Do you want create new faction?");
        dialog.setContentText("Please enter faction name:");

        Optional<String> result = dialog.showAndWait();
        return result.orElse("");
    }

    private String ShowAllyFactionDialog(String factionNameToAlly){
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

    private Optional<Map<String, String>> ShowCreateFactionDialog1() {
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

    @Override
    protected void onInitializationException(Throwable t) {
        t.printStackTrace();
        super.onInitializationException(t);
    }

    @Override
    protected void onInvocationException(ControllerActionException e) {
        e.printStackTrace();
        super.onInvocationException(e);
    }

}
