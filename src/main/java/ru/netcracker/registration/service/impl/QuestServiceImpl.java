package ru.netcracker.registration.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.netcracker.registration.model.*;
import ru.netcracker.registration.model.DTO.QuestDTO;
import ru.netcracker.registration.model.converter.QuestConverter;
import ru.netcracker.registration.repository.QuestRepository;
import ru.netcracker.registration.service.QuestService;
import ru.netcracker.registration.service.SpotService;

import java.util.ArrayList;
import java.util.List;

@Service("QuestService")
public class QuestServiceImpl implements QuestService {

    @Autowired
    QuestRepository questRepository;

    @Autowired
    SpotService spotService;

    @Override
    public Quest getById(Integer id) {
        return questRepository.findOne(id);
    }

    @Override
    public List<Quest> getByName(String name) {
        return questRepository.findAllByName(name);
    }

    @Override
    public List<Quest> getAll() {
        return (List<Quest>) questRepository.findAll();
    }

    @Override
    public boolean delete(Integer id) {
        questRepository.delete(id);
        return true;
    }

    @Override
    public boolean delete(Quest quest) {
        questRepository.delete(quest);
        return true;
    }

    @Override
    public Quest save(Quest quest) {
        return questRepository.save(quest);
    }

    @Override
    public void save(QuestDTO questDTO, Photo photo, User user) {
        Quest quest = new Quest();
        Spot spot = new Spot();
        SpotInQuest spotInQuest = new SpotInQuest();
        quest.setUploadDate(questDTO.getUploadDate());
        quest.setReward(questDTO.getReward());
        quest.setDescription(questDTO.getDescription());
        quest.setName(questDTO.getName());
        spot.getPhotoBySpotId().add(photo);
        spot.setLng(Double.valueOf(questDTO.getLng()));
        spot.setLat(Double.valueOf(questDTO.getLat()));
        spot.setName(questDTO.getName());
        spot.setUploadDate(questDTO.getUploadDate());
        photo.setSpotBySpotId(spot);
        spotInQuest.setQuest(quest);
        spotInQuest.setSpotBySpotId(spot);
        spotInQuest.setPhotoByPhotoId(photo);
        quest.getSpotInQuests().add(spotInQuest);
        questRepository.save(quest);

    }

    @Override
    public List<QuestDTO> getAllToDTO() {
        List<QuestDTO> res = new ArrayList<>();
        for (Quest q: questRepository.findAll()){
            res.add(QuestConverter.convertToDTO(q));
        }
        return res;
    }

    @Override
    public List<QuestDTO> getAllInRange(double lat, double lng, double range) {
        List<Quest> quests = getAll();
        List<QuestDTO> res=new ArrayList<>();
        for(Quest quest: quests){
            Spot spot = quest.getSpotInQuests().stream().findFirst().get().getSpotBySpotId();
            if(spotService.distFrom(lat, lng, spot)<=range)
                res.add(QuestConverter.convertToDTO(quest));
        }
        return res;
    }

}