package com.student.library.management.repositories;

import com.student.library.management.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction,Integer> {
    @Query(value ="select sum(fine) from  transactions where  card_id=:cardId" ,nativeQuery = true )
    int findFineByCardId(int cardId);

    @Query(value = "select * from  transactions where book_id=:bookId and  card_id=:cardId",nativeQuery = true)
    Transaction findByBookAndCard(int bookId, int cardId);
}
