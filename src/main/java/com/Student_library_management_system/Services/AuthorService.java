package com.Student_library_management_system.Services;

import com.Student_library_management_system.DTOs.responses.AuthorResponseDto;
import com.Student_library_management_system.Models.Author;
import com.Student_library_management_system.Repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthorService {
    @Autowired
    private AuthorRepository authorRepository;

    public List<AuthorResponseDto> getAllAuthor() {
        List<Author> authorList = authorRepository.findAll();
        List<AuthorResponseDto> authorResponseDtoList = new ArrayList<>();
        for (Author author : authorList){
            AuthorResponseDto authorResponseDto = new AuthorResponseDto();
            authorResponseDto.setId(author.getId());
            authorResponseDto.setName(author.getName());
            authorResponseDto.setCountry(author.getCountry());
            authorResponseDto.setRating(author.getRating());

            authorResponseDtoList.add(authorResponseDto);
        }

        return authorResponseDtoList;
    }
}
