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
    @Column(name = "invited")
    private final Boolean invited;
    @Column(name = "confirmed")
    private final Boolean confirmed;

    public FactionPlayer() {
        this(null, null, null, false, false);
    }

    public FactionPlayer(Long id, Long factionId, Long playerId, Boolean invited, Boolean confirmed) {
        this.id = id;
        this.factionId = factionId;
        this.playerId = playerId;
        this.invited = invited;
        this.confirmed = confirmed;
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

    public Boolean getInvited() {
        return invited;
    }

    public Boolean getConfirmed() {
        return confirmed;
    }

    @Override
    public String toString() {
        return "FactionPlayer{" +
                "id=" + id +
                ", factionId=" + factionId +
                ", playerId=" + playerId +
                ", invited=" + invited +
                ", confirmed=" + confirmed +
                '}';
    }
}
