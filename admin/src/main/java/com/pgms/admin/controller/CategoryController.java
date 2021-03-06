package com.pgms.admin.controller;

import com.pgms.service.api.CategoryService;
import com.pgms.shared.model.Category;
import com.pgms.shared.model.Category;
import com.pgms.shared.model.ComplaintStatus;
import com.pgms.shared.model.EntryStatus;
import com.pgms.shared.pojo.PgmsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by user-1 on 28/6/15.
 */
@RestController
@RequestMapping("/api/category")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    public PgmsResponse<Category> getCategory(@PathVariable Long id) {
        PgmsResponse<Category> pgmsResponse = new PgmsResponse<>();
        if(id == null) {
            pgmsResponse.setSuccess(false);
            pgmsResponse.setMessage("Failed to get category. Category id is null");
            return pgmsResponse;
        }
        try {
            Category category = categoryService.getCategory(id);
            if(category == null) {
                pgmsResponse.setSuccess(false);
                pgmsResponse.setMessage("Failed to get category. Category with id = " + id + " does not exist");
            }
            else {
                pgmsResponse.setData(category);
                pgmsResponse.setSuccess(true);
                pgmsResponse.setMessage("Successfully got category with ID " + id);
            }
        }
        catch (Exception e) {
            pgmsResponse.setSuccess(false);
            pgmsResponse.setMessage(e.getMessage());
        }
        return pgmsResponse;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public PgmsResponse<Category> saveCategory(@RequestBody Category category) {
        PgmsResponse<Category> pgmsResponse = new PgmsResponse<>();
        if(category == null || category.getId() != null) {
            pgmsResponse.setSuccess(false);
            pgmsResponse.setMessage("Failed to save category. Either category is null or category id is not null");
            return pgmsResponse;
        }
        try {
            category.setEntryStatus(EntryStatus.ACTIVE);
            category = categoryService.saveCategory(category);
            pgmsResponse.setSuccess(true);
            pgmsResponse.setMessage("Successfully saved category with ID " + category.getId());
            pgmsResponse.setData(category);
        }
        catch (Exception e) {
            pgmsResponse.setSuccess(false);
            pgmsResponse.setMessage(e.getMessage());
        }
        return pgmsResponse;
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public PgmsResponse<Category> updateCategory(@RequestBody Category category) {
        PgmsResponse<Category> pgmsResponse = new PgmsResponse<>();
        if(category == null || category.getId() == null) {
            pgmsResponse.setSuccess(false);
            pgmsResponse.setMessage("Failed to update category. Either category or category id is null");
            return pgmsResponse;
        }
        try {
            if(category.getEntryStatus() == null) {
                category.setEntryStatus(EntryStatus.ACTIVE);
            }
            categoryService.saveCategory(category);
            pgmsResponse.setSuccess(true);
            pgmsResponse.setMessage("Successfully updated category with ID " + category.getId());
            pgmsResponse.setData(category);
        }
        catch (Exception e) {
            pgmsResponse.setSuccess(false);
            pgmsResponse.setMessage(e.getMessage());
        }
        return pgmsResponse;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public PgmsResponse<Category> deleteCategory(@RequestBody Category category) {
        PgmsResponse<Category> pgmsResponse = new PgmsResponse<>();
        if(category == null || category.getId() == null) {
            pgmsResponse.setSuccess(false);
            pgmsResponse.setMessage("Failed to delete category. Either category or category id is null");
            return pgmsResponse;
        }
        category = categoryService.getCategory(category.getId());
        category.setEntryStatus(EntryStatus.DELETED);
        return updateCategory(category);
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public PgmsResponse<List<Category>> getAllCategory() {
        PgmsResponse<List<Category>> pgmsResponse = new PgmsResponse<>();
        try {
            pgmsResponse.setSuccess(true);
            pgmsResponse.setMessage("Successfully got all categories");
            pgmsResponse.setData(categoryService.getAllCategories());
        }
        catch (Exception e) {
            pgmsResponse.setSuccess(false);
            pgmsResponse.setMessage(e.getMessage());
        }
        return pgmsResponse;
    }

    @RequestMapping(value = "/getAllActive", method = RequestMethod.GET)
    public PgmsResponse<List<Category>> getAllActiveCategory() {
        PgmsResponse<List<Category>> pgmsResponse = new PgmsResponse<>();
        try {
            pgmsResponse.setSuccess(true);
            pgmsResponse.setMessage("Successfully got all categories");
            pgmsResponse.setData(categoryService.getAllActiveCategories());
        }
        catch (Exception e) {
            pgmsResponse.setSuccess(false);
            pgmsResponse.setMessage(e.getMessage());
        }
        return pgmsResponse;
    }
}
