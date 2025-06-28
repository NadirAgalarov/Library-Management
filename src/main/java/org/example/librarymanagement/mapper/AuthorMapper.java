package org.example.librarymanagement.mapper;

import org.example.librarymanagement.dao.request.AuthorRequest;
import org.example.librarymanagement.dao.response.AuthorResponse;
import org.example.librarymanagement.model.Author;
import org.mapstruct.*;
import org.springframework.web.multipart.MultipartFile;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface AuthorMapper {

    @Mapping(source="image",target="imageName",qualifiedByName = "imageNameFromImage")
    Author requestToModel(AuthorRequest authorRequest);

    @Mapping(target="imageUrl",source="imageName")
    AuthorResponse modelToResponse(Author author);

    @Mapping(source="image",target="imageName",qualifiedByName = "imageNameFromImage")
    @Mapping(source = "firstName",target = "firstName", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "lastName",target = "lastName", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateModel(@MappingTarget Author author, AuthorRequest request);

    @Named("imageNameFromImage")
    static String getImageName(MultipartFile image){return System.nanoTime()+image.getOriginalFilename();}
}
