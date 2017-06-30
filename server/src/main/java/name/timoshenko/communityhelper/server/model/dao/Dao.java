package name.timoshenko.communityhelper.server.model.dao;

import java.util.List;
import java.util.Optional;

/**
 *
 */
public interface Dao<T> {
    List<T> findAll();
    Optional<T> findById(long id);
    List<T> findById(List<Long> ids);
    T save(T object);
    boolean exists(long id);
    boolean delete(long id);
}
