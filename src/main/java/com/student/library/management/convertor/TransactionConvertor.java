package com.student.library.management.convertor;

import com.student.library.management.dtos.responses.TransactionResponseDto;
import com.student.library.management.models.Transactions;

public class TransactionConvertor {
    public static TransactionResponseDto convertEntityToDto(Transactions transactions){
        return TransactionResponseDto.builder()
                .id(transactions.getId())
                .bookName(transactions.getBook().getName())
                .cardId(transactions.getCard().getId())
                .transactionId(transactions.getTransactionId())
                .transactionDate(transactions.getTransactionDate())
                .transactionStatus(transactions.getTransactionStatus().toString())
                .fine(transactions.getFine())
                .build();
    }
}
