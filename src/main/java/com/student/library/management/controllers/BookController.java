package com.student.library.management.controllers;

import com.student.library.management.dtos.requests.AddBookRequestDto;
import com.student.library.management.dtos.responses.BookResponseDto;
import com.student.library.management.exceptions.AuthorNotFoundException;
import com.student.library.management.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {
    @Autowired
    private BookService bookService;
    @PostMapping("/")
    public String createBook(@RequestBody AddBookRequestDto addBookRequestDto) throws AuthorNotFoundException {
        return bookService.createBook(addBookRequestDto);
    }

    @GetMapping("/")
    public List<BookResponseDto> getAllBook(){
        return bookService.getAllBook();
    }

    @GetMapping("/by-author")
    public List<BookResponseDto> getBookByAuthorId(@RequestParam int authorId) throws AuthorNotFoundException {
        return bookService.getBookByAuthorId(authorId);
    }

}
