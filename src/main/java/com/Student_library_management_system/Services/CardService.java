package com.Student_library_management_system.Services;

import com.Student_library_management_system.Repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CardService {
    @Autowired
    private TransactionRepository transactionRepository;
    public int getFineByCardId(int cardId) {
        return transactionRepository.findFineByCardId(cardId);
    }

}
