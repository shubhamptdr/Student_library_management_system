package com.student.library.management.convertor;

import com.student.library.management.dtos.responses.AuthorResponseDto;
import com.student.library.management.models.Author;

public class AuthorConvertor {
    public static AuthorResponseDto convertEntityToResponseDto(Author author){
        return AuthorResponseDto.builder()
                .id(author.getId())
                .name(author.getName())
                .country(author.getCountry())
                .rating(author.getRating())
                .build();
    }
}
