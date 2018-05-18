package ru.netcracker.registration.model.converter;

import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import ru.netcracker.registration.model.DTO.QuestDTO;
import ru.netcracker.registration.model.DTO.SpotDTO;
import ru.netcracker.registration.model.DTO.UserDTO;
import ru.netcracker.registration.model.Quest;
import ru.netcracker.registration.model.SpotInQuest;
import ru.netcracker.registration.model.User;
import ru.netcracker.registration.other.Checker;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс для преобразования Entity пользователя в DTO пользователя
 */
public class QuestConverter {
    public static QuestDTO convertToDTO(Quest quest) {
        QuestDTO dto = new QuestDTO();

        dto.setQuestId(quest.getQuestId());
        dto.setName(quest.getName());
        dto.setDescription(quest.getDescription());
        dto.setOwnerEmail(quest.getOwnerId().getEmail());
        dto.setReward(quest.getReward());
        dto.setNumberOfParticipants(quest.getNumberOfParticipants());
        dto.setUploadDate(quest.getUploadDate());
        dto.setPhotoURL(quest.getSpotInQuests().stream().findFirst().get().getPhotoByPhotoId().getUrl());
        dto.setNumberOfJoiners(quest.getNumberOfJoiners());
        dto.setStatus(quest.getStatus());
        //dto.setLat(quest.getSpotInQuests().stream().findFirst().get().getSpotBySpotId().getLat().toString());
        //dto.setLng(quest.getSpotInQuests().stream().findFirst().get().getSpotBySpotId().getLng().toString());

        SpotDTO tempSpotDTO;
        List<SpotDTO> spots = new ArrayList<>();
        for(SpotInQuest spotInQuest: quest.getSpotInQuests()){
            tempSpotDTO=SpotConverter.convertToDTO(spotInQuest.getSpotBySpotId());
            tempSpotDTO.setMainPhoto(PhotoConverter.convertToDTO(spotInQuest.getPhotoByPhotoId()));
            spots.add(tempSpotDTO);
        }
        dto.setSpots(spots);

        return dto;
    }

}
