package com.student.library.management.services;

import com.student.library.management.convertor.AuthorConvertor;
import com.student.library.management.dtos.requests.AuthorRequestDto;
import com.student.library.management.dtos.responses.AuthorResponseDto;
import com.student.library.management.models.Author;
import com.student.library.management.repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthorService {
    @Autowired
    private AuthorRepository authorRepository;

    public List<AuthorResponseDto> getAllAuthor() {

        List<AuthorResponseDto> authorResponseDtoList = new ArrayList<>();
        for (Author author : authorRepository.findAll()){
            AuthorResponseDto authorResponseDto = AuthorConvertor.convertEntityToResponseDto(author);
            authorResponseDtoList.add(authorResponseDto);
        }

        return authorResponseDtoList;
    }

    public String addAuthor(AuthorRequestDto authorRequestDto) {

        List<Author> authorList = authorRepository.findByName(authorRequestDto.getName().toLowerCase());

        if (!authorList.isEmpty() && authorList.stream().anyMatch(author -> author.getCountry().equals(authorRequestDto.getCountry().toLowerCase()))) {
            return "Author already present";
        }

        Author newAuthor = Author.builder()
                .name(authorRequestDto.getName().toLowerCase())
                .rating(authorRequestDto.getRating())
                .country(authorRequestDto.getCountry().toLowerCase())
                .build();
        authorRepository.save(newAuthor);


        return "Author added successfully";
    }

}
