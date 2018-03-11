package ru.netcracker.registration.model.storage_emulation;

import ru.netcracker.registration.model.Quest;

import java.util.List;

public class QuestStorage {

    private static QuestStorage storage = null;
    private QuestStorage(){}

    public static QuestStorage getInstance(){
        if (storage == null){
            storage = new QuestStorage();
        }
        return storage;
    }

    private List<Quest> quests;

    public List<Quest> getQuests() {
        return quests;
    }

    public void setQuests(List<Quest> quests) {
        this.quests = quests;
    }

    public void add(Quest quest){
        this.quests.add(quest);
    }
}
