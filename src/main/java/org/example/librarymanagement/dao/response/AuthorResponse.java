package org.example.librarymanagement.dao.response;

import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.librarymanagement.model.Book;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthorResponse {
    private int id;
    private String imageUrl;
    private String firstName;
    private String lastName;
    private List<Book> books= new ArrayList<>();
}
