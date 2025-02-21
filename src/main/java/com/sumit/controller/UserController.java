package com.sumit.controller;

import com.sumit.entity.User;
import com.sumit.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     To update his account
     */
    @PutMapping
    public ResponseEntity<User> update(@RequestBody User user) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        User dbUser = userService.update(username, user);
        return new ResponseEntity<>(dbUser, HttpStatus.OK);
    }

    /**
     To delete his account
     */
    @DeleteMapping
    public ResponseEntity<?> delete() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        userService.delete(username);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/populateDB")
    public String createInitialUsersInDB(){
        return userService.createInitialUsersInDB();
    }

}