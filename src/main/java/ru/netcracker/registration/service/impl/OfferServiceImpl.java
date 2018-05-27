package ru.netcracker.registration.service.impl;

import org.hibernate.type.ListType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
@Transactional
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
        Page<Offer> offers = offerRepository.findAllByOrderByExpireDateDesc(new PageRequest(portion, amount));
        List<OfferDTO> dtos = new ArrayList<>();
        for (Offer offer : offers) {
            if (isOfferValid(offer))
                dtos.add(OfferConverter.convertToDTO(offer));
        }
        return dtos;
    }

    @Override
    public List<OfferDTO> getOffersByCategory(Long categoryId, int amount, int portion) {
        OfferCategory category = offerCategoryRepository.findOne(categoryId);
        Page<Offer> offers = offerRepository.findAllByCategoryOrderByExpireDateDesc(category, new PageRequest(portion, amount));
        List<OfferDTO> dtos = new ArrayList<>();
        for (Offer offer : offers) {
            if (isOfferValid(offer))
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
        if (isOfferPurchased(user, offer)) {
            throw new Exception("You have already purchased this offer");
        }
        if (offer.getPrice() <= user.getBalance()) {
            UserOffer userOffer = new UserOffer();
            userOffer.setOffer(offer);
            userOffer.setUser(user);
            user.setBalance(user.getBalance() - offer.getPrice());
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

    private boolean isOfferPurchased(User user, Offer offer) {
        UserOffer userOffer = userOfferRepository.findUserOfferByUserAndOffer(user, offer);
        return userOffer != null;
    }

    private boolean isOfferValid(Offer offer) {
        org.joda.time.LocalDate date = org.joda.time.LocalDate.now();
        return offer.getExpireDate().getTime() >= date.toDate().getTime();
    }
}
