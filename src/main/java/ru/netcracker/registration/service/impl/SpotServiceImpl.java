package ru.netcracker.registration.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;
import ru.netcracker.registration.model.DTO.SpotDTO;
import ru.netcracker.registration.model.Spot;
import ru.netcracker.registration.model.converter.SpotConverter;
import ru.netcracker.registration.repository.SpotRepository;
import ru.netcracker.registration.service.SpotService;

import java.util.ArrayList;
import java.util.List;


@Service("SpotService")
@Transactional
public class SpotServiceImpl implements SpotService {

    @Autowired
    SpotRepository spotRepository;

    @Override
    public Spot getById(Long id) {
        return spotRepository.findOne(id);
    }

    @Override
    public List<Spot> getByName(String name) {
        return spotRepository.findAllByName(name);
    }

    @Override
    public List<Spot> getAll() {
        return (List<Spot>) spotRepository.findAll();
    }

    @Override
    public boolean delete(Long id) {
        spotRepository.delete(id);
        return true;
    }

    @Override
    public boolean delete(Spot spot) {
        spotRepository.delete(spot);
        return true;
    }

    @Override
    public Spot save(Spot spot) {
        return spotRepository.save(spot);
    }

    @Override
    public List<SpotDTO> getAllInRange(double lat, double lng, double range) {
        List<Spot> spots = getAll();
        List<SpotDTO> res=new ArrayList<>();
        for(Spot spot: spots){
            if(distFrom(lat, lng, spot)<=range)
                res.add(SpotConverter.convertToDTO(spot));
        }
        return res;
    }

    public double distFrom(double lat1, double lng1, Spot spot) {
        double lat2=spot.getLat();
        double lng2=spot.getLng();
        double earthRadius = 6371000; //meters
        double dLat = Math.toRadians(lat2-lat1);
        double dLng = Math.toRadians(lng2-lng1);
        double a = Math.sin(dLat/2) * Math.sin(dLat/2) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                        Math.sin(dLng/2) * Math.sin(dLng/2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        double dist = (earthRadius * c);

        return dist;
    }
}
