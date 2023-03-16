package com.student.library.management.dtos.responses;

import com.student.library.management.enums.Genre;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookResponseDto {
    private int id;
    private String name;
    private Genre genre;
    private int authorId;
    private boolean isAvailable;
    private int quantity;
    private int noOfBookAvailable;
}
