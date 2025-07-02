package org.example.librarymanagement.service;

import org.example.librarymanagement.dao.request.BookRequest;
import org.example.librarymanagement.dao.response.BookResponse;
import org.springframework.core.io.Resource;

public interface BookService {
    BookResponse createBook(BookRequest request);
    Resource getImage(String imageName);
    BookResponse getBook(int id);

    BookResponse update(int id, BookRequest request);

    void deleteBook(int id);
}
