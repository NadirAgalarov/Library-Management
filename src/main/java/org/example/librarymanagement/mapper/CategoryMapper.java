package org.example.librarymanagement.mapper;

import org.example.librarymanagement.dao.request.CategoryRequest;
import org.example.librarymanagement.dao.response.CategoryResponse;
import org.example.librarymanagement.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    Category requestToModel(CategoryRequest request);
    CategoryResponse modelToResponse(Category category);
    void updateModel(@MappingTarget Category category,CategoryRequest request);
}
