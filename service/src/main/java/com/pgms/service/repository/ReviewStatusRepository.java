package com.pgms.service.repository;

import com.pgms.service.entity.ReviewStatusEntity;
import com.pgms.shared.model.EntryStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by user-1 on 12/7/15.
 */
public interface ReviewStatusRepository extends JpaRepository<ReviewStatusEntity, Long> {

    List<ReviewStatusEntity> findByEntryStatus(EntryStatus entryStatus);
    List<ReviewStatusEntity> findByRepresentsClosed(Boolean closed);
    List<ReviewStatusEntity> findByRepresentsDefault(Boolean closed);
    List<ReviewStatusEntity> findByRepresentsReopen(Boolean closed);
}