package com.pgms.service.repository;

import com.pgms.service.entity.VerificationStatusEntity;
import com.pgms.shared.model.EntryStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by user-1 on 12/7/15.
 */
public interface VerificationStatusRepository extends JpaRepository<VerificationStatusEntity, Long> {

    List<VerificationStatusEntity> findByEntryStatus(EntryStatus entryStatus);
    List<VerificationStatusEntity> findByRepresentsClosed(Boolean closed);
    List<VerificationStatusEntity> findByRepresentsDefault(Boolean closed);
}
