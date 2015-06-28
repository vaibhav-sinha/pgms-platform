package com.pgms.service.impl;

import com.pgms.service.api.LocationService;
import com.pgms.service.repository.LocationRepository;
import com.pgms.shared.model.EntryStatus;
import com.pgms.shared.model.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by user-1 on 28/6/15.
 */
@Component
public class LocationServiceImpl implements LocationService {

    @Autowired
    LocationRepository locationRepository;
    @Override
    public Location saveLocation(Location location) {
        return locationRepository.save(location);
    }

    @Override
    public Location getLocation(Long id) {
        return locationRepository.findOne(id);
    }

    @Override
    public List<Location> getAllLocation() {
        return locationRepository.findAll();
    }

    @Override
    public List<Location> getAllActiveLocation() {
        return locationRepository.findByEntryStatus(EntryStatus.ACTIVE);
    }
}
