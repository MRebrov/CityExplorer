package ru.netcracker.registration.model.converter;

import ru.netcracker.registration.model.DTO.OfferDTO;
import ru.netcracker.registration.model.Offer;

public class OfferConverter {
    public static OfferDTO convertToDTO(Offer offer){
        OfferDTO dto = new OfferDTO();

        dto.setId(offer.getId());
        dto.setAmount(offer.getAmount());
        dto.setCategory(OfferCategoryConverter.convertToDTO(offer.getCategory()));
        dto.setExpireDate(offer.getExpireDate());
        dto.setName(offer.getName());
        dto.setOwnerName(offer.getOwner().getFirstName()+" "+offer.getOwner().getLastName());

        return dto;
    }

    public static Offer convertToEntity(OfferDTO offerDTO){
        Offer entity = new Offer();
        entity.setId(offerDTO.getId());
        entity.setAmount(offerDTO.getAmount());
        entity.setCategory(OfferCategoryConverter.convertToEntity(offerDTO.getCategory()));
        entity.setExpireDate(offerDTO.getExpireDate());
        entity.setName(offerDTO.getName());
        return entity;
    }
}
