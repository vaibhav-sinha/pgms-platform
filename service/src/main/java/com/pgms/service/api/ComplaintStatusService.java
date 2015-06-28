package com.pgms.service.api;

import com.pgms.shared.model.ComplaintStatus;

import java.util.List;

/**
 * Created by user-1 on 28/6/15.
 */
public interface ComplaintStatusService {

    ComplaintStatus saveComplaintStatus(ComplaintStatus complaintStatus);
    ComplaintStatus getComplaintStatus(Long id);
    ComplaintStatus getDefaultComplaintStatus();
    List<ComplaintStatus> getAllComplaintStatus();
    List<ComplaintStatus> getAllActiveComplaintStatus();
}
