package ru.netcracker.registration.model.DTO;

import ru.netcracker.registration.model.Photo;
import ru.netcracker.registration.model.SpotInQuest;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;

public class SpotDTO {
    private String name;
    private Date uploadDate;
    private Double lat;
    private Double lng;
    private Collection<PhotoDTO> photos = new ArrayList<>();
    private PhotoDTO mainPhoto;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(Date uploadDate) {
        this.uploadDate = uploadDate;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    public Collection<PhotoDTO> getPhotos() {
        return photos;
    }

    public void setPhotos(Collection<PhotoDTO> photos) {
        this.photos = photos;
    }

    public PhotoDTO getMainPhoto() {
        return mainPhoto;
    }

    public void setMainPhoto(PhotoDTO mainPhoto) {
        this.mainPhoto = mainPhoto;
    }
}
