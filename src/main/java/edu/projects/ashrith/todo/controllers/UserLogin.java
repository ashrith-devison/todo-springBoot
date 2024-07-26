package edu.projects.ashrith.todo.controllers;

import edu.projects.ashrith.todo.models.Users;
import edu.projects.ashrith.todo.payload.LoginUserDTO;
import edu.projects.ashrith.todo.payload.RegisterUserDTO;
import edu.projects.ashrith.todo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserLogin {
    @Autowired
    private UserService userService;

    @PutMapping("/login")
    public ResponseEntity<Users> login(@RequestBody LoginUserDTO user) {

        return new ResponseEntity<>(userService.login(user), HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<Users> register(@RequestBody RegisterUserDTO user) {
        return new ResponseEntity<>(userService.createUser(user), HttpStatus.CREATED);
    }
}
