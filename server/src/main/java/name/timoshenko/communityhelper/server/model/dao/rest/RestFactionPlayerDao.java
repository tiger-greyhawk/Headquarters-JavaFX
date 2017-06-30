package name.timoshenko.communityhelper.server.model.dao.rest;

import name.timoshenko.communityhelper.server.model.dao.FactionPlayerDao;
import name.timoshenko.communityhelper.server.model.domain.FactionPlayer;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 *
 */
@Service("restFactionPlayerDao")
public class RestFactionPlayerDao implements FactionPlayerDao {
    @Override
    public List<FactionPlayer> findAll() {
        return null;
    }

    @Override
    public Optional<FactionPlayer> findById(long id) {
        return null;
    }

    @Override
    public List<FactionPlayer> findById(List<Long> ids) {
        return null;
    }

    @Override
    public FactionPlayer save(FactionPlayer object) {
        return null;
    }

    @Override
    public boolean exists(long id) {
        return false;
    }

    @Override
    public boolean delete(long id) {
        return false;
    }

    @Override
    public List<FactionPlayer> findByFactionId(long factionId) {
        return null;
    }
}
