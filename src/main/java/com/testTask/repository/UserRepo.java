package com.testTask.repository;

import com.testTask.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {
    User findByMail(String mail);
}
