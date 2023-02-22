package com.Student_library_management_system.Controllers;


import com.Student_library_management_system.Services.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/card")
public class CardController {
    @Autowired
    private CardService cardService;

    @GetMapping("/getFine")
    public int getFineByCardId(@RequestParam int cardId){
        return cardService.getFineByCardId(cardId);
    }

}
