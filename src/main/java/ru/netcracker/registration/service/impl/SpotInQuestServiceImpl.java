package ru.netcracker.registration.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.netcracker.registration.model.SpotInQuest;
import ru.netcracker.registration.repository.SpotInQuestRepository;
import ru.netcracker.registration.repository.SpotRepository;
import ru.netcracker.registration.service.SpotInQuestService;

import java.util.List;


@Service("SpotInQuestService")
public class SpotInQuestServiceImpl implements SpotInQuestService{

    @Autowired
    SpotInQuestRepository spotInQuestRepository;

    @Override
    public SpotInQuest getById(Integer id) {
        return spotInQuestRepository.findOne(id);
    }

    @Override
    public List<SpotInQuest> getAll() {
        return (List<SpotInQuest>) spotInQuestRepository.findAll();
    }

    @Override
    public boolean delete(Integer id) {
        spotInQuestRepository.delete(id);
        return true;
    }

    @Override
    public boolean delete(SpotInQuest spotInQuest) {
        spotInQuestRepository.delete(spotInQuest);
        return true;
    }

    @Override
    public SpotInQuest save(SpotInQuest spotInQuest) {
        return spotInQuestRepository.save(spotInQuest);
    }
}
