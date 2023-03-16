package com.student.library.management.dtos.responses;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthorResponseDto {
    private int id;
    private String name;
    private String  country;
    private double rating;
}
