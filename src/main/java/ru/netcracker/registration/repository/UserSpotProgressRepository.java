package ru.netcracker.registration.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ru.netcracker.registration.model.User;
import ru.netcracker.registration.model.UserSpotProgress;

import java.util.List;

public interface UserSpotProgressRepository extends CrudRepository<UserSpotProgress, Long>{
    @Query(
            "select usp " +
                    "from UserSpotProgress usp " +
                        "join usp.spotsInQuestsBySpotInQuestId siq " +
                        "join siq.quest q " +
                    "where q.ownerId = :#{#owner} and " +
                    "usp.spotStatus = 'Unconfirmed'"
    )
    public List<UserSpotProgress> getAllUnconfirmedByQuestOwner(@Param("owner") User owner);
}
