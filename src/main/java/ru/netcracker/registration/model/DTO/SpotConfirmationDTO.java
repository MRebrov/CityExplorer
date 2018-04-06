package ru.netcracker.registration.model.DTO;

import java.sql.Date;

public class SpotConfirmationDTO {
    private long userSpotProgressId;
    private String photoURL;
    private String mainPhotoURL;
    private Date uploadDate;
    private String questName;
    private String spotName;

    public long getUserSpotProgressId() {
        return userSpotProgressId;
    }

    public void setUserSpotProgressId(long userSpotProgressId) {
        this.userSpotProgressId = userSpotProgressId;
    }

    public String getPhotoURL() {
        return photoURL;
    }

    public void setPhotoURL(String photoURL) {
        this.photoURL = photoURL;
    }

    public String getMainPhotoURL() {
        return mainPhotoURL;
    }

    public void setMainPhotoURL(String mainPhotoURL) {
        this.mainPhotoURL = mainPhotoURL;
    }

    public Date getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(Date uploadDate) {
        this.uploadDate = uploadDate;
    }

    public String getQuestName() {
        return questName;
    }

    public void setQuestName(String questName) {
        this.questName = questName;
    }

    public String getSpotName() {
        return spotName;
    }

    public void setSpotName(String spotName) {
        this.spotName = spotName;
    }
}
