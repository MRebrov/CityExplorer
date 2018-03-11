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

    @OneToMany(mappedBy = "quest", cascade = CascadeType.PERSIST)
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Quest quest = (Quest) o;

        if (questId != null ? !questId.equals(quest.questId) : quest.questId != null) return false;
        if (name != null ? !name.equals(quest.name) : quest.name != null) return false;
        if (description != null ? !description.equals(quest.description) : quest.description != null) return false;
        if (uploadDate != null ? !uploadDate.equals(quest.uploadDate) : quest.uploadDate != null) return false;
        if (reward != null ? !reward.equals(quest.reward) : quest.reward != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = questId != null ? questId.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (uploadDate != null ? uploadDate.hashCode() : 0);
        result = 31 * result + (reward != null ? reward.hashCode() : 0);
        return result;
    }
}
