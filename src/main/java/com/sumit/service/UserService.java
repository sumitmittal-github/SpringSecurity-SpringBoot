package com.sumit.service;

import com.sumit.entity.User;
import com.sumit.enums.Roles;
import com.sumit.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(12);

    public String createInitialUsersInDB() {
        User adminUser = new User("Lokesh Mittal", "lokesh@gmail.com", "lokesh", bCryptPasswordEncoder.encode("pwd1"), List.of(Roles.ROLE_ADMIN.toString()));
        userRepository.save(adminUser);

        User normalUser = new User("Sumit Mittal", "sumit@gmail.com", "sumit", bCryptPasswordEncoder.encode("pwd2"), List.of(Roles.ROLE_USER.toString()));
        userRepository.save(normalUser);

        return "User saved in DB";
    }
}