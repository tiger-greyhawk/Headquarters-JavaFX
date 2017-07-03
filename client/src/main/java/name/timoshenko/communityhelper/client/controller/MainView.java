package name.timoshenko.communityhelper.client.controller;

import com.canoo.platform.client.ClientContext;
import com.canoo.platform.client.ControllerActionException;
import com.canoo.platform.client.javafx.view.AbstractFXMLViewBinder;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;
import name.timoshenko.communityhelper.client.controller.eventViewCommand.MainViewEventCommand;
import name.timoshenko.communityhelper.common.Constants;
import name.timoshenko.communityhelper.common.model.CurrentUserModel;
import name.timoshenko.communityhelper.common.model.MainModel;

//import java.awt.*;

import java.net.MalformedURLException;

/**
 *
 */
public class MainView extends AbstractFXMLViewBinder<MainModel> {

    private final Stage ownStage;
    private final ClientContext clientContext;

    private final MainViewEventCommand mainViewEventCommand;

    @FXML
    private MenuItem connectMenuItem;
    @FXML
    private MenuItem registrationMenuItem;
    @FXML
    private MenuItem exitMenuItem;

    public MainView(ClientContext clientContext, Stage ownStage) throws MalformedURLException {

        super(clientContext, Constants.MAIN_CONTROLLER_NAME, MainView.class.getResource("/view/headquarters_main_window.fxml"));

        this.clientContext = clientContext;
        this.ownStage = ownStage;
        mainViewEventCommand = new MainViewEventCommand();
    }

    @Override
    protected void init() {
        //connectMenuItem.setOnAction(e -> invoke(Constants.FACTION_LIST_SHOW_EVENT));
        connectMenuItem.setOnAction(e -> mainViewEventCommand.FactionListShow(clientContext));
        exitMenuItem.setOnAction(e -> mainViewEventCommand.ExitApplication());
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
