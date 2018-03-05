package ru.netcracker.registration.model;

import javax.persistence.*;
import java.sql.Date;

@Entity
public class Quests {
    private Long questId;
    private String name;
    private String description;
    private Date uploadDate;
    private Integer reward;

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

        Quests quests = (Quests) o;

        if (questId != null ? !questId.equals(quests.questId) : quests.questId != null) return false;
        if (name != null ? !name.equals(quests.name) : quests.name != null) return false;
        if (description != null ? !description.equals(quests.description) : quests.description != null) return false;
        if (uploadDate != null ? !uploadDate.equals(quests.uploadDate) : quests.uploadDate != null) return false;
        if (reward != null ? !reward.equals(quests.reward) : quests.reward != null) return false;

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
