package ru.netcracker.registration.model;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;

@Entity
@Table(name = "photo", schema = "nc_project")
public class Photo {
    private Long photoId;
    private String url;
    private Date uploadDate;
    private Spot spotBySpotId;
    private PhotoType photoTypeByTypeId;
    private Collection<SpotInQuest> spotInQuestByPhotoId;
    private User user;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "photo_id")
    public Long getPhotoId() {
        return photoId;
    }

    public void setPhotoId(Long photoId) {
        this.photoId = photoId;
    }

    @Basic
    @Column(name = "url")
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Basic
    @Column(name = "upload_date")
    public Date getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(Date uploadDate) {
        this.uploadDate = uploadDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Photo photo = (Photo) o;

        if (photoId != null ? !photoId.equals(photo.photoId) : photo.photoId != null) return false;
        if (url != null ? !url.equals(photo.url) : photo.url != null) return false;
        if (uploadDate != null ? !uploadDate.equals(photo.uploadDate) : photo.uploadDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = photoId != null ? photoId.hashCode() : 0;
        result = 31 * result + (url != null ? url.hashCode() : 0);
        result = 31 * result + (uploadDate != null ? uploadDate.hashCode() : 0);
        return result;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "spot_id", referencedColumnName = "spot_id", nullable = false)
    public Spot getSpotBySpotId() {
        return spotBySpotId;
    }

    public void setSpotBySpotId(Spot spotBySpotId) {
        this.spotBySpotId = spotBySpotId;
    }

    @ManyToOne
    @JoinColumn(name = "type_id", referencedColumnName = "photo_type_id", nullable = false)
    public PhotoType getPhotoTypeByTypeId() {
        return photoTypeByTypeId;
    }

    public void setPhotoTypeByTypeId(PhotoType photoTypeByTypeId) {
        this.photoTypeByTypeId = photoTypeByTypeId;
    }

    @OneToMany(mappedBy = "photoByPhotoId", cascade = CascadeType.ALL)
    public Collection<SpotInQuest> getSpotInQuestByPhotoId() {
        return spotInQuestByPhotoId;
    }

    public void setSpotInQuestByPhotoId(Collection<SpotInQuest> spotInQuestByPhotoId) {
        this.spotInQuestByPhotoId = spotInQuestByPhotoId;
    }
}
