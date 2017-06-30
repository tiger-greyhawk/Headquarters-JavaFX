package name.timoshenko.communityhelper.server.model.dao.dummy;

import name.timoshenko.communityhelper.server.model.dao.FactionPlayerDao;
import name.timoshenko.communityhelper.server.model.domain.FactionPlayer;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 *
 */
@Service("dummyFactionPlayerDao")
public class DummyFactionPlayerDao implements FactionPlayerDao {

    private final List<FactionPlayer> dummy = Arrays.asList(
            new FactionPlayer(0L, 1L, 1L),
            new FactionPlayer(1L, 1L, 2L),
            new FactionPlayer(2L, 1L, 3L),
            new FactionPlayer(3L, 2L, 4L),
            new FactionPlayer(4L, 2L, 5L),
            new FactionPlayer(5L, 3L, 6L),
            new FactionPlayer(6L, 3L, 7L),
            new FactionPlayer(7L, 3L, 8L),
            new FactionPlayer(8L, 3L, 9L)
    );

    @Override
    public List<FactionPlayer> findAll() {
        return dummy;
    }

    @Override
    public Optional<FactionPlayer> findById(long id) {
        return dummy.stream().filter(i -> i.getId().equals(id)).findFirst();
    }

    @Override
    public List<FactionPlayer> findById(List<Long> ids) {
        return dummy.stream().filter(i -> ids.contains(i.getId())).collect(Collectors.toList());
    }

    @Override
    public FactionPlayer save(FactionPlayer object) {
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
    public List<FactionPlayer> findByFactionId(long factionId) {
        return dummy.stream().filter(i -> i.getFactionId().equals(factionId))
                .collect(Collectors.toList());
    }
}
