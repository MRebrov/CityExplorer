package ru.netcracker.registration.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.netcracker.registration.model.PhotoType;
import ru.netcracker.registration.repository.PhotoTypeRepository;
import ru.netcracker.registration.service.PhotoTypeService;

import java.util.List;

@Service("PhotoTypeService")
@Transactional
public class PhotoTypeServiceImpl implements PhotoTypeService {

    @Autowired
    PhotoTypeRepository photoTypeRepository;

    @Override
    public PhotoType getById(Integer id) {
        return photoTypeRepository.findOne(id);
    }

    @Override
    public PhotoType getByType(String name) {
        return photoTypeRepository.findByName(name);
    }

    @Override
    public List<PhotoType> getAll() {
        return (List<PhotoType>) photoTypeRepository.findAll();
    }

    @Override
    public boolean delete(Integer id) {
        photoTypeRepository.delete(id);
        return true;
    }

    @Override
    public boolean delete(PhotoType photoType) {
        photoTypeRepository.delete(photoType);
        return true;
    }

    @Override
    public PhotoType save(PhotoType photoType) {
        return photoTypeRepository.save(photoType);
    }
}
