package com.student.library.management.controllers;


import com.student.library.management.dtos.responses.BookResponseDto;
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

    @GetMapping("/fine")
    public int getFineByCardId(@RequestParam int cardId){
        return cardService.getFineByCardId(cardId);
    }

    @GetMapping("/{cardId}")
    public List<BookResponseDto> GetIssuedBookByCardId(@PathVariable int cardId) throws CardNotFoundException {
        return cardService.getIssuedBookByCardId(cardId);
    }

}
