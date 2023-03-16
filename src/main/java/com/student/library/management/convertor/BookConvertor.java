package com.student.library.management.convertor;

import com.student.library.management.dtos.responses.BookResponseDto;
import com.student.library.management.models.Book;

public class BookConvertor {
    public static BookResponseDto convertEntityToDto(Book book){
        return BookResponseDto.builder()
                .name(book.getName())
                .id(book.getId())
                .authorId(book.getAuthor().getId())
                .genre(book.getGenre())
                .isAvailable(book.isAvailable())
                .noOfBookAvailable(book.getNoOfBookAvailable())
                .quantity(book.getQuantity())
                .build();
    }
}
