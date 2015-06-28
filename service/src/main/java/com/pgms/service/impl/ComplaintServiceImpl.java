package com.pgms.service.impl;

import com.pgms.service.api.ComplaintService;
import com.pgms.service.repository.ComplaintRepository;
import com.pgms.shared.model.Complaint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by user-1 on 28/6/15.
 */
@Component
public class ComplaintServiceImpl implements ComplaintService {

    @Autowired
    ComplaintRepository complaintRepository;

    @Override
    public Complaint saveComplaint(Complaint complaint) {
        return complaintRepository.save(complaint);
    }

    @Override
    public Complaint findComplaintById(Long id) {
        return complaintRepository.findOne(id);
    }

    @Override
    public List<Complaint> findComplaintsForDepartment(Long id) {
        return complaintRepository.findComplaintsByDepartmentId(id);
    }
}
