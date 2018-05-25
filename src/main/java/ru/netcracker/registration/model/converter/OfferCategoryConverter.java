package ru.netcracker.registration.model.converter;

import ru.netcracker.registration.model.DTO.OfferCategoryDTO;
import ru.netcracker.registration.model.OfferCategory;

public class OfferCategoryConverter {
    public static OfferCategoryDTO convertToDTO(OfferCategory offerCategory){
        OfferCategoryDTO dto=new OfferCategoryDTO();

        dto.setId(offerCategory.getId());
        dto.setName(offerCategory.getName());

        return dto;
    }

    public static OfferCategory convertToEntity(OfferCategoryDTO offerCategoryDTO){
        OfferCategory entity=new OfferCategory();
        entity.setId(offerCategoryDTO.getId());
        entity.setName(offerCategoryDTO.getName());
        return entity;
    }
}
