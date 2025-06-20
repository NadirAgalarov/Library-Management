package org.example.librarymanagement.service;

import org.example.librarymanagement.dao.request.AuthorRequest;
import org.example.librarymanagement.dao.response.AuthorResponse;
import org.example.librarymanagement.model.Author;
import org.springframework.stereotype.Service;

@Service
public interface AuthorService {
    AuthorResponse saveAuthor(AuthorRequest authorRequest);
}
