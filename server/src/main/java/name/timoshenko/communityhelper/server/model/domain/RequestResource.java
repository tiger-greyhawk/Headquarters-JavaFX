package name.timoshenko.communityhelper.server.model.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "request_resource")
public class RequestResource implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private final Long id;
    @Column(name = "player_id")
    private final Long playerId;
    @Column(name = "name")
    private final String name;
    @Column(name = "amount")
    private final String amount;

    public RequestResource() { this(-1L, -1L, "", "");
    }

    public RequestResource(Long id, Long playerId, String name, String amount) {
        this.id = id;
        this.playerId = playerId;
        this.name = name;
        this.amount = amount;
    }

    public Long getId() {
        return id;
    }

    public Long getPlayerId() {
        return playerId;
    }

    public String getName() {
        return name;
    }

    public String getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return "RequestResource{" +
                "id=" + id +
                ", playerId=" + playerId +
                ", name='" + name + '\'' +
                ", amount='" + amount + '\'' +
                '}';
    }
}
