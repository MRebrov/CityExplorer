package ru.netcracker.registration.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.web.bind.annotation.*;
import ru.netcracker.registration.model.DTO.TaskDTO;
import ru.netcracker.registration.other.ScheduledTasks;
import ru.netcracker.registration.other.TaskDatabaseClear;
import ru.netcracker.registration.other.TaskInactiveClear;
import ru.netcracker.registration.other.TaskSuspiciousClear;
import ru.netcracker.registration.service.QuestService;
import ru.netcracker.registration.service.TaskService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ScheduledFuture;

@RestController
@RequestMapping("/userapi")
public class AdminController {

    @Autowired
    ScheduledTasks scheduledTasks;
    @Autowired
    QuestService questService;
    @Autowired
    TaskService taskService;

    List<ScheduledFuture> tasks = new ArrayList<>();
    List<TaskDTO> taskDTOList = new ArrayList<>();


    @PostMapping("add/task/")
    public ResponseEntity<?> post(@RequestBody TaskDTO task) {
        try {


            if (task.getOption().startsWith("Clea")) {
                taskDTOList.add(task);
                if (task.getTask().startsWith("Find in")) {
                    tasks.add(scheduledTasks.add(new TaskInactiveClear(questService),
                            new CronTrigger(taskService.getCron(task.getExecution()))));
                }
                if (task.getTask().startsWith("Find sus")){
                    tasks.add(scheduledTasks.add(new TaskSuspiciousClear(questService),
                            new CronTrigger(taskService.getCron(task.getExecution()))));
                }
                if(task.getTask().startsWith("Find ban")){
                    tasks.add(scheduledTasks.add(new TaskDatabaseClear(questService),
                            new CronTrigger(taskService.getCron(task.getExecution()))));
                }

            }
            return new ResponseEntity<Object>("Task successfully added",
                    HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Object>(e.getMessage(),
                    HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping("/getTasks")
    public Iterable<TaskDTO> getTasks(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for(int i =0; i< tasks.size(); i++){
            taskDTOList.get(i).setFinished(tasks.get(i).isDone());

//            try {
//                taskDTOList.get(i).setExecution(sdf.parse(sdf.format(taskDTOList.get(i).getExecution()).toString()));
//            } catch (ParseException e) {
//                e.printStackTrace();
//            }
            //taskDTOList.get(i).setExecution();
        }
        return taskDTOList;
    }
}
