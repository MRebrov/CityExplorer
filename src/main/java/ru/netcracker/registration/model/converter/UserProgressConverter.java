package ru.netcracker.registration.model.converter;

import ru.netcracker.registration.model.DTO.UserProgressDTO;
import ru.netcracker.registration.model.DTO.UserSpotProgressDTO;
import ru.netcracker.registration.model.UserProgress;
import ru.netcracker.registration.model.UserSpotProgress;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс для преобразования Entity пользователя в DTO пользователя
 */
public class UserProgressConverter {
    public static UserProgressDTO convertToDTO(UserProgress userProgress) {
        UserProgressDTO dto = new UserProgressDTO();


        dto.setUser(UserConverter.convertToDTO(userProgress.getUserByUserId()));
        dto.setQuest(QuestConverter.convertToDTO(userProgress.getQuestByQuestId()));
        dto.setTakingDate(userProgress.getTakingDate());
        dto.setDateComplete(userProgress.getDateComplete());
        List<UserSpotProgressDTO> userSpotProgressList = new ArrayList<>();
        for(UserSpotProgress userSpotProgress: userProgress.getUserSpotProgressesByUserProgressId()){
            userSpotProgressList.add(UserSpotProgressConverter.convertToDTO(userSpotProgress));
        }
        dto.setUserSpotProgresses(userSpotProgressList);
        return dto;
    }
}
