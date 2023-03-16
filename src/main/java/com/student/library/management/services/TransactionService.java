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
import com.student.library.management.models.Transactions;
import com.student.library.management.repositories.BookRepository;
import com.student.library.management.repositories.CardRepository;
import com.student.library.management.repositories.TransactionRepository;
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

        Transactions transactions = Transactions.builder()
                .transactionStatus(TransactionStatus.PENDING)
                .isIssuedOperation(true)
                .book(book)
                .card(card)
                .build();


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
        card.getBooksIssued().add(book);

        // update in listOfCard for book
        book.getListOfCards().add(card);

        //update in listOfTransaction for card
        card.getListOfTransaction().add(transactions);

        book.setNoOfBookAvailable(book.getNoOfBookAvailable()-1);

        if(book.getNoOfBookAvailable() == 0){
            book.setAvailable(false);
        }
        cardRepository.save(card);

        return "Book issued successfully";
    }

    public String returnBook(ReturnBookRequestDto returnBookRequestDto) throws Exception {

        int cardId = returnBookRequestDto.getCardId();
        int bookId = returnBookRequestDto.getBookId();


        Card card = cardRepository.findById(cardId).get();
        Book book = bookRepository.findById(bookId).get();


        Transactions transactions = new Transactions();
        transactions.setTransactionStatus(TransactionStatus.PENDING);

        if(book == null ){
            transactions.setTransactionStatus(TransactionStatus.FAILED);
            transactionRepository.save(transactions);
            throw new BookNotFoundException("Enter wrong bookId");
        }

        if(card == null || card.getCardStatus() != CardStatus.ACTIVATED){
            transactions.setTransactionStatus(TransactionStatus.FAILED);
            transactionRepository.save(transactions);
            throw new CardNotFoundException("Enter wrong cardId");
        }


        transactions.setTransactionStatus(TransactionStatus.SUCCESS);

        // update issuedBook for card
//        List<Book> issuedBook = card.getBooksIssued();
//        issuedBook.remove(book);
//        card.setBooksIssued(issuedBook);
        card.getBooksIssued().remove(book);

//        List<Card> cardList = book.getListOfCards();
//        cardList.remove(card);
//        book.setListOfCards(cardList);
        book.getListOfCards().remove(card);



        // fine calculation
        Transactions currTrans = transactionRepository.findByBookAndCard(bookId,cardId);
        Date dateBefore = currTrans.getTransactionDate();
        Date datAfter = new Date();
        long timeDiff = Math.abs(datAfter.getTime() - dateBefore.getTime());
        long daysDiff = TimeUnit.DAYS.convert(timeDiff, TimeUnit.MILLISECONDS);

//        System.out.println("The number of days between dates: " + daysDiff);

        if(daysDiff > 15){
            int fine = (int) ((daysDiff - 15) * 10);   // Rs 10/- per day after 15 day
            transactions.setFine(fine);
        }


        // update listOfTransaction for card
//        List<Transactions> listOfTransactionForCard = card.getListOfTransaction();
//        listOfTransactionForCard.add(transactions);
//        card.setListOfTransaction(listOfTransactionForCard);

        card.getListOfTransaction().add(transactions);

        book.setNoOfBookAvailable(book.getNoOfBookAvailable()+1);

        cardRepository.save(card);

        return "book returned successfully and fine: Rs " + transactions.getFine()+"/-.";
    }


    public List<TransactionResponseDto> getTransactionsByCardId(int cardId) throws CardNotFoundException {
        Card card = cardRepository.findById(cardId).orElseThrow(()-> new CardNotFoundException("No such card: "+cardId));

        List<TransactionResponseDto> transactionResponseDtoList = new ArrayList<>();

        for (Transactions transactions : card.getListOfTransaction()){
            TransactionResponseDto transactionResponseDto = TransactionConvertor.convertEntityToDto(transactions);
            transactionResponseDtoList.add(transactionResponseDto);
        }
        return transactionResponseDtoList;
    }

    public List<TransactionResponseDto> getAllTransaction() {
        List<TransactionResponseDto> transactionResponseDtoList = new ArrayList<>();

        for (Transactions transactions : transactionRepository.findAll()){
            TransactionResponseDto transactionResponseDto = TransactionConvertor.convertEntityToDto(transactions);

            Book book = transactions.getBook();
            Card card = transactions.getCard();

            transactionResponseDto.setBookName((book==null) ? "Null" : transactions.getBook().getName());
            transactionResponseDto.setCardId((card==null) ? 0 : transactions.getCard().getId());

//            if(book == null){
//                transactionResponseDto.setBookName("Null");
//            }else{
//                transactionResponseDto.setBookName(transactions.getBook().getName());
//            }

//            if(card == null){
//                transactionResponseDto.setCardId(0);
//            }else {
//                transactionResponseDto.setCardId(transactions.getCard().getId());
//            }

            transactionResponseDtoList.add(transactionResponseDto);
        }
        return transactionResponseDtoList;
    }
}
