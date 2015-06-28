package com.pgms.service.api;

import com.pgms.shared.model.Location;

import java.util.List;

/**
 * Created by user-1 on 28/6/15.
 */
public interface LocationService {

    Location saveLocation(Location location);
    Location getLocation(Long id);
    List<Location> getAllLocation();
    List<Location> getAllActiveLocation();
}
