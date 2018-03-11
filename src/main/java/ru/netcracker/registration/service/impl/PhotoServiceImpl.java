package ru.netcracker.registration.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.netcracker.registration.model.Photo;
import ru.netcracker.registration.repository.PhotoRepository;
import ru.netcracker.registration.service.PhotoService;

import java.util.List;

@Service("PhotoService")
public class PhotoServiceImpl implements PhotoService {
    @Autowired
    PhotoRepository photoRepository;

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
}
