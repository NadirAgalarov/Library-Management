package org.example.librarymanagement.dao.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.librarymanagement.model.Author;
import org.example.librarymanagement.model.Category;
import org.springframework.core.io.Resource;

import java.time.LocalDateTime;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookResponse {
    private Resource image;
    private String title;
    private List<Author> authors;
    private List<Category> category;
    private String language;
    private int availableCopies;
    private int totalCopies;
    private String description;
    private String publisher;
    private LocalDateTime publishedAt;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
