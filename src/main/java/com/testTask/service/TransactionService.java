package com.testTask.service;

import com.testTask.domain.Transaction;
import com.testTask.domain.User;
import com.testTask.dto.TransactionDTO;
import com.testTask.repository.TransactionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionService {

    private final TransactionRepo transactionRepo;

    @Autowired
    public TransactionService(TransactionRepo transactionRepo) {
        this.transactionRepo = transactionRepo;
    }

    public List<TransactionDTO> getUserHistory(User user) {
        Iterable<Transaction> transactions = transactionRepo.findByUser(user);
        List<TransactionDTO> transactionDTOS = new ArrayList<>();

        for (Transaction transaction : transactions) {
            transactionDTOS.add(TransactionDTO.toTransactionDTO(transaction));
        }
        return transactionDTOS;
    }
}
