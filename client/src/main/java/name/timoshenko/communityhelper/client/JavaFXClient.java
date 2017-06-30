package name.timoshenko.communityhelper.client;

import com.canoo.platform.client.ClientContext;
import com.canoo.platform.client.ClientInitializationException;
import com.canoo.platform.client.ClientShutdownException;
import com.canoo.platform.client.javafx.DolphinPlatformApplication;
import com.canoo.platform.core.DolphinRuntimeException;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import name.timoshenko.communityhelper.client.controller.FactionListView;
import name.timoshenko.communityhelper.client.controller.LoginView;

import java.net.MalformedURLException;
import java.net.URL;

/**
 *
 */
public class JavaFXClient extends DolphinPlatformApplication {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    protected URL getServerEndpoint() throws MalformedURLException {
        return new URL("http://localhost:8080/dolphin");
    }

    @Override
    protected void start(Stage stage, ClientContext clientContext) throws Exception {
        final FactionListView viewBinder = new FactionListView(clientContext);
        final Scene scene = new Scene(viewBinder.getParent());
        stage.setScene(scene);
        stage.setOnCloseRequest(e -> System.exit(0));
        stage.show();

        final Stage loginStage = new Stage();
        final LoginView loginView = new LoginView(clientContext, loginStage);
        final Scene loginScene = new Scene(loginView.getParent());
        loginStage.setScene(loginScene);
        loginStage.setResizable(false);
        loginStage.initModality(Modality.APPLICATION_MODAL);
        loginStage.show();
    }

    @Override
    protected void onInitializationError(Stage primaryStage, ClientInitializationException initializationException, Iterable<DolphinRuntimeException> possibleCauses) {
        initializationException.printStackTrace();
        super.onInitializationError(primaryStage, initializationException, possibleCauses);
    }

    @Override
    protected void onRuntimeError(Stage primaryStage, DolphinRuntimeException runtimeException) {
        runtimeException.printStackTrace();
        super.onRuntimeError(primaryStage, runtimeException);
    }

    @Override
    protected void onShutdownError(ClientShutdownException shutdownException) {
        shutdownException.printStackTrace();
        super.onShutdownError(shutdownException);
    }
}
