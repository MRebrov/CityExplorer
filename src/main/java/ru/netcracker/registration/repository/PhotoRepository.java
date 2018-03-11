package ru.netcracker.registration.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.netcracker.registration.model.Photo;

@Repository
public interface PhotoRepository extends CrudRepository<Photo, Integer>{
    Photo findByUrl(String url);
}
