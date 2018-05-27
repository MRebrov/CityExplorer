package ru.netcracker.registration.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import ru.netcracker.registration.model.Offer;
import ru.netcracker.registration.model.OfferCategory;
import ru.netcracker.registration.model.User;

import java.util.List;

@Repository
public interface OfferRepository extends CrudRepository<Offer, Long>{
    Page<Offer> findAllByOrderByExpireDateDesc(Pageable pageable);
    Page<Offer> findAllByCategoryOrderByExpireDateDesc(OfferCategory category, Pageable pageable);
    List<Offer> findAllByOwner(User owner);
}
