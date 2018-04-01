package ru.netcracker.registration.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.netcracker.registration.model.Quest;
import ru.netcracker.registration.model.SpotInQuest;
import ru.netcracker.registration.model.Spot;
import javax.persistence.criteria.CriteriaBuilder;

@Repository
public interface SpotInQuestRepository extends CrudRepository<SpotInQuest,Integer> {
    SpotInQuest findBySpotBySpotIdAndAndQuest(Spot spot, Quest quest);
}
