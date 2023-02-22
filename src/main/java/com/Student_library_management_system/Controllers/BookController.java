package com.Student_library_management_system.Controllers;

import com.Student_library_management_system.Services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/book")
public class BookController {
    @Autowired
    private BookService bookService;
    @PostMapping("/add")
    public String createBook(@RequestBody AddBookRequestDto addBookRequestDto){
        return bookService.createBook(addBookRequestDto);
    }

}
