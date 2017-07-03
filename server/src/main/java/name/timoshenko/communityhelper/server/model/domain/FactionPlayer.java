package name.timoshenko.communityhelper.server.model.domain;

import javax.persistence.*;

/**
 *
 */
@Entity
@Table(name = "faction_players",
uniqueConstraints = {
    @UniqueConstraint(columnNames = {"faction_id", "player_id"})
})
public class FactionPlayer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private final Long id;
    @Column(name = "faction_id")
    private final Long factionId;
    @Column(name = "player_id")
    private final Long playerId;

    public FactionPlayer() {
        this(null, null, null);
    }

    public FactionPlayer(Long id, Long factionId, Long playerId) {
        this.id = id;
        this.factionId = factionId;
        this.playerId = playerId;
    }

    public Long getFactionId() {
        return factionId;
    }

    public Long getPlayerId() {
        return playerId;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "FactionPlayer{" +
                "id=" + id +
                ", factionId=" + factionId +
                ", playerId=" + playerId +
                '}';
    }
}
