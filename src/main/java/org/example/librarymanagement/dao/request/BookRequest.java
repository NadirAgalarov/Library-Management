package org.example.librarymanagement.dao.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookRequest {
    private MultipartFile image;
    @NotBlank
    private String title;
    private List<Integer> authorIds;

    private List<Integer> categoryIds;
    private String language;
    private int totalCopies;
    private String description;
    private String publisher;
    private LocalDateTime publishedAt;
}
