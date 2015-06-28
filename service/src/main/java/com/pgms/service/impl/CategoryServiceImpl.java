package com.pgms.service.impl;

import com.pgms.service.api.CategoryService;
import com.pgms.service.repository.CategoryRepository;
import com.pgms.shared.model.Category;
import com.pgms.shared.model.EntryStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by user-1 on 28/6/15.
 */
@Component
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public Category saveCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category getCategory(Long id) {
        return categoryRepository.findOne(id);
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public List<Category> getAllActiveCategories() {
        return categoryRepository.findByEntryStatus(EntryStatus.ACTIVE);
    }


}
