package org.example.librarymanagement.dao.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthorRequest {
    private MultipartFile image;
    private  String firstName;

    private String lastName;
}
