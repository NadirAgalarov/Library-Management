package org.example.librarymanagement.dao.response;

import jakarta.persistence.ManyToMany;
import org.example.librarymanagement.model.Book;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

public class AuthorResponse {
    private int id;
    private MultipartFile imageUrl;
    private String firstLame;
    private String lastName;
    private List<Book> books= new ArrayList<>();
}
