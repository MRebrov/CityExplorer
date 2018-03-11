package ru.netcracker.registration.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import ru.netcracker.registration.model.Spot;
import ru.netcracker.registration.repository.SpotRepository;
import ru.netcracker.registration.service.SpotService;

import java.util.List;


@Service("SpotService")
public class SpotServiceImpl implements SpotService {

    @Autowired
    SpotRepository spotRepository;

    @Override
    public Spot getById(Integer id) {
        return spotRepository.findOne(id);
    }

    @Override
    public List<Spot> getByName(String name) {
        return spotRepository.findAllByName(name);
    }

    @Override
    public List<Spot> getAll() {
        return (List<Spot>) spotRepository.findAll();
    }

    @Override
    public boolean delete(Integer id) {
        spotRepository.delete(id);
        return true;
    }

    @Override
    public boolean delete(Spot spot) {
        spotRepository.delete(spot);
        return true;
    }

    @Override
    public Spot save(Spot spot) {
        return spotRepository.save(spot);
    }
}
