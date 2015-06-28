package com.pgms.service.repository;

import com.pgms.shared.model.AccountStatus;
import com.pgms.shared.model.Category;
import com.pgms.shared.model.EntryStatus;
import com.pgms.shared.model.Officer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by user-1 on 27/6/15.
 */
public interface OfficerRepository extends JpaRepository<Officer, Long> {

    List<Officer> findByEntryStatus(EntryStatus entryStatus);
    List<Officer> findByAccountStatus(AccountStatus accountStatus);
    Officer findByUsername(String username);
}
