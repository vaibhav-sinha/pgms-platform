package com.pgms.service.api;

import com.pgms.shared.model.Category;

import java.util.List;

/**
 * Created by user-1 on 27/6/15.
 */
public interface CategoryService {

    Category saveCategory(Category category);
    Category getCategory(Long id);
    List<Category> getAllCategories();
    List<Category> getAllActiveCategories();
}
