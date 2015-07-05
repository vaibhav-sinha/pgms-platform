package com.pgms.service.impl;

import com.pgms.service.api.ComplaintStatusService;
import com.pgms.service.repository.ComplaintStatusRepository;
import com.pgms.shared.model.ComplaintStatus;
import com.pgms.shared.model.EntryStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by user-1 on 28/6/15.
 */
@Component
public class ComplaintStatusServiceImpl implements ComplaintStatusService {

    @Autowired
    ComplaintStatusRepository complaintStatusRepository;

    @Override
    public ComplaintStatus saveComplaintStatus(ComplaintStatus complaintStatus) {
        return complaintStatusRepository.save(complaintStatus);
    }

    @Override
    public ComplaintStatus getComplaintStatus(Long id) {
        return complaintStatusRepository.findOne(id);
    }

    @Override
    public ComplaintStatus getDefaultComplaintStatus() {
        return complaintStatusRepository.findOne(0L);
    }

    @Override
    public List<ComplaintStatus> getAllComplaintStatus() {
        return complaintStatusRepository.findAll();
    }

    @Override
    public List<ComplaintStatus> getAllActiveComplaintStatus() {
        return complaintStatusRepository.findByEntryStatus(EntryStatus.ACTIVE);
    }

    @Override
    public List<ComplaintStatus> getAllAccessibleBy(String role) throws Exception {
        switch (role) {
            case "ROLE_OFFICER":
                return complaintStatusRepository.findByOfficerCanAccess(true);
            case "ROLE_CALL_CENTRE":
                return complaintStatusRepository.findByCallCentreCanAccess(true);
            case "ROLE_CMO":
                return complaintStatusRepository.findByCmoCanAccess(true);
        }
        throw new Exception("A valid ROLE was not passed as parameter");
    }


}
