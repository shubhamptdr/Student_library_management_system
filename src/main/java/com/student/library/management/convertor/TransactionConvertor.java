package com.student.library.management.convertor;

import com.student.library.management.dtos.responses.TransactionResponseDto;
import com.student.library.management.models.Transaction;

public class TransactionConvertor {
    public static TransactionResponseDto convertEntityToDto(Transaction transaction){
        return TransactionResponseDto.builder()
                .id(transaction.getId())
                .bookName(transaction.getBook().getName())
                .cardId(transaction.getCard().getId())
                .transactionId(transaction.getTransactionId())
                .transactionDate(transaction.getTransactionDate())
                .transactionStatus(transaction.getTransactionStatus().toString())
                .fine(transaction.getFine())
                .build();
    }
}
