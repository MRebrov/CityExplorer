package ru.netcracker.registration.service.impl;

import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import ru.netcracker.registration.service.TaskService;

@Service("TaskService")
public class TaskServiceImpl implements TaskService{
    @Override
    public String getCron(DateTime dateTime) {
        String[] dt = dateTime.toString().split("T");
        String[] ymd = dt[0].split("-");
        String[] hm = dt[1].split(":");
        StringBuilder sb = new StringBuilder();
        sb.append("0 ")
                .append(hm[1])
                .append(" ")
                .append(hm[0])
                .append(" ")
                .append(ymd[2])
                .append(" ")
                .append(ymd[1])
                .append(" ?");
//                .append(ymd[0]);
        return sb.toString();
    }
}
