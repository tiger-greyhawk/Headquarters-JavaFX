package name.timoshenko.communityhelper.client.controller.dialog;

import name.timoshenko.communityhelper.common.model.WorldAdminModel;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface FactionListDialogService {
    String ShowAllyFactionDialog(String factionNameToAlly);
    Optional<Map<String, String>> ShowAllyFactionDialog1(String factionNameToAlly);
    Optional<Map<String, String>> ShowCreateFactionDialog(List<WorldAdminModel> worlds);
}
