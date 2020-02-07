package com.testTask.service;

import com.testTask.domain.User;
import com.testTask.dto.UserDTO;
import com.testTask.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepo userRepo;

    @Autowired
    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public User addUser(UserDTO userDTO) {

        User userFromDb = userRepo.findByMail(userDTO.getMail());
        User user = new User();

        if (userFromDb != null) {
            return null;
        }

        user.setBalance(0.0f);
        user.setMail(userDTO.getMail());
        user.setPassword(userDTO.getPassword());
        userRepo.save(user);

        return user;
    }

}
