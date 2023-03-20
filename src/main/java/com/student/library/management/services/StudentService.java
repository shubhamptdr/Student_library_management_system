package com.student.library.management.services;

import com.student.library.management.convertor.StudentConvertor;
import com.student.library.management.dtos.requests.StudentUpdateMobRequestDto;
import com.student.library.management.enums.CardStatus;
import com.student.library.management.exceptions.ResourceAlreadyExistsException;
import com.student.library.management.exceptions.StudentNotFoundException;
import com.student.library.management.models.Card;
import com.student.library.management.models.Student;
import com.student.library.management.dtos.requests.AddStudentRequestDto;
import com.student.library.management.dtos.responses.StudentResponseDto;
import com.student.library.management.repositories.CardRepository;
import com.student.library.management.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {
    @Autowired
    StudentRepository studentRepository;

    @Autowired
    CardRepository cardRepository;

    public String createStudent( AddStudentRequestDto addStudentRequestDto) throws ResourceAlreadyExistsException {


        //card should be auto generated when createStudent function is called
        try {
            // create student entity
            Student student = StudentConvertor.convertDtoToEntity(addStudentRequestDto);

            // create card entity
            Card card = Card.builder()
                    .cardStatus(CardStatus.ACTIVATED)
                    .student(student)
                    .build();

            //we can save both by cascading effect, child will automatically be saved when save parent.
            studentRepository.save(student);
            cardRepository.save(card);

        }catch (Exception e){
            throw new ResourceAlreadyExistsException("Student already exists");
        }

        return "Student and card added successfully";
    }

    public StudentResponseDto getStudentByEmail(String email) throws StudentNotFoundException {
        //fetch
        Student student = studentRepository.findByEmail(email);

        if(student == null)
            throw new StudentNotFoundException();

        // create response dto
        return StudentConvertor.convertEntityToDto(student);
    }

    public String updateMobileNo(StudentUpdateMobRequestDto studentUpdateMobRequestDto) throws StudentNotFoundException {
        // fetch original entity
        Student originalStudent = studentRepository.findById(studentUpdateMobRequestDto.getId()).orElseThrow(StudentNotFoundException::new);
        // update parameter
        originalStudent.setMobNo(studentUpdateMobRequestDto.getMobNo());
        //save
        studentRepository.save(originalStudent);
        return "Student mobile number has been updated successfully";
    }

    public List<StudentResponseDto> getAllStudent() {

        List<StudentResponseDto> studentResponseDtoList = new ArrayList<>();

        for (Student student: studentRepository.findAll()){
            StudentResponseDto studentResponseDto= StudentConvertor.convertEntityToDto(student);
            studentResponseDtoList.add(studentResponseDto);
        }
        return studentResponseDtoList;
    }

    public StudentResponseDto getStudentByCardId(int cardId) throws StudentNotFoundException {

        Student student = studentRepository.findByCardId(cardId);
        if(student == null)
            throw new StudentNotFoundException();

        return StudentConvertor.convertEntityToDto(student);
    }
}
