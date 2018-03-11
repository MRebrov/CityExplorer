package ru.netcracker.registration.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.netcracker.registration.model.Quest;
import ru.netcracker.registration.repository.QuestRepository;
import ru.netcracker.registration.service.QuestService;

import java.util.List;

@Service("QuestService")
public class QuestServiceImpl implements QuestService {

    @Autowired
    QuestRepository questRepository;

    @Override
    public Quest getById(Integer id) {
        return questRepository.findOne(id);
    }

    @Override
    public List<Quest> getByName(String name) {
        return questRepository.findAllByName(name);
    }

    @Override
    public List<Quest> getAll() {
        return (List<Quest>) questRepository.findAll();
    }

    @Override
    public boolean delete(Integer id) {
        questRepository.delete(id);
        return true;
    }

    @Override
    public boolean delete(Quest quest) {
        questRepository.delete(quest);
        return true;
    }

    @Override
    public Quest save(Quest quest) {
        return questRepository.save(quest);
    }
}
