package com.example.sinhnhat.service;

import java.util.List;

import com.example.sinhnhat.entity.Category;
import com.example.sinhnhat.model.request.CreateCategoryRequest;

public interface CategoryService {
    List<Category> findAll();

    List<Category> getListEnabled();

    Category createCategory(CreateCategoryRequest request);

    Category updateCategory(long id,CreateCategoryRequest request);

    void enableCategory(long id);

    void deleteCategory(long id);
}
