package com.Student_library_management_system.Services;

import com.Student_library_management_system.DTOs.requests.AddBookRequestDto;
import com.Student_library_management_system.DTOs.responses.BookByAuthorIdResponseDto;
import com.Student_library_management_system.DTOs.responses.BookResponseDto;
import com.Student_library_management_system.Models.Author;
import com.Student_library_management_system.Models.Book;
import com.Student_library_management_system.Repositories.AuthorRepository;
import com.Student_library_management_system.Repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {
    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private BookRepository bookRepository;


    public String createBook(AddBookRequestDto addBookRequestDto) {

        Author author = authorRepository.findByName(addBookRequestDto.getAuthorName());

        if(author == null){
            author = new Author();
            author.setRating(addBookRequestDto.getAuthorRating());
            author.setName(addBookRequestDto.getAuthorName());
            author.setCountry(addBookRequestDto.getCountry());
        }


        Book book = new Book();

        book.setGenre(addBookRequestDto.getGenre());
        book.setName(addBookRequestDto.getBookName());
        book.setQuantity(addBookRequestDto.getQuantity());
        book.setNoOfBookAvailable(addBookRequestDto.getQuantity());
        book.setAvailable(true);

        // setting the foreign key attr in the child class:
        book.setAuthor(author);

        // we need to update list of booksWritten in parent class
        List<Book> currentBooksWritten = author.getBooksWritten();
        currentBooksWritten.add(book);
        author.setBooksWritten(currentBooksWritten);

//        for(Book book1:author.getBooksWritten()){
//            System.out.println(book1.getName());
//        }

        // book is to be saved but also author
        authorRepository.save(author);

        return "Book-Author added successfully";
    }

    public List<BookResponseDto> getAllBook() {
        List<Book> bookList = bookRepository.findAll();
        List<BookResponseDto> bookResponseDtoList = new ArrayList<>();

        for (Book book : bookList){
            BookResponseDto bookResponseDto = new BookResponseDto();
            bookResponseDto.setId(book.getId());
            bookResponseDto.setAuthorId(book.getAuthor().getId());
            bookResponseDto.setName(book.getName());
            bookResponseDto.setNoOfBookAvailable(book.getNoOfBookAvailable());
            bookResponseDto.setGenre(book.getGenre());
            bookResponseDto.setAvailable(book.isAvailable());
            bookResponseDto.setQuantity(book.getQuantity());


            bookResponseDtoList.add(bookResponseDto);
        }
        return bookResponseDtoList;
    }


    public List<BookByAuthorIdResponseDto> getBookByAuthorId(int authorId) {
        Author author = authorRepository.findById(authorId).get();

        List<Book> bookList = author.getBooksWritten();
        List<BookByAuthorIdResponseDto> bookByAuthorIdResponseDtoList = new ArrayList<>();
        for (Book book : bookList){
            BookByAuthorIdResponseDto bookByAuthorIdResponseDto = new BookByAuthorIdResponseDto();
            bookByAuthorIdResponseDto.setId(book.getId());
            bookByAuthorIdResponseDto.setName(book.getName());
            bookByAuthorIdResponseDto.setGenre(book.getGenre());

            bookByAuthorIdResponseDtoList.add(bookByAuthorIdResponseDto);
        }

        return bookByAuthorIdResponseDtoList;
    }

    public String deleteBookById(int bookId) {

        return "Book deleted successfully";
    }
}
