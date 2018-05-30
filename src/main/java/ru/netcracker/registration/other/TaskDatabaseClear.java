package ru.netcracker.registration.other;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.netcracker.registration.service.QuestService;

public class TaskDatabaseClear implements Runnable {
    private static final Logger logger = LoggerFactory.getLogger(TaskDatabaseClear.class);

    private QuestService questService;

    public TaskDatabaseClear(QuestService questService) {
        this.questService = questService;
    }

    @Override
    public void run() {
        logger.info("Deleting banned and closed quest started");
        questService.deleteOdd();
        logger.info("Deleting banned and closed quest ended");
    }
}
