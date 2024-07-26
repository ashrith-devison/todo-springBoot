package edu.projects.ashrith.todo.payload;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterUserDTO {
    private String username;
    private String password;
    private String email;
}
