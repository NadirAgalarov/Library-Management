package org.example.librarymanagement.mapper;

import org.example.librarymanagement.dao.request.BookRequest;
import org.example.librarymanagement.dao.response.BookResponse;
import org.example.librarymanagement.model.Author;
import org.example.librarymanagement.model.Book;
import org.example.librarymanagement.model.Category;
import org.mapstruct.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE,
    nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface BookMapper {

    @Mapping(source="image",target = "imageName",qualifiedByName = "imageToUrl")
    @Mapping(source = "authorIds",target = "authors",qualifiedByName ="integerToAuthor" )
    @Mapping(source = "categoryIds",target = "categories",qualifiedByName ="integerToCategory")
    Book requestToModel(BookRequest request);

    @Mapping(target = "imageUrl",source="imageName",qualifiedByName = "nameToUrl")
    BookResponse modelToResponse(Book book);

    @Mapping(source="image",target = "imageName",qualifiedByName = "imageToUrl")
    @Mapping(source = "authorIds",target = "authors",qualifiedByName ="integerToAuthor" )
    @Mapping(source = "categoryIds",target = "categories",qualifiedByName ="integerToCategory")
    void updateBook(@MappingTarget Book book, BookRequest request);

    @Named("integerToAuthor")
    static List<Author> integerToAuthor(List<Integer> list){

        List<Author> authorList=new ArrayList<>();
        for (Integer i : list) {
            authorList.add(new Author(i));
        }
        return authorList;
    }
    @Named("integerToCategory")
    static List<Category> integerToCategory(List<Integer> list){
        List<Category> categoryList=new ArrayList<>();
        for (Integer i : list) {
            categoryList.add(new Category(i));
        }
        return categoryList;
    }

    @Named("imageToUrl")
    static String imageToUrl(MultipartFile image){
        return System.nanoTime()+image.getOriginalFilename();
    }

    @Named("nameToUrl")
    static String imageNameToImageUrl(String imageName){
        return "http://localhost:8080/author/image/"+imageName;
    }
}
