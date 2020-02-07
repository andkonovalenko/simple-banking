package com.testTask.dto;

import com.testTask.domain.User;
import lombok.Data;

@Data
public class BalanceDTO {

    private Float balance;

    public static BalanceDTO toBalanceDTO (User user) {
        BalanceDTO balanceDTO = new BalanceDTO();

        balanceDTO.setBalance(user.getBalance());

        return balanceDTO;
    }
}
