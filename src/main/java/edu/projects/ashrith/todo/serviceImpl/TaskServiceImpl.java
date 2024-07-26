package edu.projects.ashrith.todo.serviceImpl;

import edu.projects.ashrith.todo.exceptions.ApiError;
import edu.projects.ashrith.todo.models.Tasks;
import edu.projects.ashrith.todo.models.Users;
import edu.projects.ashrith.todo.payload.AddTodoDTO;
import edu.projects.ashrith.todo.repository.TaskRepository;
import edu.projects.ashrith.todo.repository.UsersRepository;
import edu.projects.ashrith.todo.service.TaskService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZoneId;
import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepo;
    @Autowired
    private UsersRepository userRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public AddTodoDTO addTask(AddTodoDTO addTodoDTO) {
        Users user = userRepo.findByEmail(addTodoDTO.getEmail());
        if(user == null){
            throw ApiError.UserNotFound(String.format(" User with email %s not found", addTodoDTO.getEmail()));
        }
        Tasks task = modelMapper.map(addTodoDTO, Tasks.class);
        task.setTaskcreateddate(java.time.LocalDateTime.now());
        task.setTaskupdateddate(java.time.LocalDateTime.now());
        task.setUser(user);
        Tasks savedTask = taskRepo.save(task);
        AddTodoDTO returnTask = modelMapper.map(savedTask,AddTodoDTO.class);
        returnTask.setEmail(addTodoDTO.getEmail());
        return returnTask;
    }

    @Override
    public AddTodoDTO updateTask(AddTodoDTO task,String email, Long taskid) {
        Tasks tasks = taskRepo.findByUser_EmailAndTaskid(email,taskid);
        if(tasks == null){
            throw ApiError.TaskNotFound(String.format(" Task with id %s not found for User %s", taskid, email));
        }
        tasks.setTaskname(task.getTaskName());
        tasks.setTaskdescription(task.getTaskDescription());
        tasks.setTaskstatus(task.getTaskStatus());
        tasks.setTaskpriority(task.getTaskPriority());
        tasks.setTaskduedate(task.getTaskDueDate());
        tasks.setTaskupdateddate(java.time.LocalDateTime.now(ZoneId.of("Asia/Kolkata")));
        return modelMapper.map(taskRepo.save(tasks),AddTodoDTO.class);
    }

    @Override
    public List<Tasks> getTasks(Long userid) {
        return taskRepo.findByUser_Userid(userid);
    }
}
