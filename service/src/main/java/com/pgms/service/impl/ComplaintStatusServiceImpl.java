package com.pgms.service.impl;

import com.pgms.service.api.ComplaintStatusService;
import com.pgms.service.entity.ComplaintStatusEntity;
import com.pgms.service.repository.ComplaintStatusRepository;
import com.pgms.shared.model.ComplaintStatus;
import com.pgms.shared.model.EntryStatus;
import com.pgms.shared.util.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * Created by user-1 on 28/6/15.
 */
@Component
public class ComplaintStatusServiceImpl implements ComplaintStatusService {

    @Autowired
    ComplaintStatusRepository complaintStatusRepository;

    @Autowired
    Mapper mapper;

    @Override
    public ComplaintStatus saveComplaintStatus(ComplaintStatus complaintStatus) {
        ComplaintStatusEntity complaintStatusEntity = mapper.map(complaintStatus, ComplaintStatusEntity.class);
        return mapper.map(complaintStatusRepository.save(complaintStatusEntity), ComplaintStatus.class);
    }

    @Override
    public ComplaintStatus getComplaintStatus(Long id) {
        return mapper.map(complaintStatusRepository.findOne(id), ComplaintStatus.class);
    }

    @Override
    public ComplaintStatus getDefaultComplaintStatus() {
        return mapper.map(complaintStatusRepository.findByRepresentsDefault(true).get(0), ComplaintStatus.class);
    }

    @Override
    public ComplaintStatus getClosedComplaintStatus() {
        ComplaintStatusEntity complaintStatusEntity = complaintStatusRepository.findByRepresentsClosed(true).get(0);
        return mapper.map(complaintStatusEntity, ComplaintStatus.class);
    }

    @Override
    public List<ComplaintStatus> getAllComplaintStatus() {
        return mapper.mapAsList(complaintStatusRepository.findAll(), ComplaintStatus.class);
    }

    @Override
    public List<ComplaintStatus> getAllActiveComplaintStatus() {
        return mapper.mapAsList(complaintStatusRepository.findByEntryStatus(EntryStatus.ACTIVE), ComplaintStatus.class);
    }

}
