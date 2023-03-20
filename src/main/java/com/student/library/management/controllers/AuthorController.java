package com.student.library.management.controllers;

import com.student.library.management.dtos.requests.AuthorRequestDto;
import com.student.library.management.dtos.responses.AuthorResponseDto;
import com.student.library.management.exceptions.AuthorNotFoundException;
import com.student.library.management.services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    @PostMapping("/")
    public String addAuthor(@RequestBody AuthorRequestDto authorRequestDto){
        return authorService.addAuthor(authorRequestDto);
    }
    @GetMapping("/{authorId}")
    public AuthorResponseDto getAuthorById(@PathVariable int authorId ) throws AuthorNotFoundException {
        return authorService.getAuthorById(authorId);
    }
    @GetMapping("/by-name/{authorName}")
    public List<AuthorResponseDto>  getAuthorsByNane(@PathVariable String authorName ) throws AuthorNotFoundException {
        return authorService.getAuthorsByNane(authorName);
    }


}
