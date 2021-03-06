package com.pgms.service.repository;

import com.pgms.service.entity.UpdateEntity;
import com.pgms.shared.model.Update;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by user-1 on 27/6/15.
 */
public interface UpdateRepository extends JpaRepository<UpdateEntity, Long> {

    @Query("SELECT u from UpdateEntity u where u.complaint.id = :id")
    List<UpdateEntity> findUpdatesByComplaintId(@Param("id") Long id);
}
