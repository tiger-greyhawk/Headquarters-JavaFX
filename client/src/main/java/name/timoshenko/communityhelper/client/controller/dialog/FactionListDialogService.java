package name.timoshenko.communityhelper.client.controller.dialog;

import java.util.Map;
import java.util.Optional;

public interface FactionListDialogService {
    String ShowAllyFactionDialog(String factionNameToAlly);
    Optional<Map<String, String>> ShowCreateFactionDialog();
}
