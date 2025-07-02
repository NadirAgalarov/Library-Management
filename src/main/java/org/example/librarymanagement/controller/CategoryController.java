package org.example.librarymanagement.controller;

import lombok.RequiredArgsConstructor;
import org.example.librarymanagement.dao.request.CategoryRequest;
import org.example.librarymanagement.dao.response.CategoryResponse;
import org.example.librarymanagement.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService service;
    @PostMapping("")
    public ResponseEntity<CategoryResponse> create(@RequestBody CategoryRequest request){
        CategoryResponse response = service.saveCategory(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponse>  read(@PathVariable("id") int id){
        CategoryResponse category = service.getCategory(id);
        return new ResponseEntity<>(category,HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryResponse> update(@PathVariable("id") int id,
                                                   @RequestBody CategoryRequest request){
        CategoryResponse response = service.updateCategory(id, request);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") int id){
        service.delete(id);
        return new ResponseEntity<>("Category deleted successfully!",HttpStatus.OK);
    }


}
