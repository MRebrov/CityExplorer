package ru.netcracker.registration.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.DefaultManagedTaskScheduler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import ru.netcracker.registration.controller.ViewController;
import ru.netcracker.registration.other.ScheduledTasts;

@Configuration
@ComponentScan(basePackages = "ru.netcracker.registration.other", basePackageClasses = {ScheduledTasts.class})
@EnableScheduling
public class TaskSchedulerConfig {
    @Bean
    public TaskScheduler taskScheduler(){
        return new ThreadPoolTaskScheduler();
    }


}
