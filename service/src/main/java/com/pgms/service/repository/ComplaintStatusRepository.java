package com.pgms.service.repository;

import com.pgms.service.entity.ComplaintStatusEntity;
import com.pgms.shared.model.ComplaintStatus;
import com.pgms.shared.model.EntryStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by user-1 on 28/6/15.
 */
public interface ComplaintStatusRepository extends JpaRepository<ComplaintStatusEntity, Long> {

    List<ComplaintStatusEntity> findByEntryStatus(EntryStatus entryStatus);
    List<ComplaintStatusEntity> findByRepresentsClosed(Boolean closed);
    List<ComplaintStatusEntity> findByRepresentsDefault(Boolean closed);
}