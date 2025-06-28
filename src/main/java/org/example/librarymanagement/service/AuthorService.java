package org.example.librarymanagement.service;

import org.example.librarymanagement.dao.request.AuthorRequest;
import org.example.librarymanagement.dao.response.AuthorResponse;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

@Service
public interface AuthorService {
    AuthorResponse saveAuthor(AuthorRequest authorRequest);
    Resource getImage(String imageName);
    AuthorResponse getAuthor(int id);

    AuthorResponse update(int id, AuthorRequest request);

    void deleteAuthor(int id);
}
