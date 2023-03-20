package com.student.library.management.convertor;

import com.student.library.management.dtos.responses.BookResponseDto;
import com.student.library.management.dtos.responses.CardResponseDto;
import com.student.library.management.models.Book;
import com.student.library.management.models.Card;

public class CardConvertor {
    public static CardResponseDto convertEntityToDto(Card card){
        return CardResponseDto.builder()
                .id(card.getId())
                .createdOn(card.getCreatedOn())
                .updatedOn(card.getUpdatedOn())
                .cardStatus(card.getCardStatus())
                .studentName(card.getStudent().getName())
                .build();
    }
}
