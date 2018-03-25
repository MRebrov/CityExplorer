package ru.netcracker.registration.model.converter;

import ru.netcracker.registration.model.DTO.PhotoDTO;
import ru.netcracker.registration.model.DTO.UserProgressDTO;
import ru.netcracker.registration.model.Photo;
import ru.netcracker.registration.model.UserProgress;

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
        return dto;
    }
}
