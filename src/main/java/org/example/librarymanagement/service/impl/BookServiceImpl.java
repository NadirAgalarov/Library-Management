package org.example.librarymanagement.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.librarymanagement.dao.request.BookRequest;
import org.example.librarymanagement.dao.response.BookResponse;
import org.example.librarymanagement.mapper.BookMapper;
import org.example.librarymanagement.model.Book;
import org.example.librarymanagement.repository.BookRepository;
import org.example.librarymanagement.service.BookService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;
    @Value("${images.base.url}")
    private String url;

    @Override
    public BookResponse createBook(BookRequest request) {
        Book requested = bookMapper.requestToModel(request);
        System.out.println(requested.getImageName());
        saveImage(request.getImage(), requested.getImageName());
        Book saved = bookRepository.save(requested);
        BookResponse response = bookMapper.modelToResponse(saved);

        return response;
    }


    private void saveImage(MultipartFile image,String imageName){
        File destination=new File(url+"\\"+imageName);
        try {
            image.transferTo(destination);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Resource getImage(String imageName) {
        File file=new File(url+"\\"+imageName);
        Resource image;
        try {
            image=new UrlResource(file.toURI());
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        return image;
    }
}
