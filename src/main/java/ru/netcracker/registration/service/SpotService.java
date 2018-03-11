package ru.netcracker.registration.service;

import ru.netcracker.registration.model.Quest;
import ru.netcracker.registration.model.Spot;

import java.util.List;

public interface SpotService {
    Spot getById(Integer id);
    List<Spot> getByName(String name);
    List<Spot> getAll();
    boolean delete(Integer id);
    boolean delete(Spot spot);
    Spot save(Spot spot);
}
