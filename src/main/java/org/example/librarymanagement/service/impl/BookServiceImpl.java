package org.example.librarymanagement.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.librarymanagement.dao.request.BookRequest;
import org.example.librarymanagement.dao.response.BookResponse;
import org.example.librarymanagement.exception.BookNotFoundException;
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

    @Override
    public BookResponse getBook(int id) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new
                BookNotFoundException("Book not found with id: " + id));
        BookResponse response = bookMapper.modelToResponse(book);
        return response;

    }

    @Override
    public BookResponse update(int id, BookRequest request) {
        Book book=bookRepository.findById(id).orElseThrow(()->
                new BookNotFoundException("Book not found with id: "+id));
        bookMapper.updateBook(book,request);
        Book saved = bookRepository.save(book);
        BookResponse response = bookMapper.modelToResponse(saved);

        return response;
    }

    @Override
    public void deleteBook(int id) {
        bookRepository.deleteById(id);
    }

    private void saveImage(MultipartFile image, String imageName){
        File destination=new File(url+"\\"+imageName);
        try {
            image.transferTo(destination);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private void deleteImage(String imageName){
        File image=new File(url+"\\"+imageName);
        if (image!=null&& image.exists()) {
            boolean deleted = image.delete();
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
