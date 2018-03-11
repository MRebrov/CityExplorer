package ru.netcracker.registration.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.netcracker.registration.model.PhotoType;

import javax.persistence.criteria.CriteriaBuilder;

@Repository
public interface PhotoTypeRepository extends CrudRepository<PhotoType, Integer> {
    PhotoType findByName(String name);
}
