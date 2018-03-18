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
    @JsonProperty("lat")
    private String lat;
    @JsonProperty("lng")
    private String lng;
    @JsonProperty("photoURL")
    private String photoURL;

    public String getPhotoURL() {
        return photoURL;
    }

    public void setPhotoURL(String photoURL) {
        this.photoURL = photoURL;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
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
}
