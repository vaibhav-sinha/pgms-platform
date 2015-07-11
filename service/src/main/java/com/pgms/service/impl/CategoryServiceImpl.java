package com.pgms.service.impl;

import com.pgms.service.api.CategoryService;
import com.pgms.service.entity.CategoryEntity;
import com.pgms.service.repository.CategoryRepository;
import com.pgms.shared.model.Category;
import com.pgms.shared.model.EntryStatus;
import com.pgms.shared.util.Mapper;
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

    @Autowired
    Mapper mapper;

    @Override
    public Category saveCategory(Category category) {
        CategoryEntity categoryEntity = mapper.map(category, CategoryEntity.class);
        return mapper.map(categoryRepository.save(categoryEntity), Category.class);
    }

    @Override
    public Category getCategory(Long id) {
        return mapper.map(categoryRepository.findOne(id), Category.class);
    }

    @Override
    public List<Category> getAllCategories() {
        return mapper.mapAsList(categoryRepository.findAll(), Category.class);
    }

    @Override
    public List<Category> getAllActiveCategories() {
        return mapper.mapAsList(categoryRepository.findByEntryStatus(EntryStatus.ACTIVE), Category.class);
    }


}
