package ru.netcracker.registration.model;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "user_spot_progress", schema = "nc_project", catalog = "vrbqprvx")
public class UserSpotProgress {
    private Long id;
    private String spotStatus;
    private Date dateComplete;
    private SpotInQuest spotsInQuestsBySpotInQuestId;
    private UserProgress userProgressByUserProgressId;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "spot_status")
    public String getSpotStatus() {
        return spotStatus;
    }

    public void setSpotStatus(String spotStatus) {
        this.spotStatus = spotStatus;
    }

    @Basic
    @Column(name = "date_complete")
    public Date getDateComplete() {
        return dateComplete;
    }

    public void setDateComplete(Date dateComplete) {
        this.dateComplete = dateComplete;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserSpotProgress that = (UserSpotProgress) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (spotStatus != null ? !spotStatus.equals(that.spotStatus) : that.spotStatus != null) return false;
        if (dateComplete != null ? !dateComplete.equals(that.dateComplete) : that.dateComplete != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (spotStatus != null ? spotStatus.hashCode() : 0);
        result = 31 * result + (dateComplete != null ? dateComplete.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "spots_in_quests_id", referencedColumnName = "spots_in_quests_id", nullable = false)
    public SpotInQuest getSpotsInQuestsBySpotInQuestId() {
        return spotsInQuestsBySpotInQuestId;
    }

    public void setSpotsInQuestsBySpotInQuestId(SpotInQuest spotsInQuestsBySpotInQuestId) {
        this.spotsInQuestsBySpotInQuestId = spotsInQuestsBySpotInQuestId;
    }

    @ManyToOne
    @JoinColumn(name = "user_progress_id", referencedColumnName = "user_progress_id", nullable = false)
    public UserProgress getUserProgressByUserProgressId() {
        return userProgressByUserProgressId;
    }

    public void setUserProgressByUserProgressId(UserProgress userProgressByUserProgressId) {
        this.userProgressByUserProgressId = userProgressByUserProgressId;
    }
}
