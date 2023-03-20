package com.student.library.management.controllers;

import com.student.library.management.dtos.requests.bookRequestDto;
import com.student.library.management.dtos.responses.BookResponseDto;
import com.student.library.management.exceptions.AuthorNotFoundException;
import com.student.library.management.exceptions.CardNotFoundException;
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

    public String createBook(@RequestBody bookRequestDto bookRequestDto) throws AuthorNotFoundException {
        return bookService.createBook(bookRequestDto);
    }

    @GetMapping("/")
    public List<BookResponseDto> getAllBook(){
        return bookService.getAllBook();
    }

    @GetMapping("/by-author/{authorId}")
    public List<BookResponseDto> getBookByAuthorId(@PathVariable int authorId) throws AuthorNotFoundException {
        return bookService.getBookByAuthorId(authorId);
    }

    @GetMapping("/by-card/{cardId}")
    public List<BookResponseDto> getIssuedBookByCardId(@PathVariable int cardId) throws CardNotFoundException {
        return bookService.getIssuedBookByCardId(cardId);
    }

}
