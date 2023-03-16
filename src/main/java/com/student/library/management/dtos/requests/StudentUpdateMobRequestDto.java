package com.student.library.management.dtos.requests;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentUpdateMobRequestDto {
    private int id;
    private String mobNo;

}
