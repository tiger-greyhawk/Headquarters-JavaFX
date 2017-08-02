package name.timoshenko.communityhelper.client;

import com.canoo.platform.client.ClientContext;
import com.canoo.platform.client.ClientInitializationException;
import com.canoo.platform.client.ClientShutdownException;
import com.canoo.platform.client.javafx.DolphinPlatformApplication;
import com.canoo.platform.core.DolphinRuntimeException;
import com.google.common.base.CaseFormat;
import javafx.scene.Scene;
import javafx.stage.Stage;
import name.timoshenko.communityhelper.client.controller.FactionListView;
import name.timoshenko.communityhelper.client.controller.LoginView;
import name.timoshenko.communityhelper.client.controller.MainView;
import name.timoshenko.communityhelper.client.controller.StagedFXMLViewBinder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;


/**
 *
 */
public class JavaFXClient extends DolphinPlatformApplication {

    public static void main(String[] args) {
        launch(args);
    }

    private Logger LOG = LoggerFactory.getLogger(JavaFXClient.class);

    /**
     * Эта функция инизиализирует окно (stage) по определённому классу.
     * <p/>
     * Требует, чтобы fxml назывался /view/имя_класса_без_view_window.fxml
     * контроллер должен называться ИМЯ_КЛАССА_БЕЗ_VIEW_CONTROLLER.
     * <p/>
     * Пример для FactionListView:
     * FXML: /view/faction_list_view.fxml
     * ControllerName: FACTION_LIST_CONTROLLER
     */
    private Stage createFxmlView(final ClientContext clientContext,
                                   final Class<? extends StagedFXMLViewBinder<?>> clazz,
                                   final Stage stage) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        final String controllerName = CaseFormat.UPPER_CAMEL
                .to(CaseFormat.UPPER_UNDERSCORE, clazz.getSimpleName().replace("View", "Controller"));
        final String fxmlName = CaseFormat.UPPER_CAMEL
                .to(CaseFormat.LOWER_UNDERSCORE, clazz.getSimpleName().replace("View", "Window"));
        final URL fxmlLocation = clazz.getResource("/view/" + fxmlName + ".fxml");
        final Constructor<? extends StagedFXMLViewBinder<?>> constructor = clazz.getConstructor(
                ClientContext.class, String.class, URL.class, Stage.class);
        final StagedFXMLViewBinder<?> instance = constructor.newInstance(clientContext, controllerName, fxmlLocation, stage);
        final Scene scene = new Scene(instance.getParent());
        stage.setScene(scene);
        return stage;
    }

    @Override
    protected URL getServerEndpoint() throws MalformedURLException {
        return new URL("http://localhost:8080/dolphin");
    }

    @Override
    protected void start(Stage mainStage, ClientContext clientContext) throws Exception {
        createFxmlView(clientContext, MainView.class, mainStage);
        createFxmlView(clientContext, FactionListView.class, new Stage());
        createFxmlView(clientContext, LoginView.class, new Stage());
        //createFxmlView(clientContext, TimeAttackListView.class, new Stage());
// Нельзя создавать сразу все окна все-таки... Даже hide().
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
