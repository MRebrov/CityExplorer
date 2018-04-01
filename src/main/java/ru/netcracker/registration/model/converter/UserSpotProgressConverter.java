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
public class UserSpotProgressConverter {
    public static UserSpotProgressDTO convertToDTO(UserSpotProgress userSpotProgress) {
        UserSpotProgressDTO dto = new UserSpotProgressDTO();

        dto.setDateComplete(userSpotProgress.getDateComplete());
        dto.setSpotStatus(userSpotProgress.getSpotStatus());
        dto.setId(userSpotProgress.getId());
        dto.setSpotId(userSpotProgress.getSpotsInQuestsBySpotInQuestId().getSpotBySpotId().getSpotId());
        return dto;
    }
}
