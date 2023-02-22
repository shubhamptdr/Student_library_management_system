package com.Student_library_management_system.Repositories;

import com.Student_library_management_system.Models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student,Integer> {
    Student findByEmail(String email);
    Student findByCardId(int cardId);
}
