package name.timoshenko.communityhelper.server.model.dao.rest;

import name.timoshenko.communityhelper.server.model.dao.FactionDao;
import name.timoshenko.communityhelper.server.model.domain.Faction;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 *
 */
@Service("restFactionDao")
public class RestFactionDao implements FactionDao {

    @Override
    public List<Faction> findAll() {
        return null;
    }

    @Override
    public Optional<Faction> findById(long id) {
        return null;
    }

    @Override
    public List<Faction> findById(List<Long> ids) {
        return null;
    }

    @Override
    public Faction save(Faction object) {
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
    public List<Faction> findByPattern(String name) {
        return null;
    }
}
