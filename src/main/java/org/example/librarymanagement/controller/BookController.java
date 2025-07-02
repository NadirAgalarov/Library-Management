package org.example.librarymanagement.controller;

import lombok.RequiredArgsConstructor;
import org.example.librarymanagement.dao.request.BookRequest;
import org.example.librarymanagement.dao.response.BookResponse;
import org.example.librarymanagement.service.BookService;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/book1")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @PostMapping(consumes = "multipart/form-data")
    public ResponseEntity<BookResponse> createBook(@ModelAttribute BookRequest request){
        BookResponse response = bookService.createBook(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/image/{imageName}")
    public Resource getImage(@PathVariable String imageName){
        return bookService.getImage(imageName);
    }
}
