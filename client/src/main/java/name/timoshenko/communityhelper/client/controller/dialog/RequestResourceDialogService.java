package name.timoshenko.communityhelper.client.controller.dialog;

import name.timoshenko.communityhelper.common.model.RequestResourceModel;

import java.util.Optional;

public interface RequestResourceDialogService {
    Optional<RequestResourceModel> showCreateNewRequestDialog(RequestResourceModel requestResourceModel);
}
