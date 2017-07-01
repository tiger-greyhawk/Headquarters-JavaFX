package name.timoshenko.communityhelper.client;

import com.canoo.dolphin.BeanManager;
import com.canoo.platform.client.*;
import com.canoo.platform.client.javafx.DolphinPlatformApplication;
//import com.canoo.platform.client.javafx.JavaFXConfiguration;
import com.canoo.platform.client.javafx.JavaFXConfiguration;
import com.canoo.platform.core.DolphinRuntimeException;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import name.timoshenko.communityhelper.client.controller.FactionListView;
import name.timoshenko.communityhelper.client.controller.LoginView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.net.www.protocol.http.HttpURLConnection;


//import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.CompletableFuture;

/**
 *
 */
public class JavaFXClient extends DolphinPlatformApplication {

    public static void main(String[] args) {
        launch(args);
    }

    private Logger LOG = LoggerFactory.getLogger(JavaFXClient.class);

    @Override
    protected URL getServerEndpoint() throws MalformedURLException {
        return new URL("http://localhost:8080/dolphin");
        //return new URL("http://localhost:8080");
    }

    /*
    HttpURLConnectionFactory myFactory = url -> {
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestProperty("Security-Header", "My secret token");
        return connection;
    };


    ClientConfiguration configuration = new JavaFXConfiguration(getServerEndpoint());
        configuration.setConnectionFactory(myFactory);
        */

    @Override
    protected void start(Stage stage, ClientContext clientContext) throws Exception {

        String clientId = clientContext.getClientId();

        HttpURLConnectionFactory myFactory = url -> {
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("User1", "123");
            return connection;
        };

        myFactory.create(getServerEndpoint());
        ClientConfiguration configuration = new JavaFXConfiguration(getServerEndpoint());
        configuration.setConnectionFactory(myFactory);

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
        LOG.error("Dolphin Platform runtime error in thread " + runtimeException.getThread().getName(), runtimeException);
        super.onRuntimeError(primaryStage, runtimeException);
    }

    @Override
    protected void onShutdownError(ClientShutdownException shutdownException) {
        shutdownException.printStackTrace();
        super.onShutdownError(shutdownException);
    }
}
