package com.student.library.management.controllers;


import com.student.library.management.dtos.responses.BookResponseDto;
import com.student.library.management.dtos.responses.CardResponseDto;
import com.student.library.management.exceptions.BookNotFoundException;
import com.student.library.management.exceptions.CardNotFoundException;
import com.student.library.management.services.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cards")
public class CardController {
    @Autowired
    private CardService cardService;

    @GetMapping("/fine/{cardId}")
    public int getFineByCardId(@PathVariable int cardId){
        return cardService.getFineByCardId(cardId);
    }

    @GetMapping("/{bookId}")
    public List<CardResponseDto> getCardsByBook(@PathVariable int bookId) throws BookNotFoundException {
        return cardService.getCardsByBook(bookId);
    }
}
