package com.testTask.domain.enums;

import lombok.Getter;

public enum TransactionType {
    DEPOSIT("Deposit"), WITHDRAW("Withdraw");

    @Getter
    private final String type;

    TransactionType(String type) { this.type = type; }
}
