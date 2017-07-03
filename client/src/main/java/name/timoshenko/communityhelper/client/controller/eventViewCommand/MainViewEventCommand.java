package name.timoshenko.communityhelper.client.controller.eventViewCommand;

import com.canoo.platform.client.ClientContext;
import com.sun.xml.internal.ws.api.policy.PolicyResolver;
import javafx.scene.Scene;
import javafx.stage.Stage;
import name.timoshenko.communityhelper.client.controller.FactionListView;

/**
 * Created by Tiger on 02.07.2017.
 */
public class MainViewEventCommand {


    public void FactionListShow(ClientContext clientContext)//throws MalformedURLException
    {
        final Stage factionStage = new Stage();
        final FactionListView factionViewBinder = new FactionListView(clientContext, factionStage);
        final Scene factionScene = new Scene(factionViewBinder.getParent());
        factionStage.setScene(factionScene);
        factionStage.show();
    }

    public void ExitApplication()
    {
        System.exit(0);
    }
}
