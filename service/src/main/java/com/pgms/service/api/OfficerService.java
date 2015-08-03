package com.pgms.service.api;

import com.pgms.shared.model.Officer;

import java.util.List;

/**
 * Created by user-1 on 28/6/15.
 */
public interface OfficerService {

    Officer createOfficer(Officer officer);
    Officer saveOfficer(Officer officer);
    Officer getOfficer(Long id);
    List<Officer> getAllOfficer();
    List<Officer> findOfficersByName(String name, int start, int count);
    List<Officer> getAllActiveOfficer();
    Officer login(String username, String password);
    Officer findOfficerByUsername(String username);
}
