package ru.netcracker.registration.service;

import ru.netcracker.registration.model.PhotoType;
import ru.netcracker.registration.model.Quest;

import java.util.List;

public interface PhotoTypeService {
    PhotoType getById(Integer id);
    PhotoType getByType(String name);
    List<PhotoType> getAll();
    boolean delete(Integer id);
    boolean delete(PhotoType photoType);
    PhotoType save(PhotoType photoType);
}
