package org.example.librarymanagement.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.librarymanagement.dao.request.CategoryRequest;
import org.example.librarymanagement.dao.response.CategoryResponse;
import org.example.librarymanagement.exception.CategoryNotFoundException;
import org.example.librarymanagement.mapper.CategoryMapper;
import org.example.librarymanagement.model.Category;
import org.example.librarymanagement.repository.CategoryRepository;
import org.example.librarymanagement.service.CategoryService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryMapper mapper;
    private final CategoryRepository repository;
    @Override
    public CategoryResponse saveCategory(CategoryRequest request) {
        Category category = mapper.requestToModel(request);
        Category saved = repository.save(category);
        CategoryResponse response = mapper.modelToResponse(saved);
        return response;
    }

    @Override
    public CategoryResponse getCategory(int id) {
        Category category = repository.findById(id).orElseThrow(() ->
                new CategoryNotFoundException("Category not found with id: " + id));
        CategoryResponse response = mapper.modelToResponse(category);
        return response;
    }

    @Override
    public CategoryResponse updateCategory(int id, CategoryRequest request) {
        Category category = repository.findById(id).orElseThrow(() ->
                new CategoryNotFoundException("Category not found with id: " + id));

        mapper.updateModel(category,request);
        Category saved = repository.save(category);
        CategoryResponse response = mapper.modelToResponse(saved);
        return response;
    }

    @Override
    public void delete(int id) {
        repository.deleteById(id);
    }
}
