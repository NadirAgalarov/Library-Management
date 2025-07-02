package org.example.librarymanagement.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.librarymanagement.dao.request.BookRequest;
import org.example.librarymanagement.dao.response.BookResponse;
import org.example.librarymanagement.service.BookService;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/book")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @PostMapping(consumes = "multipart/form-data")
    public ResponseEntity<BookResponse> createBook(@ModelAttribute BookRequest request){
        BookResponse response = bookService.createBook(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<BookResponse> getBook(@PathVariable("id") int id){
        BookResponse response = bookService.getBook(id);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @PutMapping(value = "{id}",consumes = "multipart/form-data")
    public ResponseEntity<BookResponse> update(@PathVariable("id") int id,@Valid @ModelAttribute BookRequest request){
        BookResponse response = bookService.update(id, request);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> delete(@PathVariable("id") int id){
        bookService.deleteBook(id);
        return new ResponseEntity<>("Book deleted successfully!",HttpStatus.OK);
    }

    @GetMapping("/image/{imageName}")
    public Resource getImage(@PathVariable String imageName){
        return bookService.getImage(imageName);
    }
}
