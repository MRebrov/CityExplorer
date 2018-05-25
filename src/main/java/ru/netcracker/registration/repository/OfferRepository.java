package ru.netcracker.registration.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.netcracker.registration.model.Offer;
import ru.netcracker.registration.model.OfferCategory;
import ru.netcracker.registration.model.User;

@Repository
public interface OfferRepository extends CrudRepository<Offer, Long>{
    Iterable<Offer> findAllByCategory(OfferCategory category);
    Iterable<Offer> findAllByOwner(User owner);
}
