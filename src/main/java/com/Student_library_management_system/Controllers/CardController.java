package com.Student_library_management_system.Controllers;


import com.Student_library_management_system.DTOs.responses.BookResponseDto;
import com.Student_library_management_system.Services.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/card")
public class CardController {
    @Autowired
    private CardService cardService;

    @GetMapping("/get-fine")
    public int getFineByCardId(@RequestParam int cardId){
        return cardService.getFineByCardId(cardId);
    }

    @GetMapping("/get-issued-book-by-card")
    public List<BookResponseDto> GetIssuedBookByCardId(@RequestParam int cardId){
        return cardService.GetIssuedBookByCardId(cardId);
    }

}
