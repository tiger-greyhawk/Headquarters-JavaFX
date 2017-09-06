package name.timoshenko.communityhelper.client.controller;

import com.canoo.platform.client.ClientContext;
import com.canoo.platform.client.ControllerActionException;
import com.canoo.platform.client.javafx.FXBinder;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import name.timoshenko.communityhelper.common.Constants;
import name.timoshenko.communityhelper.common.model.LoginWindowModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;


/**
 *
 */
public class LoginView extends StagedFXMLViewBinder<LoginWindowModel> {

    private static final Logger LOG = LoggerFactory.getLogger(LoginView.class);

    @FXML
    private CheckBox loggedInCheckbox;

    @FXML
    private Button loginButton;

    @FXML
    private Button quitButton;

    @FXML
    private TextField loginText;

    @FXML
    private TextField passwordText;

    public LoginView(ClientContext clientContext, String controllerName, URL fxmlLocation, Stage stage) {
        super(clientContext, controllerName, fxmlLocation, stage);
    }


    @Override
    protected void init() {
        getStage().initModality(Modality.APPLICATION_MODAL);
        getStage().show();

        // show/hide the window automagicly when corresponding property is being modified
        getModel().currentUserModelProperty().get().loggedInProperty().onChanged(evt -> {
            if (Boolean.FALSE.equals(evt.getNewValue())) {
                getStage().show();
            } else {
                getStage().hide();
            }
        });

        FXBinder.bind(loginText.textProperty()).bidirectionalTo(getModel().currentUserModelProperty().get().loginProperty());
        FXBinder.bind(passwordText.textProperty()).bidirectionalTo(getModel().currentUserModelProperty().get().passwordProperty());
        loginText.setText("user3");
        passwordText.setText("123");

        getParent().getScene().getWindow().setOnCloseRequest(e -> System.exit(0));
        quitButton.setOnAction(e -> System.exit(0));
        loginButton.setOnAction(e -> invoke(Constants.LOGIN_EVENT));
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
