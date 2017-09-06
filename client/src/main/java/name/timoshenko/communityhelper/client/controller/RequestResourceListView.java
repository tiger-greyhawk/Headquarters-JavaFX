package name.timoshenko.communityhelper.client.controller;

import com.canoo.platform.client.ClientContext;
import com.canoo.platform.client.ControllerActionException;
import com.canoo.platform.client.javafx.FXBinder;
import com.canoo.platform.client.javafx.binding.FXWrapper;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import name.timoshenko.communityhelper.client.controller.dialog.RequestResourceDialogService;
import name.timoshenko.communityhelper.client.controller.dialog.SimpleRequestResourceDialogService;
import name.timoshenko.communityhelper.common.Constants;
import name.timoshenko.communityhelper.common.model.RequestResourceListWindowModel;
import name.timoshenko.communityhelper.common.model.RequestResourceModel;

import java.net.URL;
import java.util.EmptyStackException;

public class RequestResourceListView extends StagedFXMLViewBinder<RequestResourceListWindowModel> {

    private final RequestResourceDialogService requestResourceDialogService;

    @FXML
    private TableView<RequestResourceModel> requestResourceTableView;
    @FXML
    private TableColumn<RequestResourceModel, String> requestResourceTablePlayerNameColumn;
    @FXML
    private TableColumn<RequestResourceModel, String> requestResourceTableVillageIdColumn;
    @FXML
    private TableColumn<RequestResourceModel, String> requestResourceTableNameColumn;
    @FXML
    private TableColumn<RequestResourceModel, String> requestResourceTableAmountColumn;
    @FXML
    private Button newRequestButton;

    public RequestResourceListView(ClientContext clientContext, String controllerName, URL fxmlLocation, Stage stage) {
        super(clientContext, controllerName, fxmlLocation, stage);
        requestResourceDialogService = new SimpleRequestResourceDialogService();
    }

    @Override
    protected void init() {
        getStage().hide();
        // при изменении значения переменной показывать или скрывать окно
        getModel().windowVisibleProperty().onChanged(v -> {
            if (Boolean.TRUE.equals(v.getNewValue())) {
                getStage().setOnShowing(event -> invoke(Constants.SHOW_EVENT));
                getStage().show();

            } else  {
                getStage().hide();
            }
        });
        getStage().setOnCloseRequest(event -> getModel().windowVisibleProperty().set(false));

        FXBinder.bind(requestResourceTableView.getItems())
                .bidirectionalTo(getModel().requestsResourcesProperty());


        requestResourceTableNameColumn.setCellValueFactory(e -> FXWrapper.wrapObjectProperty(e.getValue().nameProperty()));
        requestResourceTableAmountColumn.setCellValueFactory(e -> FXWrapper.wrapObjectProperty(e.getValue().amountProperty()));
        requestResourceTablePlayerNameColumn.setCellValueFactory(e -> FXWrapper.wrapObjectProperty(e.getValue().playerProperty().get().nicknameProperty()));

        getModel().requestsResourcesProperty().onChanged(listChangeEvent -> {

            //invoke(Constants.LIST_CHANGE_EVENT);
        });

        newRequestButton.setOnAction(e -> {
            RequestResourceModel requestResource = requestResourceDialogService.showCreateNewRequestDialog(getModel().newRequestProperty().get())
                    .orElseThrow(() -> new EmptyStackException());
            if (requestResource.getName().isEmpty()) return;
            getModel().newRequestProperty().set(requestResource);
            //Param resource = new Param("requestResource", requestResource);
            //Param resourceNameParam = new Param("resourceName", requestResource.get("resourceName"));
            //Param resourceAmountParam = new Param("resourceAmount", requestResource.get("resourceAmount"));
            //Param resourceTypeParam = new Param("resourceType", requestResource.get("resourceType"));
            invoke(Constants.NEW_REQUEST_EVENT);
        });
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
