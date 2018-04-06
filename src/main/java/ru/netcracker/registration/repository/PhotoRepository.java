package ru.netcracker.registration.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.netcracker.registration.model.Photo;
import ru.netcracker.registration.model.User;
import ru.netcracker.registration.model.Spot;

@Repository
public interface PhotoRepository extends CrudRepository<Photo, Integer>{
    Photo findByUrl(String url);

    Photo findByUserAndAndSpotBySpotId(User user, Spot spot);
}
