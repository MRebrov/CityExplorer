package ru.netcracker.registration.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import ru.netcracker.registration.other.ScheduledTasks;

@Configuration
@ComponentScan(basePackages = "ru.netcracker.registration.other", basePackageClasses = {ScheduledTasks.class})
@EnableScheduling
public class TaskSchedulerConfig {
    @Bean
    public TaskScheduler taskScheduler(){
        return new ThreadPoolTaskScheduler();
    }


}
