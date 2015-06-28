package com.pgms.service.repository;

import com.pgms.shared.model.Submitter;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by user-1 on 27/6/15.
 */
public interface SubmitterRepository extends JpaRepository<Submitter, Long> {

    Submitter findByMobile(String mobile);
    Submitter findByEmail(String email);
}
