package ru.netcracker.registration.model.converter;

import ru.netcracker.registration.model.DTO.PhotoDTO;
import ru.netcracker.registration.model.DTO.SpotDTO;
import ru.netcracker.registration.model.Photo;
import ru.netcracker.registration.model.Spot;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс для преобразования Entity пользователя в DTO пользователя
 */
public class SpotConverter {
    public static SpotDTO convertToDTO(Spot spot) {
        SpotDTO dto = new SpotDTO();

        dto.setSpotId(spot.getSpotId());
        dto.setLat(spot.getLat());
        dto.setLng(spot.getLng());
        dto.setName(spot.getName());
        dto.setUploadDate(spot.getUploadDate());
        List<PhotoDTO> photos= new ArrayList<>();
        for (Photo photo: spot.getPhotoBySpotId()){
            photos.add(PhotoConverter.convertToDTO(photo));
        }
        dto.setPhotos(photos);

        return dto;
    }


}
