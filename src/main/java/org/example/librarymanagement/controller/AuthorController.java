package org.example.librarymanagement.controller;

import lombok.RequiredArgsConstructor;
import org.example.librarymanagement.dao.request.AuthorRequest;
import org.example.librarymanagement.dao.response.AuthorResponse;
import org.example.librarymanagement.service.AuthorService;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/author")
@RequiredArgsConstructor
public class AuthorController {
    private final AuthorService authorService;

    @PostMapping(consumes = "multipart/form-data")
    public ResponseEntity<AuthorResponse> saveAuthor(@ModelAttribute AuthorRequest request){
        return ResponseEntity.ok(authorService.saveAuthor(request));
    }

    @GetMapping("/image/{imageName}")
    public Resource getImage(@PathVariable String imageName){
        return authorService.getImage(imageName);
    }
}
