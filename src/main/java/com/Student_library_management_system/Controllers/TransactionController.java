package com.Student_library_management_system.Controllers;

import com.Student_library_management_system.DTOs.requests.IssueBookRequestDto;
import com.Student_library_management_system.DTOs.requests.ReturnBookRequestDto;
import com.Student_library_management_system.DTOs.responses.TransactionByCardIdResponseDto;
import com.Student_library_management_system.Services.TransactionService;
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


    @GetMapping("/get-transactions-by-card")
    public List<TransactionByCardIdResponseDto> getTransactionsByCardId(@RequestParam int cardId){
        return transactionService.getTransactionsByCardId(cardId);
    }

    @GetMapping("/get-transactions")
    public List<TransactionByCardIdResponseDto> getAllTransaction(){
        return transactionService.getAllTransaction();
    }

}
