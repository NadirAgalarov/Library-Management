package org.example.librarymanagement.dao.request;

import jakarta.annotation.Nullable;
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
    @Nullable
    private MultipartFile image;
    @NotBlank
    private String title;
    @Nullable
    private List<Integer> authorIds;
    @Nullable
    private List<Integer> categoryIds;
    @Nullable
    private String language;
    @Nullable
    private Integer totalCopies;
    @Nullable
    private Integer availableCopies;
    @Nullable
    private String description;
    @Nullable
    private String publisher;
    @Nullable
    private LocalDateTime publishedAt;
}
