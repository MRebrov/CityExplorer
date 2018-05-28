package ru.netcracker.registration.model;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Table(name= "quest",schema = "nc_project")
public class Quest {
    private Long questId;
    private String name;
    private String description;
    private Date uploadDate;
    private Integer reward;
    private Collection<SpotInQuest> spotInQuests = new ArrayList<>();
    private User ownerId;
    private Integer numberOfParticipants;
    private Integer numberOfJoiners;
    private Integer status;
    private Integer reports;
    private Collection<Report> reportCollection;


    @OneToMany(mappedBy = "questId", cascade = CascadeType.PERSIST)
    public Collection<Report> getReportCollection() {
        return reportCollection;
    }

    public void setReportCollection(Collection<Report> reportCollection) {
        this.reportCollection = reportCollection;
    }

    @Basic
    @Column(name = "reports")
    public Integer getReports() {
        return reports;
    }

    public void setReports(Integer reports) {
        this.reports = reports;
    }

    @Basic
    @Column(name = "status")
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Basic
    @Column(name = "number_of_joiners", nullable = false)
    public Integer getNumberOfJoiners() {
        return numberOfJoiners;
    }

    public void setNumberOfJoiners(Integer numberOfJoiners) {
        this.numberOfJoiners = numberOfJoiners;
    }

    @Basic
    @Column(name = "number_of_participants")
    public Integer getNumberOfParticipants() {
        return numberOfParticipants;
    }

    public void setNumberOfParticipants(Integer numberOfParticipants) {
        this.numberOfParticipants = numberOfParticipants;
    }

    @OneToMany(mappedBy = "quest", cascade = CascadeType.ALL)
    public Collection<SpotInQuest> getSpotInQuests() {
        return spotInQuests;
    }

    public void setSpotInQuests(Collection<SpotInQuest> spotInQuests) {
        this.spotInQuests = spotInQuests;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "quest_id")
    public Long getQuestId() {
        return questId;
    }

    public void setQuestId(Long questId) {
        this.questId = questId;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "upload_date")
    public Date getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(Date uploadDate) {
        this.uploadDate = uploadDate;
    }

    @Basic
    @Column(name = "reward")
    public Integer getReward() {
        return reward;
    }

    public void setReward(Integer reward) {
        this.reward = reward;
    }

    @ManyToOne
    @JoinColumn(name = "owner_id", referencedColumnName = "user_id")
    public User getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(User ownerId) {
        this.ownerId = ownerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Quest quest = (Quest) o;

        if (!questId.equals(quest.questId)) return false;
        if (!name.equals(quest.name)) return false;
        if (!description.equals(quest.description)) return false;
        if (!uploadDate.equals(quest.uploadDate)) return false;
        if (!reward.equals(quest.reward)) return false;
        if (!spotInQuests.equals(quest.spotInQuests)) return false;
        if (!ownerId.equals(quest.ownerId)) return false;
        if (!numberOfParticipants.equals(quest.numberOfParticipants)) return false;
        if (!numberOfJoiners.equals(quest.numberOfJoiners)) return false;
        if (!status.equals(quest.status)) return false;
        if (!reports.equals(quest.reports)) return false;
        return reportCollection.equals(quest.reportCollection);
    }

    @Override
    public int hashCode() {
        int result = questId.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + description.hashCode();
        result = 31 * result + uploadDate.hashCode();
        result = 31 * result + reward.hashCode();
        result = 31 * result + spotInQuests.hashCode();
        result = 31 * result + ownerId.hashCode();
        result = 31 * result + numberOfParticipants.hashCode();
        result = 31 * result + numberOfJoiners.hashCode();
        result = 31 * result + status.hashCode();
        result = 31 * result + reports.hashCode();
        result = 31 * result + reportCollection.hashCode();
        return result;
    }
}
