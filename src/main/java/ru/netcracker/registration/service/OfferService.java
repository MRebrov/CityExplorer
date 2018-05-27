package ru.netcracker.registration.service;

import ru.netcracker.registration.model.DTO.OfferCategoryDTO;
import ru.netcracker.registration.model.DTO.OfferDTO;
import ru.netcracker.registration.model.DTO.UserDTO;
import ru.netcracker.registration.model.DTO.UserOfferDTO;

import java.util.List;

public interface OfferService {
    List<OfferDTO> getOffers(int amount, int portion);
    List<OfferDTO> getOffersByCategory(Long categoryId, int amount, int portion);
    List<UserOfferDTO> getUserOffers(String email);
    List<OfferDTO> getOwnedOffers(String email);
    List<OfferCategoryDTO> getCategories();
    void purchaseOffer(Long offerId, String email) throws Exception;
    void saveOffer(OfferDTO offerDTO, String ownerEmail) throws Exception;
}
