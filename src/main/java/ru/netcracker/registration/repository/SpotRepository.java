package ru.netcracker.registration.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.netcracker.registration.model.Spot;

import java.util.List;

@Repository
public interface SpotRepository extends CrudRepository<Spot, Integer> {
    List<Spot> findAllByName(String name);
}
