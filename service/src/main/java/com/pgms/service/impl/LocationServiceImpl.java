package com.pgms.service.impl;

import com.pgms.service.api.LocationService;
import com.pgms.service.entity.LocationEntity;
import com.pgms.service.repository.LocationRepository;
import com.pgms.shared.model.EntryStatus;
import com.pgms.shared.model.Location;
import com.pgms.shared.util.Mapper;
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

    @Autowired
    Mapper mapper;

    @Override
    public Location saveLocation(Location location) {
        LocationEntity locationEntity = mapper.map(location, LocationEntity.class);
        return mapper.map(locationRepository.save(locationEntity), Location.class);
    }

    @Override
    public Location getLocation(Long id) {
        return mapper.map(locationRepository.findOne(id), Location.class);
    }

    @Override
    public List<Location> getAllLocation() {
        return mapper.mapAsList(locationRepository.findAll(), Location.class);
    }

    @Override
    public List<Location> getAllActiveLocation() {
        return mapper.mapAsList(locationRepository.findByEntryStatus(EntryStatus.ACTIVE), Location.class);
    }
}
