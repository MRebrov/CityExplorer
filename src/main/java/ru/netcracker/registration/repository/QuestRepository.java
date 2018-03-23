package ru.netcracker.registration.repository;

//import jdk.internal.dynalink.linker.LinkerServices;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.netcracker.registration.model.Quest;

import java.util.List;

@Repository
public interface QuestRepository extends CrudRepository<Quest, Integer> {
    List<Quest> findAllByName(String name);
}
