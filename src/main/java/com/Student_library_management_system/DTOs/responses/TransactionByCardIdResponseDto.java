package com.Student_library_management_system.DTOs.responses;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransactionByCardIdResponseDto {

    private int id;
    private String transactionStatus;
    private int fine;
    private String transactionId;
    private Date transactionDate;

    private boolean isIssuedOperation;
    private int bookId;
    private int cardId;

}
