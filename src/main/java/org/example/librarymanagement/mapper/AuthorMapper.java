package org.example.librarymanagement.mapper;

import org.example.librarymanagement.dao.request.AuthorRequest;
import org.example.librarymanagement.dao.response.AuthorResponse;
import org.example.librarymanagement.model.Author;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface AuthorMapper {

    @Mapping(source="image",target="imageName",qualifiedByName = "imageNameFromImage")
    Author requestToModel(AuthorRequest authorRequest);

    @Mapping(target="imageUrl",source="imageName", ignore=true)
    AuthorResponse modelToResponse(Author author);

    @Named("imageNameFromImage")
    static String getImageName(MultipartFile image){return System.nanoTime()+image.getOriginalFilename();}
}
