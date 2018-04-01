package ru.netcracker.registration.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.netcracker.registration.model.*;
import ru.netcracker.registration.model.DTO.QuestDTO;
import ru.netcracker.registration.model.DTO.SpotDTO;
import ru.netcracker.registration.model.DTO.UserProgressDTO;
import ru.netcracker.registration.model.converter.QuestConverter;
import ru.netcracker.registration.model.converter.UserProgressConverter;
import ru.netcracker.registration.repository.PhotoTypeRepository;
import ru.netcracker.registration.repository.QuestRepository;
import ru.netcracker.registration.repository.UserProgressRepository;
import ru.netcracker.registration.repository.UserRepository;
import ru.netcracker.registration.service.QuestService;
import ru.netcracker.registration.service.SpotService;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Service("QuestService")
public class QuestServiceImpl implements QuestService {

    @Autowired
    QuestRepository questRepository;

    @Autowired
    SpotService spotService;

    @Autowired
    UserProgressRepository userProgressRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PhotoTypeRepository photoTypeRepository;

    @Override
    public QuestDTO getById(Long id) {
        return QuestConverter.convertToDTO(questRepository.findOne(id));
    }

    @Override
    public List<Quest> getByName(String name) {
        return questRepository.findAllByName(name);
    }

    @Override
    public QuestDTO getOneByName(String name) {
        Quest quest = getOneByNameNotDTO(name);
        if(quest != null)
            return QuestConverter.convertToDTO(quest);
        return null;
    }


    private Quest getOneByNameNotDTO(String name) {
        List<Quest> quests = questRepository.findAllByName(name);
        if(quests != null && quests.size()>0) {
            return quests.get(0);
        }
        return null;
    }

    @Override
    public List<Quest> getAll() {
        return (List<Quest>) questRepository.findAll();
    }

    @Override
    public boolean delete(Long id) {
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
        //spot.setLng(Double.valueOf(questDTO.getLng()));
        //spot.setLat(Double.valueOf(questDTO.getLat()));
        spot.setName(questDTO.getName());
        spot.setUploadDate(questDTO.getUploadDate());
        photo.setSpotBySpotId(spot);
        spotInQuest.setQuest(quest);
        spotInQuest.setSpotBySpotId(spot);
        spotInQuest.setPhotoByPhotoId(photo);
        quest.getSpotInQuests().add(spotInQuest);
        quest.setOwnerId(user);
        questRepository.save(quest);
    }

    @Override
    public void save(QuestDTO questDTO, User user) {
        Quest quest = new Quest();
        quest.setName(questDTO.getName());
        quest.setDescription(questDTO.getDescription());
        quest.setReward(questDTO.getReward());
        quest.setUploadDate(questDTO.getUploadDate());
        quest.setOwnerId(user);
        for(SpotDTO spotDTO: questDTO.getSpots()){
            Spot spot = new Spot();
            SpotInQuest spotInQuest = new SpotInQuest();
            spot.setUploadDate(questDTO.getUploadDate());
            spot.setName(spotDTO.getName());
            spot.setLat(spotDTO.getLat());
            spot.setLng(spotDTO.getLng());
            Photo photo = new Photo();
            photo.setUrl(spotDTO.getPhotos().stream().findFirst().get().getUrl());
            photo.setUploadDate(questDTO.getUploadDate());
            photo.setUser(user);
            photo.setSpotBySpotId(spot);
            photo.setPhotoTypeByTypeId(photoTypeRepository.findByName("spot"));
            spot.getPhotoBySpotId().add(photo);
            spotInQuest.setQuest(quest);
            spotInQuest.setSpotBySpotId(spot);
            spotInQuest.setPhotoByPhotoId(spot.getPhotoBySpotId().stream().findFirst().get());
            quest.getSpotInQuests().add(spotInQuest);
        }
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

    @Override
    public List<UserProgressDTO> getUserProgressByUser(String email) {
        User user = userRepository.findByEmail(email);
        List<UserProgress> userProgressList = userProgressRepository.findAllByUserByUserId(user);
        List<UserProgressDTO> userProgressDTOList = new ArrayList<>();
        for(UserProgress userProgress:userProgressList) {
            userProgressDTOList.add(UserProgressConverter.convertToDTO(userProgress));
        }
        return userProgressDTOList;
    }

    @Override
    public UserProgressDTO getUserProgressByUserAndQuest(String email, Long questId) {
        User user = userRepository.findByEmail(email);
        Quest quest = questRepository.findOne(questId);
        if(quest!= null) {
            UserProgress userProgress = userProgressRepository.findByUserByUserIdAndAndQuestByQuestId(user, quest);
            return UserProgressConverter.convertToDTO(userProgress);
        }
        return null;
    }

    @Override
    public void userJoinQuest(String email, Long questId){
        UserProgress progress = new UserProgress();
        progress.setUserByUserId(userRepository.findByEmail(email));
        progress.setQuestByQuestId(questRepository.findOne(questId));
        progress.setTakingDate(new java.sql.Date(Calendar.getInstance().getTime().getTime()));

        userProgressRepository.save(progress);
    }

}