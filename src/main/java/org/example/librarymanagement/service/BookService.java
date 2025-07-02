package org.example.librarymanagement.service;

import org.example.librarymanagement.dao.request.BookRequest;
import org.example.librarymanagement.dao.response.BookResponse;
import org.springframework.core.io.Resource;

public interface BookService {
    BookResponse createBook(BookRequest request);
    public Resource getImage(String imageName);
}
