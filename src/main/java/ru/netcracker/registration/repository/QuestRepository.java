package ru.netcracker.registration.repository;

//import jdk.internal.dynalink.linker.LinkerServices;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.netcracker.registration.model.Quest;
import ru.netcracker.registration.model.User;

import java.util.List;

@Repository
public interface QuestRepository extends CrudRepository<Quest, Long> {
    List<Quest> findAllByName(String name);
    List<Quest> findAllByOwnerId(User owner);
    List<Quest> findAllByReportsGreaterThanEqual(Integer reports);
    List<Quest> findAllByStatusEquals(Integer status);
    void deleteAllByStatusGreaterThanEqual(Integer status);
}
