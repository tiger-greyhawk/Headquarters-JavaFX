package name.timoshenko.communityhelper.server.model.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "faction_allies", uniqueConstraints={
               @UniqueConstraint(columnNames={"first_faction_id", "second_faction_id"})
        })
public class AllyOfFaction implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private final Long id;

    @Column(name = "first_faction_id", nullable = false)
    /**
     * инициатор. Нужно подтверждение от secondFactionId. Появится ответная запись с обратными factionId.
     */
    private Long firstFactionId;


    @Column(name = "second_faction_id", nullable = false)
    /**
     * "принимающая" сторона
     */
    private Long secondFactionId;

    @Column(name = "note")
    /**
     * Заметка для firstFactionId. Заметка для обратной стороны будет в ответной записи.
     */
    private String note;

    /*TODO важно!!! Реализовать через отдельную таблицу?
    Сделать связь многие ко многим. Отдельно описание союзника (данный entity), а отдельно связи:
    Здесь должно быть только описание связи, а связь в другой таблице типа Ally.
    Т.е. убрать отсюда firstFactionId и secondFactionId, и брать это из таблицы Ally (id, id из данной табл, initFactionId, answerFactionId)
    И подтверждение сделать через пару first-second + second-first
    Это поле здесь чисто для памяти - оно здесь НЕ нужно!!!
     */

    @Column(name = "ally_type")
    /*TODO Enum
     * Надо переделать на Enum?
     */
    private allyTypeEnum allyType;

    @Column(name = "created_date")
    private Date createdDate;

    public enum allyTypeEnum {NULLY, ALLY, ENEMY}

    public AllyOfFaction() { this(0L, 0L, 0L, "", allyTypeEnum.NULLY, new Date());
    }

    public AllyOfFaction(Long id, Long firstFactionId, Long secondFactionId, String note, allyTypeEnum allyType, Date createdDate) {
        this.id = id;
        this.firstFactionId = firstFactionId;
        this.secondFactionId = secondFactionId;
        this.note = note;
        this.allyType = allyType;
        this.createdDate = createdDate;
    }

    public Long getId() {
        return id;
    }

    public Long getFirstFactionId() {
        return firstFactionId;
    }

    public Long getSecondFactionId() {
        return secondFactionId;
    }

    public String getNote() {
        return note;
    }

    public Enum getAllyType() {
        return allyType;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    @Override
    public String toString() {
        return "AllyOfFaction{" +
                "id=" + id +
                ", firstFactionId=" + firstFactionId +
                ", secondFactionId=" + secondFactionId +
                ", firstFactionNotation='" + note + '\'' +
                ", allyType=" + allyType +
                ", createdDate=" + createdDate +
                '}';
    }
}
