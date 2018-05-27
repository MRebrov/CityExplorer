package ru.netcracker.registration.model.converter;

import ru.netcracker.registration.model.DTO.UserOfferDTO;
import ru.netcracker.registration.model.UserOffer;

public class UserOfferConverter {
    public static UserOfferDTO convertToDTO(UserOffer userOffer){
        UserOfferDTO dto=new UserOfferDTO();
        dto.setId(userOffer.getId());
        dto.setOffer(OfferConverter.convertToDTO(userOffer.getOffer()));
        dto.setUser(UserConverter.convertToDTO(userOffer.getUser()));

        return dto;
    }
}
