package ru.netcracker.registration.model.converter;

import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import ru.netcracker.registration.model.DTO.PhotoDTO;
import ru.netcracker.registration.model.DTO.UserDTO;
import ru.netcracker.registration.model.Photo;
import ru.netcracker.registration.model.User;
import ru.netcracker.registration.other.Checker;

/**
 * Класс для преобразования Entity пользователя в DTO пользователя
 */
public class PhotoConverter {
    public static PhotoDTO convertToDTO(Photo photo) {
        PhotoDTO dto = new PhotoDTO();

        dto.setUrl(photo.getUrl());
        dto.setPhotoType(photo.getPhotoTypeByTypeId().getName());
        dto.setUploadDate(photo.getUploadDate());
        return dto;
    }
}
