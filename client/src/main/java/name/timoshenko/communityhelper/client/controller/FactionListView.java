package name.timoshenko.communityhelper.client.controller;

import com.canoo.platform.client.ClientContext;
import com.canoo.platform.client.ControllerActionException;
import com.canoo.platform.client.javafx.FXBinder;
import com.canoo.platform.client.javafx.binding.FXWrapper;
import com.canoo.platform.client.javafx.view.AbstractFXMLViewBinder;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import name.timoshenko.communityhelper.common.Constants;
import name.timoshenko.communityhelper.common.model.FactionListModel;
import name.timoshenko.communityhelper.common.model.FactionModel;
import name.timoshenko.communityhelper.common.model.PlayerModel;

import java.net.MalformedURLException;

/**
 *
 */
public class FactionListView extends AbstractFXMLViewBinder<FactionListModel> {



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
    private TableColumn<PlayerModel, Long> factionPlayersTableIdColumn;
    @FXML
    private TableColumn<PlayerModel, Long> factionPlayersTableUserIdColumn;
    @FXML
    private TableColumn<PlayerModel, String> factionPlayersTableNickColumn;
    @FXML
    private Button deleteFactionButton;
    @FXML
    private TextField loginProperty;

    public FactionListView(ClientContext clientContext) throws MalformedURLException {
        super(clientContext, Constants.FACTION_LIST_CONTROLLER_NAME, FactionListView.class.getResource("/view/faction_list_window.fxml"));
    }

    @Override
    public void init() {
        factionTableNameColumn.setCellValueFactory(e -> FXWrapper.wrapObjectProperty(e.getValue().nameProperty()));
        factionTableOwnerColumn.setCellValueFactory(e -> FXWrapper.wrapObjectProperty(e.getValue().ownerNameProperty()));

        factionPlayersTableIdColumn.setCellValueFactory(e->FXWrapper.wrapObjectProperty(e.getValue().idProperty()));
        factionPlayersTableUserIdColumn.setCellValueFactory(e->FXWrapper.wrapObjectProperty(e.getValue().userIdProperty()));
        factionPlayersTableNickColumn.setCellValueFactory(e->FXWrapper.wrapObjectProperty(e.getValue().nicknameProperty()));

        FXBinder.bind(factionFilterPattern.textProperty())
                .bidirectionalTo(getModel().filterProperty());
        FXBinder.bind(factionTableView.getItems())
                .bidirectionalTo(getModel().factionsProperty());
        FXBinder.bind(factionPlayersTableView.getItems())
                .bidirectionalTo(getModel().playersProperty());

        FXBinder.bind(loginProperty.textProperty())
                .bidirectionalTo(getModel().currentUserModelProperty().get().loginProperty());



        deleteFactionButton.setDisable(true);
        FXBinder.bind(deleteFactionButton.disableProperty())
                .to(getModel().cannotDeleteCurrentFactionProperty());

        factionTableView.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    if (newValue != oldValue) {
                        getModel().selectedFactionProperty().set(newValue);
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
