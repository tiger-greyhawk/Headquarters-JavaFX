package name.timoshenko.communityhelper.common.model;

import com.canoo.dolphin.collections.ObservableList;
import com.canoo.dolphin.mapping.DolphinBean;
import com.canoo.dolphin.mapping.Property;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.io.Serializable;
import java.util.List;

@DolphinBean
@EnableScheduling
@EnableAsync
public class RequestResourceListWindowModel implements Serializable{

    private Property<Boolean> windowVisible;

    public Property<CurrentUserModel> currentUserModel;

    private ObservableList<RequestResourceModel> requestsResources;

    private Property<RequestResourceModel> newRequest;

    public Property<Boolean> windowVisibleProperty() {
        return windowVisible;
    }

    public Property<CurrentUserModel> currentUserModelProperty() {
        return currentUserModel;
    }

    public ObservableList<RequestResourceModel> requestsResourcesProperty(){
        return requestsResources;
    }

    public Property<RequestResourceModel> newRequestProperty(){
        return newRequest;
    }

    @Async
    @Scheduled(initialDelay = 5000, fixedDelay = 5000)
    public void setRequestsResources(List<RequestResourceModel> e) {
        for (RequestResourceModel res: e
             ) {
            requestsResourcesProperty().add(res);
        }
    }

}
