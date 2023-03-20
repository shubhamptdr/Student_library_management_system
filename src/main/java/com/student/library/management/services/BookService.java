package com.student.library.management.services;

import com.student.library.management.convertor.BookConvertor;
import com.student.library.management.dtos.requests.bookRequestDto;
import com.student.library.management.exceptions.AuthorNotFoundException;
import com.student.library.management.exceptions.CardNotFoundException;
import com.student.library.management.models.Book;
import com.student.library.management.models.Card;
import com.student.library.management.repositories.AuthorRepository;
import com.student.library.management.dtos.responses.BookResponseDto;
import com.student.library.management.models.Author;
import com.student.library.management.repositories.BookRepository;
import com.student.library.management.repositories.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CardRepository cardRepository;

    public String createBook(bookRequestDto bookRequestDto) throws AuthorNotFoundException {
        // fetch author
        List<Author> authorList = authorRepository.findByName(bookRequestDto.getAuthorName().toLowerCase());

        if (authorList.isEmpty()) {
            throw new AuthorNotFoundException(bookRequestDto.getAuthorName());
        }

        Optional<Author> result = authorList.stream()
                .filter(author -> author.getCountry().equals(bookRequestDto.getCountry().toLowerCase()))
                .findFirst();

        Author author = result.orElseThrow(() -> new AuthorNotFoundException("No author found for country " + bookRequestDto.getCountry()));


        // create book entity
        Book book = Book.builder()
                .name(bookRequestDto.getBookName())
                .quantity(bookRequestDto.getQuantity())
                .genre(bookRequestDto.getGenre())
                .noOfBookAvailable(bookRequestDto.getQuantity())
                .isAvailable(true)
                .author(author)
                .build();


        // we need to update list of booksWritten in parent class
        author.getBooksWritten().add(book);
        authorRepository.save(author);


        bookRepository.save(book);

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


    public List<BookResponseDto> getIssuedBookByCardId(int cardId) throws CardNotFoundException {

        Optional<Card> optionalCard = cardRepository.findById(cardId);

        if (optionalCard.isPresent()) {
            Card card = optionalCard.get();
            List<BookResponseDto> bookResponseDtoList = new ArrayList<>();

            for (Book book : card.getBooksIssued()) {
                BookResponseDto bookResponseDto = BookConvertor.convertEntityToDto(book);

                bookResponseDto.setAvailable((book.getNoOfBookAvailable()) > 0);

                bookResponseDtoList.add(bookResponseDto);
            }
            return bookResponseDtoList;
        }
        else {
            throw new CardNotFoundException("No such card: "+ cardId);
        }


    }

}
