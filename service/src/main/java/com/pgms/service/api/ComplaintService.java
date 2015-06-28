package com.pgms.service.api;

import com.pgms.shared.model.Complaint;

import java.util.List;

/**
 * Created by user-1 on 27/6/15.
 */
public interface ComplaintService {

    Complaint saveComplaint(Complaint complaint);
    Complaint findComplaintById(Long id);
    List<Complaint> findComplaintsForDepartment(Long id);
}
