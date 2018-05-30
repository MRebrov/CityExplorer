package ru.netcracker.registration.other;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.netcracker.registration.model.converter.QuestConverter;
import ru.netcracker.registration.service.QuestService;

public class TaskSuspiciousClear implements Runnable {
    private static final Logger logger = LoggerFactory.getLogger(TaskSuspiciousClear.class);

    private QuestService questService;

    public TaskSuspiciousClear(QuestService questService) {
        this.questService = questService;
    }

    @Override
    public void run() {
        logger.info("Deleting suspicious quest started");
        questService.deleteIterable(questService.findSuspicious());
        logger.info("Deleting suspicious quest ended");
    }

}
