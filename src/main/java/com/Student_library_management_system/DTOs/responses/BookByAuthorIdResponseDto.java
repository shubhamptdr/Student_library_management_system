package com.Student_library_management_system.DTOs.responses;

import com.Student_library_management_system.Enums.Genre;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookByAuthorIdResponseDto {
    private int id;
    private String name;
    private Genre genre;
}
