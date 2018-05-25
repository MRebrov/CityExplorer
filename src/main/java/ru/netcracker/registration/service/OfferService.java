package ru.netcracker.registration.service;

import ru.netcracker.registration.model.DTO.OfferCategoryDTO;
import ru.netcracker.registration.model.DTO.OfferDTO;
import ru.netcracker.registration.model.DTO.UserDTO;
import ru.netcracker.registration.model.DTO.UserOfferDTO;

public interface OfferService {
    Iterable<OfferDTO> getOffers(int amount, int portion);
    Iterable<OfferDTO> getOffersByCategory(OfferCategoryDTO category, int amount, int portion);
    Iterable<OfferDTO> getOffersByOwner(UserDTO owner, int amount, int portion);
    Iterable<UserOfferDTO> getUserOffers(UserDTO owner);
    Iterable<OfferCategoryDTO> getCategories();
    void saveOffer(OfferDTO offerDTO, String ownerEmail);
}
