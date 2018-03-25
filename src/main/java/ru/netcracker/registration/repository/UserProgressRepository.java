package ru.netcracker.registration.repository;

import org.springframework.data.repository.CrudRepository;
import ru.netcracker.registration.model.User;
import ru.netcracker.registration.model.UserProgress;

import java.util.List;

public interface UserProgressRepository extends CrudRepository<UserProgress, Long>{
    List<UserProgress> findAllByUserByUserId(User user);
}
