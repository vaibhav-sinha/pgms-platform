package com.pgms.service.impl;

import com.pgms.service.api.DesignationService;
import com.pgms.service.entity.DesignationEntity;
import com.pgms.service.repository.DesignationRepository;
import com.pgms.shared.model.Designation;
import com.pgms.shared.model.EntryStatus;
import com.pgms.shared.util.Mapper;
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

    @Autowired
    Mapper mapper;

    @Override
    public Designation saveDesignation(Designation designation) {
        DesignationEntity designationEntity = mapper.map(designation, DesignationEntity.class);
        return mapper.map(designationRepository.save(designationEntity), Designation.class);
    }

    @Override
    public Designation getDesignation(Long id) {
        return mapper.map(designationRepository.findOne(id), Designation.class);
    }

    @Override
    public List<Designation> getAllDesignation() {
        return mapper.mapAsList(designationRepository.findAll(), Designation.class);
    }

    @Override
    public List<Designation> getAllActiveDesignation() {
        return mapper.mapAsList(designationRepository.findByEntryStatus(EntryStatus.ACTIVE), Designation.class);
    }
}
