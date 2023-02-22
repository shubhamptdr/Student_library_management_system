package com.Student_library_management_system.DTOs.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthorResponseDto {
    private int id;
    private String name;
    private String  country;
    private double rating;
}
