package org.example.librarymanagement.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.librarymanagement.dao.request.AuthorRequest;
import org.example.librarymanagement.dao.response.AuthorResponse;
import org.example.librarymanagement.mapper.AuthorMapper;
import org.example.librarymanagement.model.Author;
import org.example.librarymanagement.repository.AuthorRepository;
import org.example.librarymanagement.service.AuthorService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;

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
        System.out.println(author.getImageName());
        File destination=new File(url+"\\"+author.getImageName());
        try {
            authorRequest.getImage().transferTo(destination);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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
}
