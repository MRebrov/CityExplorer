package ru.netcracker.registration.service.impl;

import org.hibernate.type.ListType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.netcracker.registration.model.DTO.OfferCategoryDTO;
import ru.netcracker.registration.model.DTO.OfferDTO;
import ru.netcracker.registration.model.DTO.UserDTO;
import ru.netcracker.registration.model.DTO.UserOfferDTO;
import ru.netcracker.registration.model.Offer;
import ru.netcracker.registration.model.OfferCategory;
import ru.netcracker.registration.model.User;
import ru.netcracker.registration.model.UserOffer;
import ru.netcracker.registration.model.converter.OfferCategoryConverter;
import ru.netcracker.registration.model.converter.OfferConverter;
import ru.netcracker.registration.model.converter.UserOfferConverter;
import ru.netcracker.registration.repository.OfferCategoryRepository;
import ru.netcracker.registration.repository.OfferRepository;
import ru.netcracker.registration.repository.UserOfferRepository;
import ru.netcracker.registration.repository.UserRepository;
import ru.netcracker.registration.service.OfferService;

import java.util.ArrayList;
import java.util.List;

@Service
public class OfferServiceImpl implements OfferService {
    @Autowired
    OfferRepository offerRepository;
    @Autowired
    OfferCategoryRepository offerCategoryRepository;
    @Autowired
    UserOfferRepository userOfferRepository;
    @Autowired
    UserRepository userRepository;

    public OfferServiceImpl() {

    }

    @Override
    public List<OfferDTO> getOffers(int amount, int portion) {
        Iterable<Offer> offers = offerRepository.findAll();
        List<OfferDTO> dtos = new ArrayList<>();
        int i = 0;
        for (Offer offer : offers) {
            if (i >= portion * amount && i < (portion + 1) * amount) {
                dtos.add(OfferConverter.convertToDTO(offer));
            }
            i++;
        }
        return dtos;
    }

    @Override
    public List<OfferDTO> getOffersByCategory(String categoryName, int amount, int portion) {
        OfferCategory category = offerCategoryRepository.findOfferCategoryByName(categoryName);
        List<Offer> offers = offerRepository.findAllByCategory(category);
        List<OfferDTO> dtos = new ArrayList<>();
        for (Offer offer : offers) {
            dtos.add(OfferConverter.convertToDTO(offer));
        }
        return dtos;
    }

    @Override
    public List<UserOfferDTO> getUserOffers(String email) {
        User user = userRepository.findByEmail(email);
        List<UserOffer> userOffers = userOfferRepository.findAllByUser(user);
        List<UserOfferDTO> dtos = new ArrayList<>();
        for (UserOffer userOffer : userOffers) {
            dtos.add(UserOfferConverter.convertToDTO(userOffer));
        }
        return dtos;
    }

    @Override
    public List<OfferDTO> getOwnedOffers(String email) {
        User user = userRepository.findByEmail(email);
        List<Offer> offers = offerRepository.findAllByOwner(user);
        List<OfferDTO> dtos = new ArrayList<>();
        for (Offer offer : offers) {
            dtos.add(OfferConverter.convertToDTO(offer));
        }
        return dtos;
    }

    @Override
    public List<OfferCategoryDTO> getCategories() {
        Iterable<OfferCategory> categories = offerCategoryRepository.findAll();
        List<OfferCategoryDTO> dtos = new ArrayList<>();
        for (OfferCategory offerCategory : categories) {
            dtos.add(OfferCategoryConverter.convertToDTO(offerCategory));
        }
        return dtos;
    }

    @Override
    public void purchaseOffer(Long offerId, String email) throws Exception {
        User user = userRepository.findByEmail(email);
        Offer offer = offerRepository.findOne(offerId);
        if (offer.getPrice() <= user.getBalance()) {
            UserOffer userOffer = new UserOffer();
            userOffer.setOffer(offer);
            userOffer.setUser(user);
            user.setBalance(user.getBalance()-offer.getPrice());
            userOfferRepository.save(userOffer);
            userRepository.save(user);
        } else throw new Exception("Not enough cash to buy offer");
    }

    @Override
    public void saveOffer(OfferDTO offerDTO, String ownerEmail) throws Exception {
        User user = userRepository.findByEmail(ownerEmail);
        if (offerDTO.getPrice() <= user.getBusinessBalance()) {
            Offer offer = OfferConverter.convertToEntity(offerDTO);
            offer.setOwner(user);
            user.setBusinessBalance(user.getBusinessBalance() - offer.getPrice());
            userRepository.save(user);
            offerRepository.save(offer);
        } else throw new Exception("Not enough business cash to create offer");
    }
}
