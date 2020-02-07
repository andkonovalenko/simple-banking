package com.testTask.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.testTask.domain.Transaction;
import com.testTask.domain.User;
import com.testTask.domain.Views;
import com.testTask.dto.BalanceDTO;
import com.testTask.dto.TransactionDTO;
import com.testTask.dto.UserDTO;
import com.testTask.dto.ValueDTO;
import com.testTask.repository.UserRepo;
import com.testTask.service.BalanceService;
import com.testTask.service.TransactionService;
import com.testTask.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping
public class UserController {

    private final UserRepo userRepo;
    private final UserService userService;
    private final BalanceService balanceService;
    private final TransactionService transactionService;

    @Autowired
    public UserController(UserRepo userRepo, UserService userService,
                          BalanceService balanceService, TransactionService transactionService) {
        this.userRepo = userRepo;
        this.userService = userService;
        this.balanceService = balanceService;
        this.transactionService = transactionService;
    }

    @GetMapping("user/list")
    @JsonView(Views.Full.class)
    public List<User> getListOfUsers() {
        return userRepo.findAll();
    }

    @GetMapping("user/{id}")
    @JsonView(Views.Full.class)
    public User getOneUser(@PathVariable("id") User user) {
        return user;
    }

    @GetMapping("balance/user/{id}")
    public BalanceDTO getOneBalance(@PathVariable("id") Long userId) {
        User user = userRepo.getOne(userId);
        return BalanceDTO.toBalanceDTO(user);
    }

    @GetMapping("history/user/{id}")
    public List<TransactionDTO> getUserTransactionsHistory(@PathVariable("id") Long userId) {
        User user = userRepo.getOne(userId);
        return transactionService.getUserHistory(user);
    }

    @PostMapping("user/create")
    public User createOne(@RequestBody UserDTO userDTO) { return userService.addUser(userDTO); }

    @PostMapping("deposit/user/{id}")
    @JsonView(Views.Full.class)
    public Transaction deposit(@PathVariable("id") Long userId, @RequestBody @Valid ValueDTO valueDto) {
        return balanceService.deposit(userId, valueDto.getValue());
    }

    @PostMapping("withdraw/user/{id}")
    @JsonView(Views.Full.class)
    public Transaction withdraw(@PathVariable("id") Long userId, @RequestBody @Valid ValueDTO value) {
        return balanceService.withdraw(userId, value.getValue());
    }
}
