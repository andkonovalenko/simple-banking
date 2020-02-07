package com.testTask.dto;

import com.testTask.domain.Transaction;
import lombok.Data;

@Data
public class TransactionDTO {

    private String transactionType;

    private Float amountOfMoney;

    public static TransactionDTO toTransactionDTO(Transaction transaction) {
        TransactionDTO transactionDTO = new TransactionDTO();

        transactionDTO.setTransactionType(transaction.getTransactionType().getType());
        transactionDTO.setAmountOfMoney(transaction.getAmountOfMoney());

        return transactionDTO;
    }
}