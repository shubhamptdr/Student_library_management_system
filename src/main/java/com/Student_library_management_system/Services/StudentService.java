package com.Student_library_management_system.Services;

import com.Student_library_management_system.DTOs.requests.AddStudentRequestDto;
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

    public String createStudent( AddStudentRequestDto addStudentRequestDto){


        //card should be auto generated when createStudent function is called
        try {
            // create student entity
            Student student = new Student();
            student.setName(addStudentRequestDto.getName());
            student.setAge(addStudentRequestDto.getAge());
            student.setCountry(addStudentRequestDto.getCountry());
            student.setMobNo(addStudentRequestDto.getMobNo());
            student.setEmail(addStudentRequestDto.getEmail());

            // create card entity
            Card card = new Card();
            card.setCardStatus(CardStatus.ACTIVATED);
            card.setStudentVariableName(student);

            //by cascading effect, child will automatically be saved.
            //for student
            student.setCard(card);
            studentRepository.save(student);

        }catch (Exception e){
            return "Student already exist " + e.toString();
        }

        return "Student and card added successfully";
    }

    public StudentResponseDto getStudentByEmail(String email) {
        Student student = studentRepository.findByEmail(email);
        StudentResponseDto studentResponseDto = new StudentResponseDto();
        studentResponseDto.setId(student.getId());
        studentResponseDto.setName(student.getName());
        studentResponseDto.setEmail(student.getEmail());
        studentResponseDto.setCountry(student.getCountry());
        studentResponseDto.setAge(student.getAge());
        studentResponseDto.setMobNo(student.getMobNo());
        studentResponseDto.setCardId(student.getCard().getId());

        return studentResponseDto;
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

        List<Student> studentList = studentRepository.findAll();

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

    public StudentResponseDto getStudentByCardId(int cardId) {

        Student student = studentRepository.findByCardId(cardId);

        StudentResponseDto studentResponseDto= new StudentResponseDto();
        studentResponseDto.setId(student.getId());
        studentResponseDto.setName(student.getName());
        studentResponseDto.setEmail(student.getEmail());
        studentResponseDto.setAge(student.getAge());
        studentResponseDto.setCountry(student.getCountry());
        studentResponseDto.setMobNo(student.getMobNo());
        studentResponseDto.setCardId(student.getCard().getId());

        return studentResponseDto;
    }
}
