package ru.netcracker.registration.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import ru.netcracker.registration.model.*;
import ru.netcracker.registration.model.DTO.QuestDTO;
import ru.netcracker.registration.model.DTO.SpotConfirmationDTO;
import ru.netcracker.registration.model.DTO.SpotDTO;
import ru.netcracker.registration.model.DTO.UserProgressDTO;
import ru.netcracker.registration.model.converter.QuestConverter;
import ru.netcracker.registration.model.converter.UserProgressConverter;
import ru.netcracker.registration.repository.*;
import ru.netcracker.registration.service.QuestService;
import ru.netcracker.registration.service.SpotInQuestService;
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
    UserProgressRepository userProgressRepository;
    @Autowired
    UserSpotProgressRepository userSpotProgressRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    PhotoRepository photoRepository;
    @Autowired
    PhotoTypeRepository photoTypeRepository;

    @Autowired
    SpotInQuestService spotInQuestService;
    @Autowired
    SpotService spotService;

    @Autowired
    SimpMessagingTemplate messagingTemplate;

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
        if (quest != null)
            return QuestConverter.convertToDTO(quest);
        return null;
    }


    private Quest getOneByNameNotDTO(String name) {
        List<Quest> quests = questRepository.findAllByName(name);
        if (quests != null && quests.size() > 0) {
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
        quest.setNumberOfParticipants(questDTO.getNumberOfParticipants());
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
        //user.setBalance(user.getBalance()-quest.getReward()*quest.getNumberOfParticipants());
        questRepository.save(quest);
    }

    @Override
    public void save(QuestDTO questDTO, User user) {
        Quest quest = new Quest();
        quest.setName(questDTO.getName());
        quest.setDescription(questDTO.getDescription());
        quest.setReward(questDTO.getReward());
        quest.setUploadDate(questDTO.getUploadDate());
        quest.setNumberOfParticipants(questDTO.getNumberOfParticipants());
        quest.setOwnerId(user);
        for (SpotDTO spotDTO : questDTO.getSpots()) {
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
        //user.setBalance(user.getBalance()-quest.getReward()*quest.getNumberOfParticipants()*quest.getSpotInQuests().size());
        questRepository.save(quest);
    }

    @Override
    public List<QuestDTO> getAllToDTO() {
        List<QuestDTO> res = new ArrayList<>();
        for (Quest q : questRepository.findAll()) {
            res.add(QuestConverter.convertToDTO(q));
        }
        return res;
    }

    @Override
    public List<QuestDTO> getAllInRange(double lat, double lng, double range) {
        List<Quest> quests = getAll();
        List<QuestDTO> res = new ArrayList<>();
        for (Quest quest : quests) {
            Spot spot = quest.getSpotInQuests().stream().findFirst().get().getSpotBySpotId();
            if (spotService.distFrom(lat, lng, spot) <= range)
                res.add(QuestConverter.convertToDTO(quest));
        }
        return res;
    }

    @Override
    public List<UserProgressDTO> getUserProgressByUser(String email) {
        User user = userRepository.findByEmail(email);
        List<UserProgress> userProgressList = userProgressRepository.findAllByUserByUserId(user);
        List<UserProgressDTO> userProgressDTOList = new ArrayList<>();
        for (UserProgress userProgress : userProgressList) {
            userProgressDTOList.add(UserProgressConverter.convertToDTO(userProgress));
        }
        return userProgressDTOList;
    }

    @Override
    public UserProgressDTO getUserProgressByUserAndQuest(String email, Long questId) {
        User user = userRepository.findByEmail(email);
        Quest quest = questRepository.findOne(questId);
        if (quest != null) {
            UserProgress userProgress = userProgressRepository.findByUserByUserIdAndAndQuestByQuestId(user, quest);
            return UserProgressConverter.convertToDTO(userProgress);
        }
        return null;
    }

    @Override
    public void userJoinQuest(String email, Long questId) {
        UserProgress progress = new UserProgress();
        progress.setUserByUserId(userRepository.findByEmail(email));
        progress.setQuestByQuestId(questRepository.findOne(questId));
        progress.setTakingDate(new java.sql.Date(Calendar.getInstance().getTime().getTime()));

        userProgressRepository.save(progress);
    }

    @Override
    public void userCompleteSpot(String email, Long questId, Long spotId) {
        SpotInQuest spotInQuest = spotInQuestService.getBySpotAndQuest(spotId, questId);
        User user = userRepository.findByEmail(email);
        Quest quest = questRepository.findOne(questId);
        UserProgress userProgress = userProgressRepository.findByUserByUserIdAndAndQuestByQuestId(user, quest);

        Date currentDate = new java.sql.Date(Calendar.getInstance().getTime().getTime());
        UserSpotProgress userSpotProgress = new UserSpotProgress();
        userSpotProgress.setDateComplete(currentDate);
        userSpotProgress.setSpotsInQuestsBySpotInQuestId(spotInQuest);
        userSpotProgress.setSpotStatus("Unconfirmed");
        userSpotProgress.setUserProgressByUserProgressId(userProgress);

        userSpotProgressRepository.save(userSpotProgress);

        messagingTemplate.convertAndSendToUser(
                quest.getOwnerId().getEmail(),
                "/confirmation",
                "Somebody has complete spot in your quest. Check confirmations page"
        );
    }

    public List<SpotConfirmationDTO> getSpotConfirmationsForOwner(String email) {
        User owner = userRepository.findByEmail(email);
        List<UserSpotProgress> progresses = userSpotProgressRepository.getAllUnconfirmedByQuestOwner(owner);
        List<SpotConfirmationDTO> confirmationDTOS = new ArrayList<>();
        for (UserSpotProgress progress : progresses) {
            if (!progress.getUserProgressByUserProgressId().getUserByUserId().equals(owner)) {
                SpotConfirmationDTO confirmationDTO = new SpotConfirmationDTO();
                confirmationDTO.setUserSpotProgressId(progress.getId());
                confirmationDTO.setUploadDate(progress.getDateComplete());
                confirmationDTO.setMainPhotoURL(progress.getSpotsInQuestsBySpotInQuestId().getPhotoByPhotoId().getUrl());
                confirmationDTO.setQuestName(progress.getSpotsInQuestsBySpotInQuestId().getQuest().getName());
                confirmationDTO.setSpotName(progress.getSpotsInQuestsBySpotInQuestId().getSpotBySpotId().getName());

                Photo photo = photoRepository.findByUserAndAndSpotBySpotId(progress.getUserProgressByUserProgressId().getUserByUserId(), progress.getSpotsInQuestsBySpotInQuestId().getSpotBySpotId());
                confirmationDTO.setPhotoURL(photo.getUrl());

                confirmationDTOS.add(confirmationDTO);
            }
        }

        return confirmationDTOS;
    }

    public void setConfirmation(String email, Long userSpotProgressId, Boolean confirm) throws Exception {
        UserSpotProgress userSpotProgress = userSpotProgressRepository.findOne(userSpotProgressId);
        User owner = userSpotProgress.getSpotsInQuestsBySpotInQuestId().getQuest().getOwnerId();
        User user = userSpotProgress.getUserProgressByUserProgressId().getUserByUserId();
        Quest quest = userSpotProgress.getUserProgressByUserProgressId().getQuestByQuestId();
        if (!owner.getEmail().equals(email)) {
            throw new Exception("User is not owner of the quest");
        }

        if (confirm) {
            userSpotProgress.setSpotStatus("Confirmed");
            userSpotProgressRepository.save(userSpotProgress);
            //if quest is totally completed and confirmed
            if (isQuestConfirmedAndCompleted(user, quest)) {
                user.setBalance(user.getBalance() + quest.getReward());
                userRepository.save(user);
                Date currentDate = new java.sql.Date(Calendar.getInstance().getTime().getTime());
                UserProgress userProgress = userSpotProgress.getUserProgressByUserProgressId();
                userProgress.setDateComplete(currentDate);
                userProgressRepository.save(userProgress);
            }
        } else {
            Photo photo = photoRepository.findByUserAndAndSpotBySpotId(user, userSpotProgress.getSpotsInQuestsBySpotInQuestId().getSpotBySpotId());
            userSpotProgressRepository.delete(userSpotProgress);
            photoRepository.delete(photo);
        }
    }

    public Long questCostCalculation(Quest quest) {
        int k = quest.getSpotInQuests().size();
        int n = 1;
        switch(k) {
            case 2: n = 2;
            break;
            case 3: n = 4;
            break;
            case 4: n = 7;
            break;
            case 5: n = 11;
            break;
        }
        return Long.valueOf(quest.getNumberOfParticipants()*quest.getReward()*n);
    }

    private boolean isQuestConfirmedAndCompleted(User user, Quest quest) {
        UserProgress userProgress = userProgressRepository.findByUserByUserIdAndAndQuestByQuestId(user, quest);
        int count = 0;
        if (userProgress.getQuestByQuestId().getSpotInQuests().size() == userProgress.getUserSpotProgressesByUserProgressId().size()) {
            for (UserSpotProgress usp : userProgress.getUserSpotProgressesByUserProgressId()) {
                if (usp.getSpotStatus().equals("Confirmed"))
                    count++;
            }
            if (count == userProgress.getUserSpotProgressesByUserProgressId().size())
                return true;
        }
        return false;
    }

}