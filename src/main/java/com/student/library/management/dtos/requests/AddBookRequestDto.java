package com.student.library.management.dtos.requests;

import com.student.library.management.enums.Genre;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddBookRequestDto {
    private String bookName;
    private Genre genre;
    private int quantity;
    private String authorName;
    private String  country;
    private double authorRating;

}
