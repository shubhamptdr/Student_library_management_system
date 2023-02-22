package com.Student_library_management_system.Controllers;

import com.Student_library_management_system.DTOs.responses.AuthorResponseDto;
import com.Student_library_management_system.Services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/author")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @GetMapping("/get-authors")
    public List<AuthorResponseDto> getAllAuthor(){
        return authorService.getAllAuthor();
    }

}
