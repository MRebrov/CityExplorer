package ru.netcracker.registration.service;

import org.joda.time.DateTime;

public interface TaskService {
   String getCron(DateTime dateTime);
}
