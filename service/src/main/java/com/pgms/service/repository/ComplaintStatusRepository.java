package com.pgms.service.repository;

import com.pgms.shared.model.ComplaintStatus;
import com.pgms.shared.model.EntryStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by user-1 on 28/6/15.
 */
public interface ComplaintStatusRepository extends JpaRepository<ComplaintStatus, Long> {

    List<ComplaintStatus> findByEntryStatus(EntryStatus entryStatus);
    List<ComplaintStatus> findByOfficerCanAccess(Boolean access);
    List<ComplaintStatus> findByCallCentreCanAccess(Boolean access);
    List<ComplaintStatus> findByCmoCanAccess(Boolean access);
}
