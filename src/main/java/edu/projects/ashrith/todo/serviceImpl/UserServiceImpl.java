package edu.projects.ashrith.todo.serviceImpl;

import edu.projects.ashrith.todo.exceptions.ApiError;
import edu.projects.ashrith.todo.models.Users;
import edu.projects.ashrith.todo.payload.LoginUserDTO;
import edu.projects.ashrith.todo.payload.RegisterUserDTO;
import edu.projects.ashrith.todo.repository.UsersRepository;
import edu.projects.ashrith.todo.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UsersRepository userRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public Users login(LoginUserDTO user) {
        Users userData = userRepo.findByEmail(user.getEmail());
        if(userData == null){
            throw ApiError.UserNotFound(String.format(" User with email %s not found", user.getEmail()));
        }
        else{
            return userData;
        }
    }

    @Override
    public Users createUser(RegisterUserDTO user) {
        if(userRepo.findByEmail(user.getEmail()) == null){
            Users userEntity = modelMapper.map(user, Users.class);
            Users savedUser = userRepo.save(userEntity);
            return modelMapper.map(savedUser, Users.class);
        }
        else{
            throw ApiError.UserAlreadyExists(String.format(" User with email %s already exists", user.getEmail()));
        }
    }
}
