package ru.netcracker.registration.service;

import ru.netcracker.registration.model.DTO.SpotDTO;
import ru.netcracker.registration.model.Quest;
import ru.netcracker.registration.model.Spot;

import java.util.List;

public interface SpotService {
    Spot getById(Long id);
    List<Spot> getByName(String name);
    List<Spot> getAll();
    boolean delete(Long id);
    boolean delete(Spot spot);
    Spot save(Spot spot);
    List<SpotDTO> getAllInRange(double lat, double lng, double range);
    double distFrom(double lat1, double lng1, Spot spot);
}
