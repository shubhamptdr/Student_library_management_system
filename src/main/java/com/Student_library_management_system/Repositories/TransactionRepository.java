package com.Student_library_management_system.Repositories;

import com.Student_library_management_system.Models.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transactions,Integer> {
    @Query(value ="select sum(fine) from  transactions where  card_id=:cardId" ,nativeQuery = true )
    int findFineByCardId(int cardId);

    @Query(value = "select * from  transactions where book_id=:bookId and  card_id=:cardId",nativeQuery = true)
    Transactions findByBookAndCard(int bookId, int cardId);
}
