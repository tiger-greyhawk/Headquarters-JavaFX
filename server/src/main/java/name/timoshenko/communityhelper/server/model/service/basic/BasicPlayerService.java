package name.timoshenko.communityhelper.server.model.service.basic;

import name.timoshenko.communityhelper.server.model.domain.Player;
import name.timoshenko.communityhelper.server.model.repositories.PlayerRepository;
import name.timoshenko.communityhelper.server.model.service.PlayerService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 *
 */
@Service("basicPlayerService")
public class BasicPlayerService implements PlayerService {

    private final PlayerRepository playerRepository;

    public BasicPlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }


    /***
     * Доступны аннотации вида @Secured, @(Pre)(Post)Authorize, @(Pre)(Post)Filter  (как для всего сервиса, так и для отдельных методов)
     * Secured - только проверка обладания правами (не обязательно ролями)
     * Authorize -
     * Filter - для применения фильтра при выборке коллекции.
     * Examples: @PreAuthorize("#user.name == principal.username") тогда надо принимать параметр в методе:
     * public Optional<Player> findPlayer(long id, @P("user") User user)
     *
     * @PreAuthorize("hasRole('ROLE_USER')") - просто проверка обладания ролью.
     * @PreAuthorize("hasPermission(#player, 'user')") - Возможно также проверять каждый возвращаемый объект на права для текущего, к примеру, пользователя. Так ли это?
     * @PostFilter("hasRole('ROLE_USER') or filterObject.assignee == authentication.name") - с фильтром. Не разобрался. Применяется только для коллекций.
     * @PreFilter("hasRole('ROLE_USER')") -
     * @PostFilter("hasPermission(filterObject, 'read') or hasPermission(filterObject, 'admin')")
     *
     * Более подробно в документации здесь: https://docs.spring.io/spring-security/site/docs/current/reference/html/el-access.html
     */


    @Override
    public Optional<Player> findPlayer(long id) {
        return Optional.ofNullable(playerRepository.findOne(id));
    }

    @Override
    //@PostFilter("hasRole('ROLE_ADMIN')")
    //@Secured({"ROLE_USER", "ACL_DELETE"})
    //@Secured({"ACL_REPORT_ACCEPT"})
    //@PostAuthorize("returnObject.size() < 4")
    //@PostFilter("filterObject.getUserId() == principal.getId()")
    //@PreAuthorize("hasPermission('ACL_DELETE')")
    //@PostFilter("hasPermission(filterObject, 'READ')")
    //@PostFilter("hasPermission(filterObject, 'ACL_DELETE')")
    public List<Player> getPlayers(List<Long> ids) {
        List<Player> players = playerRepository.findAll(ids);
        return players;
    }
}
