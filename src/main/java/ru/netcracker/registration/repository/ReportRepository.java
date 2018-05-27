package ru.netcracker.registration.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.netcracker.registration.model.Quest;
import ru.netcracker.registration.model.Report;
import ru.netcracker.registration.model.User;

import java.util.List;

@Repository
public interface ReportRepository extends CrudRepository<Report, Integer> {
    void deleteAllByQuestId(Quest questId);
    Report findByQuestIdAndUserId(Quest questId, User userId);
}
