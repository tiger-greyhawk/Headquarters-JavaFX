package name.timoshenko.communityhelper.client.controller;

import com.canoo.platform.client.ClientContext;
import com.canoo.platform.client.ControllerActionException;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;
import name.timoshenko.communityhelper.common.model.MainWindowModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//import java.awt.*;

import java.net.URL;

import static name.timoshenko.communityhelper.common.Constants.TOGGLE_FACTION_WINDOW_ACTION;

/**
 *
 */
public class MainView extends StagedFXMLViewBinder<MainWindowModel> {

    private static final Logger LOG = LoggerFactory.getLogger(MainView.class);

    @FXML
    private MenuItem connectMenuItem;
    @FXML
    private MenuItem registrationMenuItem;
    @FXML
    private MenuItem logoutMenuItem;
    @FXML
    private MenuItem exitMenuItem;

    public MainView(ClientContext clientContext, String controllerName, URL fxmlLocation, Stage stage) {
        super(clientContext, controllerName, fxmlLocation, stage);
    }

    @Override
    protected void init() {
        getStage().setOnCloseRequest(e -> System.exit(0));
        getStage().show();

        // show factions window
        connectMenuItem.setOnAction(a -> getModel().factionWinowVisibleProperty().set(true));
        // log out
        logoutMenuItem.setOnAction(a -> getModel().currentUserModelProperty().get().loggedInProperty().set(false));
    }

    @Override
    protected void onInitializationException(Throwable t) {
        LOG.error("initialization error", t);
        super.onInitializationException(t);
    }

    @Override
    protected void onInvocationException(ControllerActionException e) {
        LOG.error("invocation error", e);
        super.onInvocationException(e);
    }
}
