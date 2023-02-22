package com.Student_library_management_system.DTOs.requests;

import com.Student_library_management_system.Enums.Genre;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
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
