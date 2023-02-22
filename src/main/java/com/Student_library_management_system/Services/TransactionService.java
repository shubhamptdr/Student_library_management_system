package com.Student_library_management_system.Services;

import com.Student_library_management_system.DTOs.requests.IssueBookRequestDto;
import com.Student_library_management_system.DTOs.requests.ReturnBookRequestDto;
import com.Student_library_management_system.DTOs.responses.TransactionByCardIdResponseDto;
import com.Student_library_management_system.Enums.CardStatus;
import com.Student_library_management_system.Enums.TransactionStatus;
import com.Student_library_management_system.Models.Book;
import com.Student_library_management_system.Models.Card;
import com.Student_library_management_system.Models.Transactions;
import com.Student_library_management_system.Repositories.BookRepository;
import com.Student_library_management_system.Repositories.CardRepository;
import com.Student_library_management_system.Repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CardRepository cardRepository;
    public String issueBook(IssueBookRequestDto issueBookRequestDto) throws Exception {

        int bookId = issueBookRequestDto.getBookId();
        int cardId = issueBookRequestDto.getCardId();

        Book book = bookRepository.findById(bookId).get();
        Card card = cardRepository.findById(cardId).get();

        Transactions transactions = new Transactions();
        transactions.setTransactionStatus(TransactionStatus.PENDING);
        transactions.setIssuedOperation(true);
        transactions.setBook(book);
        transactions.setCard(card);


        if(book == null || !book.isAvailable()){
            transactions.setTransactionStatus(TransactionStatus.FAILED);
            transactionRepository.save(transactions);
            throw new Exception("Book is not available");
        }

        if(card == null || card.getCardStatus() != CardStatus.ACTIVATED){
            transactions.setTransactionStatus(TransactionStatus.FAILED);
            transactionRepository.save(transactions);
            throw new Exception("Card is not ACTIVATED");
        }

        transactions.setTransactionStatus(TransactionStatus.SUCCESS);

        // update issuedBook for card
        List<Book> issuedBook = card.getBooksIssued();
        issuedBook.add(book);
        card.setBooksIssued(issuedBook);

        // update in listOfCard for book
        List<Card> cardList = book.getListOfCards();
        cardList.add(card);
        book.setListOfCards(cardList);

        //update in listOfTransaction for card
        List<Transactions> listOfTransactionForCard = card.getListOfTransaction();
        listOfTransactionForCard.add(transactions);
        card.setListOfTransaction(listOfTransactionForCard);


        book.setNoOfBookAvailable(book.getNoOfBookAvailable()-1);
        if(book.getNoOfBookAvailable() == 0){
            book.setAvailable(false);
        }
        cardRepository.save(card);

        return "Book issued";
    }

    public String returnBook(ReturnBookRequestDto returnBookRequestDto) throws Exception {

        int cardId = returnBookRequestDto.getCardId();
        int bookId = returnBookRequestDto.getBookId();


        Card card = cardRepository.findById(cardId).get();
        Book book = bookRepository.findById(bookId).get();


        Transactions transactions = new Transactions();
        transactions.setTransactionStatus(TransactionStatus.PENDING);
        System.out.println("level 1");
        if(book == null ){
            transactions.setTransactionStatus(TransactionStatus.FAILED);
            transactionRepository.save(transactions);
            throw new Exception("Enter wrong bookId");
        }
        System.out.println("level 2");

        if(card == null || card.getCardStatus() != CardStatus.ACTIVATED){
            transactions.setTransactionStatus(TransactionStatus.FAILED);
            transactionRepository.save(transactions);
            throw new Exception("Enter wrong cardId");
        }
        System.out.println("level 3");


        transactions.setTransactionStatus(TransactionStatus.SUCCESS);

        // update issuedBook for card
        List<Book> issuedBook = card.getBooksIssued();
        issuedBook.remove(book);
        card.setBooksIssued(issuedBook);

        System.out.println("level 4");

        // fine calculation
        Transactions currTrans = transactionRepository.findByBookAndCard(bookId,cardId);
        Date dateBefore = currTrans.getTransactionDate();
        Date datAfter = new Date();
        long timeDiff = Math.abs(datAfter.getTime() - dateBefore.getTime());
        long daysDiff = TimeUnit.DAYS.convert(timeDiff, TimeUnit.MILLISECONDS);

        System.out.println("The number of days between dates: " + daysDiff);

        if(daysDiff > 15){
            int fine = (int) ((daysDiff - 15) * 10);   // Rs 10/- per day after 15 day
            transactions.setFine(fine);
        }

        System.out.println("level 5");

        // update listOfTransaction for card
        List<Transactions> listOfTransactionForCard = card.getListOfTransaction();
        listOfTransactionForCard.add(transactions);
        card.setListOfTransaction(listOfTransactionForCard);

        book.setNoOfBookAvailable(book.getNoOfBookAvailable()+1);

        System.out.println("level 6");
        cardRepository.save(card);

        return "book returned successfully and fine: Rs " + transactions.getFine()+"/-.";
    }


    public List<TransactionByCardIdResponseDto> getTransactionsByCardId(int cardId) {
        Card card = cardRepository.findById(cardId).get();
        List<Transactions> transactionsList = card.getListOfTransaction();
        List<TransactionByCardIdResponseDto> transactionByCardIdResponseDtoList = new ArrayList<>();

        for (Transactions transactions : transactionsList){
            TransactionByCardIdResponseDto transactionByCardIdResponseDto = new TransactionByCardIdResponseDto();
            transactionByCardIdResponseDto.setTransactionDate(transactions.getTransactionDate());
            transactionByCardIdResponseDto.setId(transactions.getId());
            transactionByCardIdResponseDto.setFine(transactions.getFine());
            transactionByCardIdResponseDto.setBookId(transactions.getBook().getId());
            transactionByCardIdResponseDto.setCardId(transactions.getCard().getId());
            transactionByCardIdResponseDto.setTransactionStatus(transactions.getTransactionStatus().toString());
            transactionByCardIdResponseDto.setTransactionId(transactions.getTransactionId());


            transactionByCardIdResponseDtoList.add(transactionByCardIdResponseDto);
        }
        return transactionByCardIdResponseDtoList;
    }

    public List<TransactionByCardIdResponseDto> getAllTransaction() {
        List<Transactions> transactionsList = transactionRepository.findAll();
        List<TransactionByCardIdResponseDto> transactionByCardIdResponseDtoList = new ArrayList<>();

        for (Transactions transactions : transactionsList){
            TransactionByCardIdResponseDto transactionByCardIdResponseDto = new TransactionByCardIdResponseDto();
            transactionByCardIdResponseDto.setTransactionDate(transactions.getTransactionDate());
            transactionByCardIdResponseDto.setId(transactions.getId());
            transactionByCardIdResponseDto.setFine(transactions.getFine());
            transactionByCardIdResponseDto.setTransactionStatus(transactions.getTransactionStatus().toString());
            transactionByCardIdResponseDto.setTransactionId(transactions.getTransactionId());

            Book book = transactions.getBook();
            Card card = transactions.getCard();

            if(book == null){
                transactionByCardIdResponseDto.setBookId(0);
            }else{
                transactionByCardIdResponseDto.setBookId(transactions.getBook().getId());
            }

            if(card == null){
                transactionByCardIdResponseDto.setCardId(0);
            }else {
                transactionByCardIdResponseDto.setCardId(transactions.getCard().getId());
            }

            transactionByCardIdResponseDtoList.add(transactionByCardIdResponseDto);
        }
        return transactionByCardIdResponseDtoList;
    }
}
