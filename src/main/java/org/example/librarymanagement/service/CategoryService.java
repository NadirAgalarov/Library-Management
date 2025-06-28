package org.example.librarymanagement.service;

import org.example.librarymanagement.dao.request.CategoryRequest;
import org.example.librarymanagement.dao.response.CategoryResponse;


public interface CategoryService {
    CategoryResponse saveCategory(CategoryRequest request);
    CategoryResponse getCategory(int id);

    CategoryResponse updateCategory(int id, CategoryRequest request);

    void delete(int id);

}
