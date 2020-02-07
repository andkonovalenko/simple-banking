package com.testTask.service;

import com.testTask.domain.Transaction;
import com.testTask.domain.User;
import com.testTask.domain.enums.TransactionType;
import com.testTask.repository.TransactionRepo;
import com.testTask.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class BalanceService {

    private final UserRepo userRepo;
    private final TransactionRepo transactionRepo;

    @Autowired
    public BalanceService(UserRepo userRepo,
                          TransactionRepo transactionRepo) {
        this.userRepo = userRepo;
        this.transactionRepo = transactionRepo;
    }

    public Transaction deposit(Long userId, Float value) {
        return updateBalance(userId, value, TransactionType.DEPOSIT);
    }

    public Transaction withdraw(Long userId, Float value) {
        return updateBalance(userId, value, TransactionType.WITHDRAW);
    }

    private Transaction updateBalance(Long userId, Float value, TransactionType transactionType) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        Date date = new Date();

        Transaction transaction = new Transaction();
        User user = userRepo.getOne(userId);

        transaction.setAmountOfMoney(value);
        transaction.setTransactionType(transactionType);
        transaction.setDate(formatter.format(date));
        transaction.setUser(user);

        transactionRepo.save(transaction);

        if(transactionType == TransactionType.DEPOSIT){
            user.setBalance(user.getBalance() + value);
        }
        if(transactionType == TransactionType.WITHDRAW) {
            user.setBalance(user.getBalance() - value);
        }

        userRepo.save(user);

        return transaction;
    }
}
