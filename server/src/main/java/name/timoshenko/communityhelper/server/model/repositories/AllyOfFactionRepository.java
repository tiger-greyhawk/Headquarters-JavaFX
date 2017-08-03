package name.timoshenko.communityhelper.server.model.repositories;

import name.timoshenko.communityhelper.server.model.domain.AllyOfFaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AllyOfFactionRepository extends JpaRepository<AllyOfFaction, Long> {
    List<AllyOfFaction> findByFirstFactionId(Long firstFactionId);
    List<AllyOfFaction> findBySecondFactionId(Long secondFactionId);
}
