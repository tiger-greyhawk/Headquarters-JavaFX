package name.timoshenko.communityhelper.client.controller;

import com.canoo.platform.client.ClientContext;
import javafx.stage.Stage;
import name.timoshenko.communityhelper.common.model.TimeAttackListWindowModel;

import java.net.URL;

/**
 * Created by Tiger on 24.07.2017.
 */
public class TimeAttackListView extends StagedFXMLViewBinder<TimeAttackListWindowModel> {

    public TimeAttackListView(ClientContext clientContext, String controllerName, URL fxmlLocation, Stage stage) {
        super(clientContext, controllerName, fxmlLocation, stage);
    }

    @Override
    protected void init() {
        getStage().show();
    }
}
