package com.Student_library_management_system.Services;

import com.Student_library_management_system.DTOs.requests.StudentUpdateMobRequestDto;
import com.Student_library_management_system.DTOs.responses.StudentResponseDto;
import com.Student_library_management_system.Enums.CardStatus;
import com.Student_library_management_system.Models.Card;
import com.Student_library_management_system.Models.Student;
import com.Student_library_management_system.Repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {
    @Autowired
    StudentRepository studentRepository;

    public String createStudent( Student student){


        //card should be auto generated when createStudent function is called
        try {
            Card card = new Card();
            card.setCardStatus(CardStatus.ACTIVATED);
            card.setStudentVariableName(student);

            //for student
            student.setCard(card);
            studentRepository.save(student);

        }catch (Exception e){
            return "Student already exist " + e.toString();
        }
        //by cascading effect, child will automatically be saved.

        return "Student and card added successfully";
    }

    public String getByEmail(String email) {
        return studentRepository.findByEmail(email).getName();
    }

    public String updateMobileNo(StudentUpdateMobRequestDto studentUpdateMobRequestDto) {
        // fetch original entity
        Student originalStudent = studentRepository.findById(studentUpdateMobRequestDto.getId()).get();
        // update parameter
        originalStudent.setMobNo(studentUpdateMobRequestDto.getMobNo());
        //
        studentRepository.save(originalStudent);
        return "Student mobile number has been updated successfully";
    }

    public List<StudentResponseDto> getAllStudent() {

        List<Student> studentList =studentRepository.findAll();

        List<StudentResponseDto> studentResponseDtoList = new ArrayList<>();
        for (Student student: studentList){
            StudentResponseDto studentResponseDto= new StudentResponseDto();
            studentResponseDto.setId(student.getId());
            studentResponseDto.setName(student.getName());
            studentResponseDto.setEmail(student.getEmail());
            studentResponseDto.setAge(student.getAge());
            studentResponseDto.setCountry(student.getCountry());
            studentResponseDto.setMobNo(student.getMobNo());
            studentResponseDto.setCardId(student.getCard().getId());

            studentResponseDtoList.add(studentResponseDto);
        }
        return studentResponseDtoList;
    }

}
