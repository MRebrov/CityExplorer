package ru.netcracker.registration.other;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.netcracker.registration.service.QuestService;

public class TaskInactiveClear implements Runnable {
    private static final Logger logger = LoggerFactory.getLogger(TaskInactiveClear.class);

    private QuestService questService;

    public TaskInactiveClear(QuestService questService) {
        this.questService = questService;
    }

    @Override
    public void run(){
        logger.info("Deleting inactive quest started");
        questService.deleteIterable(questService.findInactive());
        logger.info("Deleting inactive quest ended");
    }
}
