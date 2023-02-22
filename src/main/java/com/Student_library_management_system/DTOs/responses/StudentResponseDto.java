package com.Student_library_management_system.DTOs.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
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
