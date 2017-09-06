package name.timoshenko.communityhelper.client.controller;

import com.canoo.platform.client.ClientContext;
import com.canoo.platform.client.ControllerActionException;
import com.canoo.platform.client.Param;
import com.canoo.platform.client.javafx.FXBinder;
import com.canoo.platform.client.javafx.binding.FXWrapper;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import name.timoshenko.communityhelper.client.controller.dialog.FactionListDialogService;
import name.timoshenko.communityhelper.client.controller.dialog.SimpleFactionListDialogService;
import name.timoshenko.communityhelper.client.controller.event.BasicFactionListEvent;
import name.timoshenko.communityhelper.client.controller.event.FactionListEvent;
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
    private TableColumn<FactionModel, String> factionTableWorldColumn;
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
    private Button joinFactionButton;
    @FXML
    private Button allyFactionButton;
    @FXML
    private TextField sloganProperty;

    FactionListDialogService factionListDialogService;
    FactionListEvent factionListEvent;

    public FactionListView(ClientContext clientContext, String controllerName, URL fxmlLocation, Stage stage) {
        super(clientContext, controllerName, fxmlLocation, stage);
        this.factionListDialogService = new SimpleFactionListDialogService();
        this.factionListEvent = new BasicFactionListEvent();
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
                createFactionButton.setDisable(true);
                FXBinder.bind(createFactionButton.disableProperty())
                        .to(getModel().selectedPlayerProperty().get().canNotPlayerCreateFactionProperty());
                FXBinder.bind(joinFactionButton.disableProperty())
                        .to(getModel().selectedPlayerProperty().get().canNotPlayerCreateFactionProperty());
            } else  {
                getStage().hide();
            }
        });
        // при закрытии окна -- выставить переменную в false
        getStage().setOnCloseRequest(event -> getModel().windowVisibleProperty().set(false));

        factionTableNameColumn.setCellValueFactory(e -> FXWrapper.wrapObjectProperty(e.getValue().nameProperty()));
        factionTableWorldColumn.setCellValueFactory(e -> FXWrapper.wrapObjectProperty(e.getValue().worldProperty()));
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

        allyFactionButton.setOnAction(event -> {
            Optional<Map<String, String>> allyDialogResult =
                    factionListDialogService.ShowAllyFactionDialog1(getModel().selectedFactionProperty().get().getName());
            if (!allyDialogResult.isPresent() || allyDialogResult.get().get("allyType")==null) return;
            Param allyType = new Param("allyType", allyDialogResult.get().get("allyType"));
            Param allyNote = new Param("allyNote", allyDialogResult.get().get("allyNote"));
            invoke(Constants.SET_ALLY_FACTION_EVENT, allyType, allyNote);
        });

        /*TODO разобраться как вынести ивенты в отдельный ифейс!
        */
        //allyFactionButton.setOnAction(factionListEvent.setAllyFaction(getModel().selectedFactionProperty().get().getName()));

        createFactionButton.setOnAction(event -> {
            Map<String, String> factionName = factionListDialogService.ShowCreateFactionDialog(getModel().worldsProperty()).orElse(new TreeMap<>());
            if (factionName.isEmpty() || factionName.get("factionName").isEmpty() || factionName.get("worldId").isEmpty()) return;
            Param factionNameParam = new Param("factionName", factionName.get("factionName"));
            Param factionSloganParam = new Param("factionSlogan", factionName.get("factionSlogan"));
            Param worldIdParam = new Param("worldId", factionName.get("worldId"));
            invoke(Constants.CREATE_FACTION_EVENT, factionNameParam, factionSloganParam, worldIdParam);
        });

        deleteFactionButton.setOnAction(e -> invoke(Constants.DELETE_FACTION_EVENT));
        deleteFactionButton.setDisable(true);
        FXBinder.bind(deleteFactionButton.disableProperty())
                .to(getModel().cannotDeleteCurrentFactionProperty());

        joinFactionButton.setOnAction(e -> {
            if (getModel().selectedPlayerProperty().get().canNotPlayerCreateFactionProperty().get()) {
                joinFactionButton.textProperty().set("Join");
                invoke(Constants.LEAVE_FACTION_EVENT);
            }
            else {
                joinFactionButton.textProperty().set("Leave");
                invoke(Constants.JOIN_FACTION_EVENT);
            }
        });



        factionTableView.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    if (newValue != oldValue) {
                        getModel().selectedFactionProperty().set(newValue);
                        if (newValue!=null)
                            sloganProperty.setText(getModel().selectedFactionProperty().get().getSlogan());
                    }
                });

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
