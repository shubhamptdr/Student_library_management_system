package com.student.library.management.services;

import com.student.library.management.convertor.BookConvertor;
import com.student.library.management.convertor.CardConvertor;
import com.student.library.management.dtos.responses.BookResponseDto;
import com.student.library.management.dtos.responses.CardResponseDto;
import com.student.library.management.exceptions.BookNotFoundException;
import com.student.library.management.exceptions.CardNotFoundException;
import com.student.library.management.models.Book;
import com.student.library.management.models.Card;
import com.student.library.management.repositories.BookRepository;
import com.student.library.management.repositories.CardRepository;
import com.student.library.management.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CardService {
    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private BookRepository bookRepository;

    public int getFineByCardId(int cardId) {
        return transactionRepository.findFineByCardId(cardId);
    }

    public List<CardResponseDto> getCardsByBook(int bookId) throws BookNotFoundException {
        Book book = bookRepository.findById(bookId).orElseThrow(()-> new BookNotFoundException("No such book: "+bookId));

        List<CardResponseDto> cardRepositoryList = new ArrayList<>();
        for ( Card card : book.getListOfCards()){
            CardResponseDto cardResponseDto = CardConvertor.convertEntityToDto(card);
            cardRepositoryList.add(cardResponseDto);
        }

        return cardRepositoryList;
    }
}
