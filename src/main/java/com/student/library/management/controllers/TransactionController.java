package com.student.library.management.controllers;

import com.student.library.management.dtos.requests.IssueBookRequestDto;
import com.student.library.management.dtos.requests.ReturnBookRequestDto;
import com.student.library.management.dtos.responses.TransactionResponseDto;
import com.student.library.management.exceptions.CardNotFoundException;
import com.student.library.management.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    @PostMapping("/issue-book")
    public String issueBook(@RequestBody IssueBookRequestDto issueBookRequestDto){
        try {
            return transactionService.issueBook(issueBookRequestDto);
        }catch (Exception e){
            return e.getMessage();
        }
    }

    @PostMapping("/return-book")
    public String returnBook(@RequestBody ReturnBookRequestDto returnBookRequestDto){
        try {
            return transactionService.returnBook(returnBookRequestDto);
        } catch (Exception e) {
            return e.getMessage();
        }
    }


    @GetMapping("/by-card/{cardId}")
    public List<TransactionResponseDto> getTransactionsByCardId(@PathVariable int cardId) throws CardNotFoundException {
        return transactionService.getTransactionsByCardId(cardId);
    }

    @GetMapping("/")
    public List<TransactionResponseDto> getAllTransaction(){
        return transactionService.getAllTransaction();
    }

}
