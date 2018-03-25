package ru.netcracker.registration.model.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Date;
import java.util.List;

public class QuestDTO {
    @JsonProperty("name")
    private String name;
    @JsonProperty("description")
    private String description;
    @JsonProperty("uploadDate")
    private Date uploadDate;
    @JsonProperty("reward")
    private Integer reward;
    @JsonProperty("photoURL")
    private String photoURL;
    @JsonProperty("spots")
    private List<SpotDTO> spots;


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
}
