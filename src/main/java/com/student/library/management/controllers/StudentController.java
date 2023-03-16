package com.student.library.management.controllers;

import com.student.library.management.dtos.requests.StudentUpdateMobRequestDto;
import com.student.library.management.dtos.requests.AddStudentRequestDto;
import com.student.library.management.dtos.responses.StudentResponseDto;
import com.student.library.management.exceptions.ResourceAlreadyExistsException;
import com.student.library.management.exceptions.StudentNotFoundException;
import com.student.library.management.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @PostMapping("/")
    public String createStudent(@RequestBody AddStudentRequestDto addStudentRequestDto) throws ResourceAlreadyExistsException {
        return studentService.createStudent(addStudentRequestDto);
    }

    @GetMapping("/by-email/{email}")
    public StudentResponseDto getStudentByEmail(@PathVariable String email) throws StudentNotFoundException {
        return studentService.getStudentByEmail(email);
    }

    @GetMapping("/by-cardId/{cardId}")
    public StudentResponseDto getStudentByCardId(@PathVariable int cardId) throws StudentNotFoundException {
        return studentService.getStudentByCardId(cardId);
    }

    @GetMapping("/")
    public List<StudentResponseDto> getAllStudent(){
        return studentService.getAllStudent();
    }

    @PutMapping("/")
    public String updateMobileNo(@RequestBody StudentUpdateMobRequestDto studentUpdateMobRequestDto) throws StudentNotFoundException {
        return studentService.updateMobileNo(studentUpdateMobRequestDto);
    }



}
