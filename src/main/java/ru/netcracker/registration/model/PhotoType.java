package ru.netcracker.registration.model;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "photo_type", schema = "nc_project", catalog = "vrbqprvx")
public class PhotoType {
    private Integer photoTypeId;
    private String name;
    private Collection<Photo> photoByPhotoTypeId;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "photo_type_id")
    public Integer getPhotoTypeId() {
        return photoTypeId;
    }

    public void setPhotoTypeId(Integer photoTypeId) {
        this.photoTypeId = photoTypeId;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PhotoType photoType = (PhotoType) o;

        if (photoTypeId != null ? !photoTypeId.equals(photoType.photoTypeId) : photoType.photoTypeId != null)
            return false;
        if (name != null ? !name.equals(photoType.name) : photoType.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = photoTypeId != null ? photoTypeId.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "photoTypeByTypeId")
    public Collection<Photo> getPhotoByPhotoTypeId() {
        return photoByPhotoTypeId;
    }

    public void setPhotoByPhotoTypeId(Collection<Photo> photoByPhotoTypeId) {
        this.photoByPhotoTypeId = photoByPhotoTypeId;
    }
}
