package com.student.library.management.repositories;

import com.student.library.management.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student,Integer> {
    Student findByEmail(String email);
    Student findByCardId(int cardId);
}
