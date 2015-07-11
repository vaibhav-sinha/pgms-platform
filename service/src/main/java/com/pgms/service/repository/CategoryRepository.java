package com.pgms.service.repository;

import com.pgms.service.entity.CategoryEntity;
import com.pgms.shared.model.Category;
import com.pgms.shared.model.EntryStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by user-1 on 27/6/15.
 */
public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {

    List<CategoryEntity> findByEntryStatus(EntryStatus entryStatus);
}
