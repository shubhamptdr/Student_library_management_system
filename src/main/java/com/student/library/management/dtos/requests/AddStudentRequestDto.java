package com.student.library.management.dtos.requests;


import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddStudentRequestDto {
    private String name;
    private String email;
    private String mobNo;
    private String country;
    private int age;
}
