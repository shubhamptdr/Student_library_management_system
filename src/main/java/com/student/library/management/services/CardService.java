package com.student.library.management.services;

import com.student.library.management.convertor.BookConvertor;
import com.student.library.management.dtos.responses.BookResponseDto;
import com.student.library.management.exceptions.CardNotFoundException;
import com.student.library.management.models.Book;
import com.student.library.management.models.Card;
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

    public int getFineByCardId(int cardId) {
        return transactionRepository.findFineByCardId(cardId);
    }

    public List<BookResponseDto> getIssuedBookByCardId(int cardId) throws CardNotFoundException {

        Optional<Card> optionalCard = cardRepository.findById(cardId);

        if (optionalCard.isPresent()) {
            Card card = optionalCard.get();
            List<BookResponseDto> bookResponseDtoList = new ArrayList<>();

            for (Book book : card.getBooksIssued()) {
                BookResponseDto bookResponseDto = BookConvertor.convertEntityToDto(book);

                int availableBookQuantity = book.getQuantity() - book.getNoOfBookAvailable();

                bookResponseDto.setAvailable(availableBookQuantity > 0);

//                if ((book.getQuantity() - book.getNoOfBookAvailable()) > 0) {
//                    bookResponseDto.setAvailable(true);
//                }
//                else {
//                    bookResponseDto.setAvailable(false);
//                }

                bookResponseDtoList.add(bookResponseDto);
            }
            return bookResponseDtoList;
        }
        else {
            throw new CardNotFoundException("No such card: "+ cardId);
        }


    }
}
