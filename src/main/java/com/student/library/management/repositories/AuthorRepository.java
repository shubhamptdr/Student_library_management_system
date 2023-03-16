package com.student.library.management.repositories;

import com.student.library.management.models.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author,Integer> {
    Author findByName(String authorName);
}
