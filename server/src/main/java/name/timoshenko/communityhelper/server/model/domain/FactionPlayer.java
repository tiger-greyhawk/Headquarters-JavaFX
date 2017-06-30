package name.timoshenko.communityhelper.server.model.domain;

/**
 *
 */
public class FactionPlayer {

    private final Long id;
    private final Long factionId;
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
