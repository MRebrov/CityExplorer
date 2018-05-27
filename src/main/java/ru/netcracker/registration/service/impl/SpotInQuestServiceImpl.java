package ru.netcracker.registration.service.impl;

import groovy.transform.TailRecursive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.netcracker.registration.model.Quest;
import ru.netcracker.registration.model.SpotInQuest;
import ru.netcracker.registration.repository.QuestRepository;
import ru.netcracker.registration.repository.SpotInQuestRepository;
import ru.netcracker.registration.repository.SpotRepository;
import ru.netcracker.registration.service.SpotInQuestService;
import ru.netcracker.registration.model.Spot;

import java.util.List;


@Service("SpotInQuestService")
@Transactional
public class SpotInQuestServiceImpl implements SpotInQuestService{

    @Autowired
    SpotInQuestRepository spotInQuestRepository;
    @Autowired
    SpotRepository spotRepository;
    @Autowired
    QuestRepository questRepository;

    @Override
    public SpotInQuest getById(Integer id) {
        return spotInQuestRepository.findOne(id);
    }

    @Override
    public List<SpotInQuest> getAll() {
        return (List<SpotInQuest>) spotInQuestRepository.findAll();
    }

    @Override
    public SpotInQuest getBySpotAndQuest(Long spotId, Long questId){
        Quest quest = questRepository.findOne(questId);
        Spot spot = spotRepository.findOne(spotId);
        return spotInQuestRepository.findBySpotBySpotIdAndAndQuest(spot, quest);
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
