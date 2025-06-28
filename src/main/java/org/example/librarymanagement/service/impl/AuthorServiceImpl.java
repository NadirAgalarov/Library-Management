package org.example.librarymanagement.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.librarymanagement.dao.request.AuthorRequest;
import org.example.librarymanagement.dao.response.AuthorResponse;
import org.example.librarymanagement.exception.AuthorNotFoundException;
import org.example.librarymanagement.mapper.AuthorMapper;
import org.example.librarymanagement.model.Author;
import org.example.librarymanagement.repository.AuthorRepository;
import org.example.librarymanagement.service.AuthorService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authRepo;
    private final AuthorMapper authorMapper;

    @Value("${images.base.url}")
    private  String url;


    @Override
    public AuthorResponse saveAuthor(AuthorRequest authorRequest)  {
        Author author=authorMapper.requestToModel(authorRequest);

        saveImage(authorRequest.getImage(),author.getImageName());

        Author saved = authRepo.save(author);
        AuthorResponse response = authorMapper.modelToResponse(saved);

        response.setImageUrl("http://localhost:8080/author/image/"+author.getImageName());

        return response;
    }

    @Override
    public Resource getImage(String imageName) {
        File file=new File(url+"\\"+imageName);
        Resource image;
        try {
            image=new UrlResource(file.toURI());
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        return image;
    }

    @Override
    public AuthorResponse getAuthor(int id) {
        Author author;
        try {
            author= authRepo.findById(id).orElseThrow(()->
                    new AuthorNotFoundException("Author not found for id: "+id));
        } catch (AuthorNotFoundException e) {
            throw new RuntimeException(e);
        }
        AuthorResponse response=authorMapper.modelToResponse(author);

        return response;
    }

    @Override
    public AuthorResponse update(int id, AuthorRequest request) {
        Author author =authRepo.findById(id).orElseThrow(()-> new
                AuthorNotFoundException("Author not found for id: "+id));
        authorMapper.updateModel(author,request);
        if (request.getImage()!=null && !request.getImage().isEmpty())
            saveImage(request.getImage(),request.getImage().getOriginalFilename());
        Author saved = authRepo.save(author);
        AuthorResponse response = authorMapper.modelToResponse(saved);
        return response;
    }

    @Override
    public void deleteAuthor(int id) {
        Author author = authRepo.findById(id).orElseThrow(() -> new AuthorNotFoundException("Author deleted successfully!"));
        deleteImage(author.getImageName());
        authRepo.deleteById(id);
    }

    private void saveImage(MultipartFile image,String imageName){
        File destination=new File(url+"\\"+imageName);
        try {
            image.transferTo(destination);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private void deleteImage(String imageName){
        File destination=new File(url+"\\"+imageName);
        if (destination!=null && destination.exists()) {
            boolean delete = destination.delete();
        }
    }
}
