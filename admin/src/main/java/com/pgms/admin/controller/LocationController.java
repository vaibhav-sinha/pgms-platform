package com.pgms.admin.controller;

import com.pgms.service.api.LocationService;
import com.pgms.shared.model.Location;
import com.pgms.shared.model.EntryStatus;
import com.pgms.shared.pojo.PgmsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by user-1 on 28/6/15.
 */
@RestController
@RequestMapping("/location")
public class LocationController {

    @Autowired
    LocationService locationService;

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public PgmsResponse<Location> saveLocation(@RequestBody Location location) {
        PgmsResponse<Location> pgmsResponse = new PgmsResponse<>();
        if(location == null || location.getId() != null) {
            pgmsResponse.setSuccess(false);
            pgmsResponse.setMessage("Failed to save location. Either location is null or location id is not null");
            return pgmsResponse;
        }
        try {
            location.setEntryStatus(EntryStatus.ACTIVE);
            location = locationService.saveLocation(location);
            pgmsResponse.setSuccess(true);
            pgmsResponse.setMessage("Successfully saved location with ID " + location.getId());
            pgmsResponse.setData(location);
        }
        catch (Exception e) {
            pgmsResponse.setSuccess(false);
            pgmsResponse.setMessage(e.getMessage());
        }
        return pgmsResponse;
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public PgmsResponse<Location> updateLocation(@RequestBody Location location) {
        PgmsResponse<Location> pgmsResponse = new PgmsResponse<>();
        if(location == null || location.getId() == null) {
            pgmsResponse.setSuccess(false);
            pgmsResponse.setMessage("Failed to update location. Either location or location id is null");
            return pgmsResponse;
        }
        try {
            locationService.saveLocation(location);
            pgmsResponse.setSuccess(true);
            pgmsResponse.setMessage("Successfully updated location with ID " + location.getId());
            pgmsResponse.setData(location);
        }
        catch (Exception e) {
            pgmsResponse.setSuccess(false);
            pgmsResponse.setMessage(e.getMessage());
        }
        return pgmsResponse;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public PgmsResponse<Location> deleteLocation(@RequestBody Location location) {
        PgmsResponse<Location> pgmsResponse = new PgmsResponse<>();
        if(location == null || location.getId() == null) {
            pgmsResponse.setSuccess(false);
            pgmsResponse.setMessage("Failed to delete location. Either location or location id is null");
            return pgmsResponse;
        }
        location.setEntryStatus(EntryStatus.DELETED);
        return updateLocation(location);
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public PgmsResponse<List<Location>> getAllLocation() {
        PgmsResponse<List<Location>> pgmsResponse = new PgmsResponse<>();
        try {
            pgmsResponse.setSuccess(true);
            pgmsResponse.setMessage("Successfully got all categories");
            pgmsResponse.setData(locationService.getAllLocation());
        }
        catch (Exception e) {
            pgmsResponse.setSuccess(false);
            pgmsResponse.setMessage(e.getMessage());
        }
        return pgmsResponse;
    }

    @RequestMapping(value = "/getAllActive", method = RequestMethod.GET)
    public PgmsResponse<List<Location>> getAllActiveLocation() {
        PgmsResponse<List<Location>> pgmsResponse = new PgmsResponse<>();
        try {
            pgmsResponse.setSuccess(true);
            pgmsResponse.setMessage("Successfully got all categories");
            pgmsResponse.setData(locationService.getAllActiveLocation());
        }
        catch (Exception e) {
            pgmsResponse.setSuccess(false);
            pgmsResponse.setMessage(e.getMessage());
        }
        return pgmsResponse;
    }
}

