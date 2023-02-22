package com.Student_library_management_system.DTOs.requests;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddStudentRequestDto {
    private String name;
    private String email;
    private String mobNo;
    private String country;
    private int age;
}
