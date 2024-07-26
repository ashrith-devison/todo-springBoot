package edu.projects.ashrith.todo.service;

import edu.projects.ashrith.todo.models.Users;
import edu.projects.ashrith.todo.payload.LoginUserDTO;
import edu.projects.ashrith.todo.payload.RegisterUserDTO;

public interface UserService {
    public Users login(LoginUserDTO user);
    public Users createUser (RegisterUserDTO user);
}
