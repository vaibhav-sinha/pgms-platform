package com.pgms.service.impl;

import com.pgms.service.api.DesignationService;
import com.pgms.service.repository.DesignationRepository;
import com.pgms.shared.model.Designation;
import com.pgms.shared.model.EntryStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by user-1 on 28/6/15.
 */
@Component
public class DesignationServiceImpl implements DesignationService {

    @Autowired
    DesignationRepository designationRepository;

    @Override
    public Designation saveDesignation(Designation designation) {
        return designationRepository.save(designation);
    }

    @Override
    public Designation getDesignation(Long id) {
        return designationRepository.findOne(id);
    }

    @Override
    public List<Designation> getAllDesignation() {
        return designationRepository.findAll();
    }

    @Override
    public List<Designation> getAllActiveDesignation() {
        return designationRepository.findByEntryStatus(EntryStatus.ACTIVE);
    }
}
