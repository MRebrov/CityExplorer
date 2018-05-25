package ru.netcracker.registration.service.impl;

import ru.netcracker.registration.model.DTO.OfferCategoryDTO;
import ru.netcracker.registration.model.DTO.OfferDTO;
import ru.netcracker.registration.model.DTO.UserDTO;
import ru.netcracker.registration.model.DTO.UserOfferDTO;
import ru.netcracker.registration.service.OfferService;

public class OfferServiceImpl implements OfferService{
    @Override
    public Iterable<OfferDTO> getOffers(int amount, int portion) {
        return null;
    }

    @Override
    public Iterable<OfferDTO> getOffersByCategory(OfferCategoryDTO category, int amount, int portion) {
        return null;
    }

    @Override
    public Iterable<OfferDTO> getOffersByOwner(UserDTO owner, int amount, int portion) {
        return null;
    }

    @Override
    public Iterable<UserOfferDTO> getUserOffers(UserDTO owner) {
        return null;
    }

    @Override
    public Iterable<OfferCategoryDTO> getCategories() {
        return null;
    }

    @Override
    public void saveOffer(OfferDTO offerDTO, String ownerEmail) {

    }
}
