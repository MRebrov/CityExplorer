package ru.netcracker.registration.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.netcracker.registration.model.Offer;
import ru.netcracker.registration.model.User;
import ru.netcracker.registration.model.UserOffer;

import java.util.List;

@Repository
public interface UserOfferRepository extends CrudRepository<UserOffer, Long> {
    List<UserOffer> findAllByUser(User user);

    UserOffer findUserOfferByUserAndOffer(User user, Offer offer);

    List<UserOffer> findAllByOffer(Offer offer);
}
