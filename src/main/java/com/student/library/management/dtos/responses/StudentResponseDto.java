package com.student.library.management.dtos.responses;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentResponseDto {
    private int id;
    private int cardId;
    private String name;
    private String email;
    private String mobNo;
    private String country;
    private int age;

}
