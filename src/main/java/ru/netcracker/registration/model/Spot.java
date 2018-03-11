package ru.netcracker.registration.model;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Table(name = "spot", schema = "nc_project")
public class Spot {
    private Long spotId;
    private String name;
    private Date uploadDate;
    private Double lat;
    private Double lng;
    private Collection<Photo> photoBySpotId = new ArrayList<>();
    private Collection<SpotInQuest> spotInQuestBySpotId = new ArrayList<>();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "spot_id")
    public Long getSpotId() {
        return spotId;
    }

    public void setSpotId(Long spotId) {
        this.spotId = spotId;
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
    @Column(name = "upload_date")
    public Date getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(Date uploadDate) {
        this.uploadDate = uploadDate;
    }

    @Basic
    @Column(name = "lat")
    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    @Basic
    @Column(name = "lng")
    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Spot spot = (Spot) o;

        if (spotId != null ? !spotId.equals(spot.spotId) : spot.spotId != null) return false;
        if (name != null ? !name.equals(spot.name) : spot.name != null) return false;
        if (uploadDate != null ? !uploadDate.equals(spot.uploadDate) : spot.uploadDate != null) return false;
        if (lat != null ? !lat.equals(spot.lat) : spot.lat != null) return false;
        if (lng != null ? !lng.equals(spot.lng) : spot.lng != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = spotId != null ? spotId.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (uploadDate != null ? uploadDate.hashCode() : 0);
        result = 31 * result + (lat != null ? lat.hashCode() : 0);
        result = 31 * result + (lng != null ? lng.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "spotBySpotId", cascade = CascadeType.PERSIST)
    public Collection<Photo> getPhotoBySpotId() {
        return photoBySpotId;
    }

    public void setPhotoBySpotId(Collection<Photo> photoBySpotId) {
        this.photoBySpotId = photoBySpotId;
    }

    @OneToMany(mappedBy = "spotBySpotId", cascade = CascadeType.PERSIST)
    public Collection<SpotInQuest> getSpotInQuestBySpotId() {
        return spotInQuestBySpotId;
    }

    public void setSpotInQuestBySpotId(Collection<SpotInQuest> spotInQuestBySpotId) {
        this.spotInQuestBySpotId = spotInQuestBySpotId;
    }
}
