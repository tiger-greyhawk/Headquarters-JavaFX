package name.timoshenko.communityhelper.client.controller;

import com.canoo.platform.client.ClientContext;
import com.canoo.platform.client.ControllerActionException;
import com.canoo.platform.client.javafx.FXBinder;
import com.canoo.platform.client.javafx.view.AbstractFXMLViewBinder;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import name.timoshenko.communityhelper.common.Constants;
import name.timoshenko.communityhelper.common.model.CurrentUserModel;


/**
 *
 */
public class LoginView extends AbstractFXMLViewBinder<CurrentUserModel> {

    @FXML
    private CheckBox loggedInCheckbox;

    @FXML
    private Button loginButton;

    @FXML
    private TextField loginText;
    @FXML
    private TextField passwordText;

    private final Stage ownStage;

    /**
     * Constructor
     *
     * @param clientContext  the DOlphin Platform client context
     */
    public LoginView(ClientContext clientContext, Stage ownStage) {
        super(clientContext, Constants.LOGIN_CONTROLLER_NAME, FactionListView.class.getResource("/view/login_window.fxml"));
        this.ownStage = ownStage;
    }

    @Override
    protected void init() {
        FXBinder.bind(loginText.textProperty()).bidirectionalTo(getModel().loginProperty());
        FXBinder.bind(passwordText.textProperty()).bidirectionalTo(getModel().passwordProperty());

        // уверен, что диалог закрывается, не уверен, что он откроется. )
        getModel().loggedInProperty().onChanged(evt -> {
            System.out.println("LoggedInProperty value changed: " + evt.getOldValue() + "->" + evt.getNewValue());
            if (Boolean.FALSE.equals(evt.getNewValue())) {
                ownStage.show();
            } else {
                ownStage.hide();
            }
        });
        getParent().getScene().getWindow().setOnCloseRequest(e -> System.exit(0));
        loginButton.setOnAction(e -> invoke(Constants.LOGIN_EVENT));
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
