package name.timoshenko.communityhelper.server.model.service.basic;

import name.timoshenko.communityhelper.server.model.dao.FactionPlayerDao;
import name.timoshenko.communityhelper.server.model.domain.FactionPlayer;
import name.timoshenko.communityhelper.server.model.service.FactionPlayerService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 *
 */
@Service("basicFactionPlayerService")
public class BasicFactionPlayerService implements FactionPlayerService {

    private final FactionPlayerDao factionPlayerDao;

    public BasicFactionPlayerService(@Qualifier("dummyFactionPlayerDao") FactionPlayerDao factionPlayerDao) {
        this.factionPlayerDao = factionPlayerDao;
    }

    @Override
    public List<Long> findPlayersByFactionId(long factionId) {
        return factionPlayerDao.findByFactionId(factionId)
                .stream()
                .map(FactionPlayer::getPlayerId)
                .collect(Collectors.toList());
    }
}
