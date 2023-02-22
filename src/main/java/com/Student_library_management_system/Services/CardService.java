package com.Student_library_management_system.Services;

import com.Student_library_management_system.DTOs.responses.BookResponseDto;
import com.Student_library_management_system.Models.Book;
import com.Student_library_management_system.Models.Card;
import com.Student_library_management_system.Repositories.CardRepository;
import com.Student_library_management_system.Repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CardService {
    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private CardRepository cardRepository;

    public int getFineByCardId(int cardId) {
        return transactionRepository.findFineByCardId(cardId);
    }

    public List<BookResponseDto> GetIssuedBookByCardId(int cardId) {
        Card card = cardRepository.findById(cardId).get();
        List<Book> bookList = card.getBooksIssued();
        List<BookResponseDto> bookResponseDtoList = new ArrayList<>();
        for(Book book : bookList){
            BookResponseDto bookResponseDto = new BookResponseDto();
            bookResponseDto.setId(book.getId());
            bookResponseDto.setNoOfBookAvailable(book.getNoOfBookAvailable());
            bookResponseDto.setName(book.getName());
            bookResponseDto.setGenre(book.getGenre());
            bookResponseDto.setAuthorId(book.getAuthor().getId());
            bookResponseDto.setQuantity(book.getQuantity());
            if((book.getQuantity()-book.getNoOfBookAvailable()) > 0){
                bookResponseDto.setAvailable(true);
            }else{
                bookResponseDto.setAvailable(false);
            }


            bookResponseDtoList.add(bookResponseDto);
        }
        return bookResponseDtoList;
    }
}
