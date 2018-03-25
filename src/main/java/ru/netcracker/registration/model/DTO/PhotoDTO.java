package ru.netcracker.registration.model.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import ru.netcracker.registration.model.PhotoType;
import ru.netcracker.registration.model.Spot;
import ru.netcracker.registration.model.SpotInQuest;
import ru.netcracker.registration.model.User;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;

public class PhotoDTO {

    @JsonProperty("url")
    private String url;

    @JsonProperty("uploadDate")
    private Date uploadDate;

    @JsonProperty("photoType")
    private String photoType;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Date getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(Date uploadDate) {
        this.uploadDate = uploadDate;
    }

    public String getPhotoType() {
        return photoType;
    }

    public void setPhotoType(String photoType) {
        this.photoType = photoType;
    }
}
