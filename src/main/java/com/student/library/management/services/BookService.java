package com.student.library.management.services;

import com.student.library.management.convertor.BookConvertor;
import com.student.library.management.dtos.requests.AddBookRequestDto;
import com.student.library.management.exceptions.AuthorNotFoundException;
import com.student.library.management.models.Book;
import com.student.library.management.repositories.AuthorRepository;
import com.student.library.management.dtos.responses.BookResponseDto;
import com.student.library.management.models.Author;
import com.student.library.management.repositories.BookRepository;
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


    public String createBook(AddBookRequestDto addBookRequestDto) throws AuthorNotFoundException {
        // fetch author
        Author author = authorRepository.findByName(addBookRequestDto.getAuthorName());

        if (author == null) {
            throw new AuthorNotFoundException(addBookRequestDto.getAuthorName());
        }

        // create entity
        author = Author.builder()
                .name(addBookRequestDto.getAuthorName())
                .rating(addBookRequestDto.getAuthorRating())
                .country(addBookRequestDto.getCountry())
                .build();

        // create book entity
        Book book = Book.builder()
                .name(addBookRequestDto.getBookName())
                .quantity(addBookRequestDto.getQuantity())
                .genre(addBookRequestDto.getGenre())
                .noOfBookAvailable(addBookRequestDto.getQuantity())
                .isAvailable(true)
                .author(author)
                .build();


        // we need to update list of booksWritten in parent class
        List<Book> currentBooksWritten = author.getBooksWritten();
        currentBooksWritten.add(book);
        author.setBooksWritten(currentBooksWritten);

        // book is to be saved but also author
        authorRepository.save(author);

        return "Book-Author added successfully";
    }

    public List<BookResponseDto> getAllBook() {

        List<BookResponseDto> bookResponseDtoList = new ArrayList<>();

        for (Book book : bookRepository.findAll()) {
            BookResponseDto bookResponseDto = BookConvertor.convertEntityToDto(book);
            bookResponseDtoList.add(bookResponseDto);
        }
        return bookResponseDtoList;
    }


    public List<BookResponseDto> getBookByAuthorId(int authorId) throws AuthorNotFoundException {
        // fetch
        Author author = authorRepository.findById(authorId)
                .orElseThrow(() -> new AuthorNotFoundException(String.valueOf(authorId)));

        List<BookResponseDto> bookResponseDtoList = new ArrayList<>();

        for (Book book : author.getBooksWritten()) {
            BookResponseDto bookResponseDto = BookConvertor.convertEntityToDto(book);
            bookResponseDtoList.add(bookResponseDto);
        }

        return bookResponseDtoList;
    }


}
