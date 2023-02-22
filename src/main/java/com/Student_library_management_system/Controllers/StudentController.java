package com.Student_library_management_system.Controllers;

import com.Student_library_management_system.DTOs.requests.AddBookRequestDto;
import com.Student_library_management_system.DTOs.requests.AddStudentRequestDto;
import com.Student_library_management_system.DTOs.requests.StudentUpdateMobRequestDto;
import com.Student_library_management_system.DTOs.responses.StudentResponseDto;
import com.Student_library_management_system.Services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @PostMapping("/add")
    public String createStudent(@RequestBody AddStudentRequestDto addStudentRequestDto){
        return studentService.createStudent(addStudentRequestDto);
    }


    @GetMapping("/get_user/{email}")
    public String getByEmail(@PathVariable String email){
        return studentService.getByEmail(email);
    }

    @GetMapping("/getAllStudent")
    public List<StudentResponseDto> getAllStudent(){
        return studentService.getAllStudent();
    }

    @PutMapping("/update-mobile")
    public String updateMobileNo(@RequestBody StudentUpdateMobRequestDto studentUpdateMobRequestDto){
        return studentService.updateMobileNo(studentUpdateMobRequestDto);
    }

}
