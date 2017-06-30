package name.timoshenko.communityhelper.server.model.dao.dummy;

import name.timoshenko.communityhelper.server.model.domain.Faction;
import name.timoshenko.communityhelper.server.model.dao.FactionDao;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 *
 */
@Service("dummyFactionDao")
public class DummyFactionDao implements FactionDao {

    private final List<Faction> dummy = Arrays.asList(
            new Faction(1L, "The United Earth Federation", 1L),
            new Faction(2L, "The Cybran Nation", 2L),
            new Faction(3L, "The Aeon Illuminate", 3L),
            new Faction(4L, "The Seraphim", 4L)
    );

    @Override
    public List<Faction> findAll() {
        return dummy;
    }

    @Override
    public Optional<Faction> findById(long id) {
        return dummy.stream().filter(i -> i.getId().equals(id)).findFirst();
    }

    @Override
    public List<Faction> findById(List<Long> ids) {
        return dummy.stream().filter(i -> ids.contains(i.getId())).collect(Collectors.toList());
    }

    @Override
    public Faction save(Faction object) {
        return null;
    }

    @Override
    public boolean exists(long id) {
        return dummy.stream().anyMatch(i -> i.getId() == id);
    }

    @Override
    public boolean delete(long id) {
        return false;
    }

    @Override
    public List<Faction> findByPattern(String pattern) {
        return dummy.stream().filter(i -> i.getName().matches(pattern))
                .collect(Collectors.toList());
    }
}
