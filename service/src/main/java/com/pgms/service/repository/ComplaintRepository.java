package com.pgms.service.repository;

import com.pgms.shared.model.Complaint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by user-1 on 27/6/15.
 */
public interface ComplaintRepository extends JpaRepository<Complaint, Long> {

    @Query("SELECT c from Complaint c where c.department.id = :id")
    List<Complaint> findComplaintsByDepartmentId(@Param("id") Long id);
}
