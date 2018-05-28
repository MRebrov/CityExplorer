package ru.netcracker.registration.service;


import ru.netcracker.registration.model.DTO.QuestDTO;
import ru.netcracker.registration.model.DTO.SpotConfirmationDTO;
import ru.netcracker.registration.model.DTO.UserProgressDTO;
import ru.netcracker.registration.model.Photo;
import ru.netcracker.registration.model.Quest;
import ru.netcracker.registration.model.User;

import java.util.List;

public interface QuestService {
    QuestDTO getById(Long id);
    List<Quest> getByName(String name);
    QuestDTO getOneByName(String name);
    List<Quest> getAll();
    boolean delete(Long id);
    boolean delete(Quest quest);
    Quest save(Quest quest);
    void save(QuestDTO questDTO, User user);
    List<QuestDTO> getAllToDTO();
    List<QuestDTO> getAllByOwner(String email);
    List<QuestDTO> getAllInRange(double lat, double lng, double range);
    List<UserProgressDTO> getUserProgressByUser(String email);
    UserProgressDTO getUserProgressByUserAndQuest(String email, Long questId);
    void userJoinQuest(String email, Long questId);
    void userCompleteSpot(String email, Long questId, Long spotId);
    List<SpotConfirmationDTO> getSpotConfirmationsForOwner(String email);
    void setConfirmation(String email, Long userSpotProgressId, Boolean confirm) throws Exception;
    QuestDTO getTopQuest();
    Quest getEntityById(Long id);
    void reportQuest(Long id);
    void approve(Long id);
    void ban(Long id);
    List<QuestDTO> getReported(Integer reportCount);
    void userCloseQuest(String email, Long questId);
}
