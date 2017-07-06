package name.timoshenko.communityhelper.client.controller;

import com.canoo.platform.client.ClientContext;
import com.canoo.platform.client.javafx.view.AbstractFXMLViewBinder;
import javafx.stage.Stage;

import java.net.URL;

/**
 * Abstract class that extends view binder with an own stage to be able to hide and show window
 */
public abstract class StagedFXMLViewBinder<T> extends AbstractFXMLViewBinder<T> {

    private final Stage stage;

    public StagedFXMLViewBinder(ClientContext clientContext, String controllerName, URL fxmlLocation, Stage stage) {
        super(clientContext, controllerName, fxmlLocation);
        this.stage = stage;
    }

    public Stage getStage() {
        return stage;
    }
}
