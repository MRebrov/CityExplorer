package ru.netcracker.registration.other;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.concurrent.ScheduledFuture;

@Component
public class ScheduledTasks {
    private static final Logger logger = LoggerFactory.getLogger(ScheduledTasks.class);

    @Autowired
    TaskScheduler taskScheduler;
    ScheduledFuture task;


    @PostConstruct
    public void scheduleRunnable(){

        task = taskScheduler.schedule(new TaskPrint("map"), new CronTrigger("*/3 * * * * *"));
    }

    public void change(Runnable task, Trigger trigger){
        this.task.cancel(true);
        this.task = taskScheduler.schedule(task, trigger);
    }
    public ScheduledFuture add(Runnable task, Trigger trigger){
        this.task = taskScheduler.schedule(task, trigger);
        return this.task;
    }

//    @Scheduled(fixedRateString = "${schedule.rate.seconds}000")
//    public void task1(){
//        logger.info("Fixed rate = {}", LocalDateTime.now());
//    }
}
