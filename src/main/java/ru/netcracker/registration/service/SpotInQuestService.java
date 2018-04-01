package ru.netcracker.registration.service;

import ru.netcracker.registration.model.Spot;
import ru.netcracker.registration.model.SpotInQuest;

import java.util.List;

public interface SpotInQuestService {
    SpotInQuest getById(Integer id);
    List<SpotInQuest> getAll();
    SpotInQuest getBySpotAndQuest(Long spotId, Long questId);
    boolean delete(Integer id);
    boolean delete(SpotInQuest spotInQuest);
    SpotInQuest save(SpotInQuest spotInQuest);
}
