package com.pgms.service.repository;

import com.pgms.service.entity.LocationEntity;
import com.pgms.shared.model.Category;
import com.pgms.shared.model.EntryStatus;
import com.pgms.shared.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by user-1 on 27/6/15.
 */
public interface LocationRepository extends JpaRepository<LocationEntity, Long> {

    List<LocationEntity> findByEntryStatus(EntryStatus entryStatus);
}
