package ru.netcracker.registration.model.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Date;
import java.util.List;

public class QuestDTO {
    @JsonProperty("questId")
    private Long questId;
    @JsonProperty("name")
    private String name;
    @JsonProperty("description")
    private String description;
    @JsonProperty("uploadDate")
    private Date uploadDate;
    @JsonProperty("reward")
    private Integer reward;
    @JsonProperty("numberOfParticipants")
    private Integer numberOfParticipants;
    @JsonProperty("photoURL")
    private String photoURL;
    @JsonProperty("spots")
    private List<SpotDTO> spots;
    @JsonProperty("ownerEmail")
    private String ownerEmail;
    @JsonProperty("numberOfJoiners")
    private Integer numberOfJoiners;
    @JsonProperty("status")
    private Integer status;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getNumberOfJoiners() {
        return numberOfJoiners;
    }

    public void setNumberOfJoiners(Integer numberOfJoiners) {
        this.numberOfJoiners = numberOfJoiners;
    }

    public Long getQuestId() {
        return questId;
    }

    public void setQuestId(Long questId) {
        this.questId = questId;
    }

    public String getPhotoURL() {
        return photoURL;
    }

    public void setPhotoURL(String photoURL) {
        this.photoURL = photoURL;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(Date uploadDate) {
        this.uploadDate = uploadDate;
    }

    public Integer getReward() {
        return reward;
    }

    public void setReward(Integer reward) {
        this.reward = reward;
    }

    public List<SpotDTO> getSpots() {
        return spots;
    }

    public void setSpots(List<SpotDTO> spots) {
        this.spots = spots;
    }

    public Integer getNumberOfParticipants() {
        return numberOfParticipants;
    }

    public void setNumberOfParticipants(Integer numberOfParticipants) {
        this.numberOfParticipants = numberOfParticipants;
    }
    public String getOwnerEmail() {
        return ownerEmail;
    }

    public void setOwnerEmail(String ownerEmail) {
        this.ownerEmail = ownerEmail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        QuestDTO questDTO = (QuestDTO) o;

        if (!questId.equals(questDTO.questId)) return false;
        if (!name.equals(questDTO.name)) return false;
        if (!description.equals(questDTO.description)) return false;
        if (!uploadDate.equals(questDTO.uploadDate)) return false;
        if (!reward.equals(questDTO.reward)) return false;
        if (!numberOfParticipants.equals(questDTO.numberOfParticipants)) return false;
        if (!photoURL.equals(questDTO.photoURL)) return false;
        if (!spots.equals(questDTO.spots)) return false;
        if (!status.equals(questDTO.status)) return false;
        if (!numberOfJoiners.equals(questDTO.numberOfJoiners)) return false;
        return ownerEmail.equals(questDTO.ownerEmail);
    }

    @Override
    public int hashCode() {
        int result = questId.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + description.hashCode();
        result = 31 * result + uploadDate.hashCode();
        result = 31 * result + reward.hashCode();
        result = 31 * result + numberOfParticipants.hashCode();
        result = 31 * result + photoURL.hashCode();
        result = 31 * result + spots.hashCode();
        result = 31 * result + ownerEmail.hashCode();
        result = 31 * result + numberOfJoiners.hashCode();
        result = 31 * result + status.hashCode();
        return result;
    }
}
