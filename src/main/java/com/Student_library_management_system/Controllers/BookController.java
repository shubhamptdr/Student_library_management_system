package com.Student_library_management_system.Controllers;

import com.Student_library_management_system.DTOs.requests.AddBookRequestDto;
import com.Student_library_management_system.DTOs.responses.BookByAuthorIdResponseDto;
import com.Student_library_management_system.DTOs.responses.BookResponseDto;
import com.Student_library_management_system.Services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {
    @Autowired
    private BookService bookService;
    @PostMapping("/add")
    public String createBook(@RequestBody AddBookRequestDto addBookRequestDto){
        return bookService.createBook(addBookRequestDto);
    }

    @GetMapping("/get-books")
    public List<BookResponseDto> getAllBook(){
        return bookService.getAllBook();
    }


    @GetMapping("/get-books-by-author")
    public List<BookByAuthorIdResponseDto> getBookByAuthorId(@RequestParam int authorId){
        return bookService.getBookByAuthorId(authorId);
    }

    @DeleteMapping("/delete-book")
    public String deleteBookById(@RequestParam int bookId){
        return bookService.deleteBookById(bookId);
    }

}
