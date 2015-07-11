package com.pgms.service.repository;

import com.pgms.service.entity.OfficerEntity;
import com.pgms.shared.model.AccountStatus;
import com.pgms.shared.model.Category;
import com.pgms.shared.model.EntryStatus;
import com.pgms.shared.model.Officer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by user-1 on 27/6/15.
 */
public interface OfficerRepository extends JpaRepository<OfficerEntity, Long> {

    List<OfficerEntity> findByEntryStatus(EntryStatus entryStatus);
    List<OfficerEntity> findByAccountStatus(AccountStatus accountStatus);
    OfficerEntity findByUsername(String username);
}
