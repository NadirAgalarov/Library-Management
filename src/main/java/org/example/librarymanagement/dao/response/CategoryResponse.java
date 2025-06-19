package org.example.librarymanagement.dao.response;

import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.librarymanagement.model.Book;

import java.util.ArrayList;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryResponse {
    private int id;
    private String categoryName;
    private List<Book> books=new ArrayList<>();
}
