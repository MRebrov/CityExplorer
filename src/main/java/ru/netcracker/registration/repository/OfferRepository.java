package ru.netcracker.registration.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.netcracker.registration.model.Offer;
import ru.netcracker.registration.model.OfferCategory;
import ru.netcracker.registration.model.User;

import java.util.List;

@Repository
public interface OfferRepository extends CrudRepository<Offer, Long>{
    List<Offer> findAllByCategory(OfferCategory category);
    List<Offer> findAllByOwner(User owner);
}
