package ru.netcracker.registration.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.netcracker.registration.model.Photo;
import ru.netcracker.registration.repository.PhotoRepository;
import ru.netcracker.registration.service.PhotoService;
import ru.netcracker.registration.service.PhotoTypeService;
import ru.netcracker.registration.service.SpotService;

import java.util.Calendar;
import java.util.List;

@Service("PhotoService")
public class PhotoServiceImpl implements PhotoService {
    @Autowired
    PhotoRepository photoRepository;
    @Autowired
    PhotoTypeService photoTypeService;
    @Autowired
    UserService userService;
    @Autowired
    SpotService spotService;

    @Override
    public Photo getById(Integer id) {
        return photoRepository.findOne(id);
    }

    @Override
    public Photo getByUrl(String url) {
        return photoRepository.findByUrl(url);
    }

    @Override
    public List<Photo> getAll() {
        return (List<Photo>) photoRepository.findAll();
    }

    @Override
    public boolean delete(Integer id) {
        photoRepository.delete(id);
        return true;
    }

    @Override
    public boolean delete(Photo photo) {
        photoRepository.delete(photo);
        return true;
    }

    @Override
    public Photo save(Photo photo) {
        return photoRepository.save(photo);
    }

    @Override
    public void save(String email, String url, Long questId, Long spotId) {
        Photo photo = new Photo();
        photo.setUrl(url);
        photo.setPhotoTypeByTypeId(photoTypeService.getByType("spot"));
        photo.setUploadDate(new java.sql.Date(Calendar.getInstance().getTime().getTime()));
        photo.setUser(userService.getByEmail(email));
        photo.setSpotBySpotId(spotService.getById(spotId));

        photoRepository.save(photo);
    }
}
