package ru.netcracker.registration.service;


import ru.netcracker.registration.model.Quest;

import java.util.List;

public interface QuestService {
    Quest getById(Integer id);
    List<Quest> getByName(String name);
    List<Quest> getAll();
    boolean delete(Integer id);
    boolean delete(Quest quest);
    Quest save(Quest quest);

}
