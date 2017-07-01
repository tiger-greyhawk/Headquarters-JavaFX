package name.timoshenko.communityhelper.server.controller.Listener;

//import com.sun.javafx.logging.Logger;
import com.canoo.platform.server.ServerListener;
import com.canoo.platform.server.client.ClientSession;
import com.canoo.platform.server.client.ClientSessionListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//import com.canoo.platform.server.spi;

/**
 * Created by Tiger on 01.07.2017.
 */
@ServerListener
public class SessionListener implements ClientSessionListener {

    private Logger LOG = LoggerFactory.getLogger(SessionListener.class);

    public void sessionCreated(ClientSession s) {
        LOG.info("Session with id {0} created!", s, s.getId());
    }

    public void sessionDestroyed(ClientSession s) {
        LOG.info("Session with id {0} destroyed!", s.getId());
    }

}
