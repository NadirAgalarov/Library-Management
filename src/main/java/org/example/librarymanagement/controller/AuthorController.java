package org.example.librarymanagement.controller;

import lombok.RequiredArgsConstructor;
import org.example.librarymanagement.dao.request.AuthorRequest;
import org.example.librarymanagement.dao.response.AuthorResponse;
import org.example.librarymanagement.service.AuthorService;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
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

    @GetMapping("{id}")
    public ResponseEntity<AuthorResponse> getAuthor(@PathVariable int id){
        return ResponseEntity.ok(authorService.getAuthor(id));
    }

    @PutMapping(consumes = "multipart/form-data",value = "/{id}")
    public ResponseEntity<AuthorResponse> updateAuthor(@PathVariable("id") int id,
                                                       @ModelAttribute AuthorRequest request){
        System.out.println(request.getFirstName());
        System.out.println(request.getLastName());
        AuthorResponse updated = authorService.update(id, request);
        System.out.println(updated.getFirstName());
        System.out.println(updated.getLastName());
        return new ResponseEntity<>(updated, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteAuthor(@PathVariable("id") int id){
        authorService.deleteAuthor(id);
        return new ResponseEntity<>("Author deleted successfully!",HttpStatus.OK);
    }
}
