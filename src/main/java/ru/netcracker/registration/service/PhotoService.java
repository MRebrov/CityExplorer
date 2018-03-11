package ru.netcracker.registration.service;

import ru.netcracker.registration.model.Photo;
import ru.netcracker.registration.model.PhotoType;

import java.util.List;

public interface PhotoService {
    Photo getById(Integer id);
    Photo getByUrl(String url);
    List<Photo> getAll();
    boolean delete(Integer id);
    boolean delete(Photo photo);
    Photo save(Photo photo);
}
