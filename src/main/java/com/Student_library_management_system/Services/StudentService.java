package com.Student_library_management_system.Services;

import com.Student_library_management_system.Enums.CardStatus;
import com.Student_library_management_system.Models.Card;
import com.Student_library_management_system.Models.Student;
import com.Student_library_management_system.Repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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


}
