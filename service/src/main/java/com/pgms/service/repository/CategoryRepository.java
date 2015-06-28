package com.pgms.service.repository;

import com.pgms.shared.model.Category;
import com.pgms.shared.model.EntryStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by user-1 on 27/6/15.
 */
public interface CategoryRepository extends JpaRepository<Category, Long> {

    List<Category> findByEntryStatus(EntryStatus entryStatus);
}
