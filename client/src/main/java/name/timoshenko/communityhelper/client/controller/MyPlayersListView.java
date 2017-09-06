package name.timoshenko.communityhelper.client.controller;

import com.canoo.platform.client.ClientContext;
import com.canoo.platform.client.ControllerActionException;
import com.canoo.platform.client.javafx.FXBinder;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import name.timoshenko.communityhelper.common.Constants;
import name.timoshenko.communityhelper.common.model.MyPlayersListWindowModel;
import name.timoshenko.communityhelper.common.model.PlayerModel;
import name.timoshenko.communityhelper.common.model.WorldAdminModel;

import java.net.URL;
import java.util.stream.Collectors;

public class MyPlayersListView extends StagedFXMLViewBinder<MyPlayersListWindowModel> {

    @FXML
    private ComboBox<PlayerModel> playersComboBox;
    @FXML
    private Button saveActivePlayer;
    @FXML
    private TextField newPlayerNickname;
    @FXML
    private ComboBox<WorldAdminModel> worldsComboBox;

    @FXML
    private Button addNewPlayer;

    public MyPlayersListView(ClientContext clientContext, String controllerName, URL fxmlLocation, Stage stage) {
        super(clientContext, controllerName, fxmlLocation, stage);
    }

    @Override
    protected void init() {
        // изначально окно не показано
        getStage().hide();
        //ObservableArrayList<String> nicknames = new ObservableArrayList<>();
        // при изменении значения переменной показывать или скрывать окно
        getModel().windowVisibleProperty().onChanged(v -> {
            if (Boolean.TRUE.equals(v.getNewValue())) {
                getStage().setOnShowing(event -> invoke(Constants.SHOW_EVENT));
                /*playersComboBox.setItems(FXCollections.observableArrayList(
                        getModel().myPlayersProperty().stream().map(playerModel -> {
                            return playerToNick(playerModel);
                        }).collect(Collectors.toList())
                        ));
                playersComboBox.setValue(getModel().myActivePlayerProperty().get().getNickName());*/
                /*nicknames.addAll(getModel().myPlayersProperty().stream().map(playerModel -> {
                    return playerToNick(playerModel);
                }).collect(Collectors.toList()));*/
                getStage().show();
            } else  {
                getStage().hide();
            }
        });
        // при закрытии окна -- выставить переменную в false
        getStage().setOnCloseRequest(event -> getModel().windowVisibleProperty().set(false));

        FXBinder.bind(worldsComboBox.getItems())
                .bidirectionalTo(getModel().worldsProperty());
        worldsComboBox.setConverter(worldAdminModelStringConverter);

        FXBinder.bind(playersComboBox.getItems())
                .bidirectionalTo(getModel().myPlayersProperty());
        playersComboBox.setConverter(playerModelStringConverter);

        FXBinder.bind(playersComboBox.valueProperty()).bidirectionalTo(getModel().selectedPlayerProperty());

        playersComboBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != oldValue) {
                getModel().selectedPlayerProperty().set(newValue);
                if (newValue!=null) return;
                    //worldName.setText(getModel().selectedPlayerProperty().get().getWorldName());
            }
        });

        saveActivePlayer.setOnAction(event -> {
            invoke(Constants.MYPLAYERS_LIST_WINDOW_EVENT_SET_ACTIVE_PLAYER);
        });

        addNewPlayer.setOnAction(event -> {
            getModel().newPlayerProperty().get().worldProperty().set(worldsComboBox.getSelectionModel().getSelectedItem());
            getModel().newPlayerProperty().get().nicknameProperty().set(newPlayerNickname.getText());
            invoke(Constants.MYPLAYERS_LIST_WINDOW_EVENT_ADD_NEW_PLAYER);
        });


    }

    private StringConverter<WorldAdminModel> worldAdminModelStringConverter = new StringConverter<WorldAdminModel>() {
        @Override
        public String toString(WorldAdminModel worldAdminModel) {
            return worldAdminModel.nameProperty().get();
        }

        @Override
        public WorldAdminModel fromString(String id) {
            return getModel().worldsProperty().stream()
                    .filter(item -> item.idProperty().get().equals(id))
                    .collect(Collectors.toList()).get(0);
        }
    };

    private StringConverter<PlayerModel> playerModelStringConverter = new StringConverter<PlayerModel>() {
        @Override
        public String toString(PlayerModel playerModel) {
            return playerModel.nicknameProperty().get()+" ("+playerModel.worldProperty().get().getName()+")";
        }

        @Override
        public PlayerModel fromString(String id) {
            return getModel().myPlayersProperty().stream()
                    .filter(item -> item.idProperty().get().equals(id))
                    .collect(Collectors.toList()).get(0);
        }
    };

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
