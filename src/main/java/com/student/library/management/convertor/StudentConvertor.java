package com.student.library.management.convertor;

import com.student.library.management.dtos.requests.AddStudentRequestDto;
import com.student.library.management.dtos.responses.StudentResponseDto;
import com.student.library.management.models.Student;

public class StudentConvertor {
    public static Student convertDtoToEntity(AddStudentRequestDto addStudentRequestDto){
        return Student.builder()
                .name(addStudentRequestDto.getName().toLowerCase())
                .age(addStudentRequestDto.getAge())
                .email(addStudentRequestDto.getEmail())
                .country(addStudentRequestDto.getCountry().toLowerCase())
                .mobNo(addStudentRequestDto.getMobNo())
                .build();
    }
    public static StudentResponseDto convertEntityToDto(Student student){
        return StudentResponseDto.builder()
                .id(student.getId())
                .name(student.getName())
                .email(student.getEmail())
                .country(student.getCountry())
                .age(student.getAge())
                .mobNo(student.getMobNo())
                .cardId(student.getCard().getId())
                .build();
    }
}
