package ru.netcracker.registration.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.netcracker.registration.model.User;
import ru.netcracker.registration.model.UserOffer;

@Repository
public interface UserOfferRepository extends CrudRepository<UserOffer,Long>{
    Iterable<UserOffer> findAllByUser(User user);
}
