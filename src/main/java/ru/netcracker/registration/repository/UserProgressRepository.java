package ru.netcracker.registration.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import ru.netcracker.registration.model.Quest;
import ru.netcracker.registration.model.User;
import ru.netcracker.registration.model.UserProgress;

import java.util.List;

@Repository
public interface UserProgressRepository extends CrudRepository<UserProgress, Long>{
    List<UserProgress> findAllByUserByUserId(User user);
    UserProgress findByUserByUserIdAndAndQuestByQuestId(User user, Quest quest);
}
