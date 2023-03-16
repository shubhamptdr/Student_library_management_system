package com.student.library.management.controllers;

import com.student.library.management.dtos.responses.AuthorResponseDto;
import com.student.library.management.services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @GetMapping("/")
    public List<AuthorResponseDto> getAllAuthor(){
        return authorService.getAllAuthor();
    }

}
