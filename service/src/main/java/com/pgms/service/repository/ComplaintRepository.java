package com.pgms.service.repository;

import com.pgms.service.entity.ComplaintEntity;
import com.pgms.shared.model.Complaint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by user-1 on 27/6/15.
 */
public interface ComplaintRepository extends JpaRepository<ComplaintEntity, Long> {

    @Query("SELECT c from ComplaintEntity c where c.department.id = :id")
    List<ComplaintEntity> findComplaintsByDepartmentId(@Param("id") Long id);
}
