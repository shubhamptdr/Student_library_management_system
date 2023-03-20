package com.student.library.management.services;

import com.student.library.management.convertor.TransactionConvertor;
import com.student.library.management.dtos.requests.IssueBookRequestDto;
import com.student.library.management.dtos.requests.ReturnBookRequestDto;
import com.student.library.management.dtos.responses.TransactionResponseDto;
import com.student.library.management.enums.CardStatus;
import com.student.library.management.enums.TransactionStatus;
import com.student.library.management.exceptions.BookNotFoundException;
import com.student.library.management.exceptions.CardNotFoundException;
import com.student.library.management.models.Book;
import com.student.library.management.models.Card;
import com.student.library.management.models.Transaction;
import com.student.library.management.repositories.BookRepository;
import com.student.library.management.repositories.CardRepository;
import com.student.library.management.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CardRepository cardRepository;

    private static final String MESSAGE_CARD = "No such card: ";

    public String issueBook(IssueBookRequestDto issueBookRequestDto) throws Exception {

        int bookId = issueBookRequestDto.getBookId();
        int cardId = issueBookRequestDto.getCardId();

        Book book = bookRepository.findById(bookId).orElseThrow(() -> new BookNotFoundException("No such book: "+bookId));
        Card card = cardRepository.findById(cardId).orElseThrow(() -> new CardNotFoundException(MESSAGE_CARD +cardId));

        Transaction transaction = Transaction.builder()
                .transactionStatus(TransactionStatus.PENDING)
                .transactionId(UUID.randomUUID().toString())
                .book(book)
                .card(card)
                .build();

        transaction.setIssuedOperation(true);

        if(!book.isAvailable()){
            transaction.setTransactionStatus(TransactionStatus.FAILED);
            transactionRepository.save(transaction);
            throw new BookNotFoundException("Book is not available");
        }

        if(card.getCardStatus() != CardStatus.ACTIVATED){
            transaction.setTransactionStatus(TransactionStatus.FAILED);
            transactionRepository.save(transaction);
            throw new CardNotFoundException("Card is not active");
        }

        transaction.setTransactionStatus(TransactionStatus.SUCCESS);

        // update attr. for mapped entity because we have to remove later to
        card.getBooksIssued().add(book);
        book.getListOfCards().add(card);


        book.setNoOfBookAvailable(book.getNoOfBookAvailable()-1);

        if(book.getNoOfBookAvailable() == 0){
            book.setAvailable(false);
        }
        transactionRepository.save(transaction);

        return "Book issued successfully";
    }

    public String returnBook(ReturnBookRequestDto returnBookRequestDto) throws Exception {

        int cardId = returnBookRequestDto.getCardId();
        int bookId = returnBookRequestDto.getBookId();


        Book book = bookRepository.findById(bookId).orElseThrow(() -> new BookNotFoundException("No such book: "+bookId));
        Card card = cardRepository.findById(cardId).orElseThrow(() -> new CardNotFoundException(MESSAGE_CARD +cardId));



        Transaction transaction = new Transaction();
        transaction.setTransactionStatus(TransactionStatus.PENDING);
        transaction.setTransactionId(UUID.randomUUID().toString());


        

        if(card.getCardStatus() != CardStatus.ACTIVATED){
            transaction.setTransactionStatus(TransactionStatus.FAILED);
            transactionRepository.save(transaction);
            throw new CardNotFoundException("Enter wrong cardId");
        }


        transaction.setTransactionStatus(TransactionStatus.SUCCESS);
        transaction.setBook(book);
        transaction.setCard(card);

        // update bookList for card & book
        if(card.getBooksIssued().contains(book) && book.getListOfCards().contains(card)){
         card.getBooksIssued().remove(book);
         book.getListOfCards().remove(card);
        }else{
            throw new BookNotFoundException("Book already returned");
        }


        // fine calculation
        Transaction currTrans = transactionRepository.findByBookAndCard(bookId,cardId);
        Date dateBefore = currTrans.getTransactionDate();
        Date datAfter = new Date();
        long timeDiff = Math.abs(datAfter.getTime() - dateBefore.getTime());
        long daysDiff = TimeUnit.DAYS.convert(timeDiff, TimeUnit.MILLISECONDS);

        if(daysDiff > 15){
            int fine = (int) ((daysDiff - 15) * 10);   // Rs 10/- per day after 15 day
            transaction.setFine(fine);
        }



        book.setNoOfBookAvailable(book.getNoOfBookAvailable()+1);
        book.setAvailable(true);

        transactionRepository.save(transaction);
        return "book returned successfully and fine: Rs " + transaction.getFine()+"/-.";
    }


    public List<TransactionResponseDto> getTransactionsByCardId(int cardId) throws CardNotFoundException {
        Card card = cardRepository.findById(cardId).orElseThrow(()-> new CardNotFoundException("No such card: "+cardId));

        List<TransactionResponseDto> transactionResponseDtoList = new ArrayList<>();

        for (Transaction transaction : card.getListOfTransaction()){
            TransactionResponseDto transactionResponseDto = TransactionConvertor.convertEntityToDto(transaction);
            transactionResponseDtoList.add(transactionResponseDto);
        }
        return transactionResponseDtoList;
    }

    public List<TransactionResponseDto> getAllTransaction() {
        List<TransactionResponseDto> transactionResponseDtoList = new ArrayList<>();

        for (Transaction transaction : transactionRepository.findAll()){
            TransactionResponseDto transactionResponseDto = TransactionConvertor.convertEntityToDto(transaction);

            Book book = transaction.getBook();
            Card card = transaction.getCard();

            transactionResponseDto.setBookName((book==null) ? "Null" : transaction.getBook().getName());
            transactionResponseDto.setCardId((card==null) ? 0 : transaction.getCard().getId());


            transactionResponseDtoList.add(transactionResponseDto);
        }
        return transactionResponseDtoList;
    }
}
