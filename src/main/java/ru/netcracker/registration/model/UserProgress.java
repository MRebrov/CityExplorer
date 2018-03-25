package ru.netcracker.registration.model;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;

@Entity
@Table(name = "user_progress", schema = "nc_project", catalog = "vrbqprvx")
public class UserProgress {
    private User userByUserId;
    private Quest questByQuestId;
    private Date dateComplete;
    private Date takingDate;
    private Integer userProgressId;
    private Collection<UserSpotProgress> userSpotProgressesByUserProgressId;

    @Basic
    @Column(name = "date_complete")
    public Date getDateComplete() {
        return dateComplete;
    }

    public void setDateComplete(Date dateComplete) {
        this.dateComplete = dateComplete;
    }

    @Basic
    @Column(name = "taking_date")
    public Date getTakingDate() {
        return takingDate;
    }

    public void setTakingDate(Date takingDate) {
        this.takingDate = takingDate;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_progress_id")
    public Integer getUserProgressId() {
        return userProgressId;
    }

    public void setUserProgressId(Integer userProgressId) {
        this.userProgressId = userProgressId;
    }

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
    public User getUserByUserId() {
        return userByUserId;
    }

    public void setUseByUserId(User useByUserId) {
        this.userByUserId = useByUserId;
    }

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "quest_id", referencedColumnName = "quest_id", nullable = false)
    public Quest getQuestByQuestId() {
        return questByQuestId;
    }

    public void setQuestByQuestId(Quest questByQuestId) {
        this.questByQuestId = questByQuestId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserProgress that = (UserProgress) o;

        if (dateComplete != null ? !dateComplete.equals(that.dateComplete) : that.dateComplete != null) return false;
        if (takingDate != null ? !takingDate.equals(that.takingDate) : that.takingDate != null) return false;
        if (userProgressId != null ? !userProgressId.equals(that.userProgressId) : that.userProgressId != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = dateComplete != null ? dateComplete.hashCode() : 0;
        result = 31 * result + (takingDate != null ? takingDate.hashCode() : 0);
        result = 31 * result + (userProgressId != null ? userProgressId.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "userProgressByUserProgressId")
    public Collection<UserSpotProgress> getUserSpotProgressesByUserProgressId() {
        return userSpotProgressesByUserProgressId;
    }

    public void setUserSpotProgressesByUserProgressId(Collection<UserSpotProgress> userSpotProgressesByUserProgressId) {
        this.userSpotProgressesByUserProgressId = userSpotProgressesByUserProgressId;
    }
}
