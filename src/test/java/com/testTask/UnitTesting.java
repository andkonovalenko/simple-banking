package com.testTask;

import com.testTask.domain.Transaction;
import com.testTask.domain.User;
import com.testTask.dto.UserDTO;
import com.testTask.repository.TransactionRepo;
import com.testTask.repository.UserRepo;
import com.testTask.service.BalanceService;
import com.testTask.service.TransactionService;
import com.testTask.service.UserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

public class UnitTesting {

    private User user;
    private Transaction transaction;

    @Mock
    private UserRepo userRepo;
    @Mock
    private TransactionRepo transactionRepo;

    @InjectMocks
    private UserService userService;
    @InjectMocks
    private TransactionService transactionService;
    @InjectMocks
    private BalanceService balanceService;

    @Before
    public void setUp() {
        user = new User();
        user.setId((long)11);
        user.setPassword("123");
        user.setMail("test11@gmail.com");
        user.setBalance(0.0f);
        MockitoAnnotations.initMocks(this);
        when(userRepo.getOne((long) 11)).thenReturn(user);
    }

    @Test
    public void createDeposit() {
        Assert.assertEquals(user.getBalance().doubleValue(), 0, 0);

        when(userRepo.save(user)).thenReturn(user);
        balanceService.deposit((long) 11, 23.2f);
        User updated = userRepo.getOne((long) 11);
        Assert.assertEquals(23.2f, updated.getBalance().doubleValue(), 0.0);
        verify(userRepo, times(2)).getOne((long) 11);
    }

    @Test
    public void createWithdraw() {
        user.setBalance(23.2f);
        Assert.assertEquals(user.getBalance().doubleValue(), 23.2, 0.000001);

        when(userRepo.save(user)).thenReturn(user);
        balanceService.withdraw((long) 11, 10.0f);
        User updated = userRepo.getOne((long) 11);
        Assert.assertEquals(13.2f, updated.getBalance().doubleValue(), 0.000001);
        verify(userRepo, times(2)).getOne((long) 11);
    }

}
