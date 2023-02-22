package com.Student_library_management_system.DTOs.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentUpdateMobRequestDto {
    private int id;
    private String mobNo;

}
