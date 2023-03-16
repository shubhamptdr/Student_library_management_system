package com.student.library.management.dtos.responses;


import lombok.*;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransactionResponseDto {

    private int id;
    private String transactionStatus;
    private int fine;
    private String transactionId;
    private Date transactionDate;

    private boolean isIssuedOperation;
    private String bookName;
    private int cardId;

}
