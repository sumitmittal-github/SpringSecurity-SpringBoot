package com.sumit.service;

import com.sumit.entity.User;
import com.sumit.repository.UserRepository;
import com.sumit.config.UserToUserDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);

        // Convert Database User to SpringSecurity UserDetail type
        return user.map(UserToUserDetail::new)
                .orElseThrow(() -> new UsernameNotFoundException("User "+username+" not found !!!"));
    }

    public String createInitialUsersInDB() {
        User adminUser = new User("Lokesh Mittal", "lokesh@gmail.com", "lokesh", encoder.encode("pwd1"), "ROLE_ADMIN");
        userRepository.save(adminUser);

        User normalUser = new User("Sumit Mittal", "sumit@gmail.com", "sumit", encoder.encode("pwd2"), "ROLE_USER");
        userRepository.save(normalUser);

        return "User saved in DB";
    }
}