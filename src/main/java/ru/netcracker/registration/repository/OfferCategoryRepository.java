package ru.netcracker.registration.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.netcracker.registration.model.OfferCategory;

@Repository
public interface OfferCategoryRepository extends CrudRepository<OfferCategory, Long>{
    OfferCategory findOfferCategoryByName(String name);
}
