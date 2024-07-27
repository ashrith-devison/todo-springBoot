package edu.projects.ashrith.todo.serviceImpl;

import edu.projects.ashrith.todo.exceptions.ActivityLogs;
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
            ActivityLogs log=new ActivityLogs("ApiErrors.txt");
            log.logActivity(addTodoDTO.getEmail(),String.format(" User with email %s not found", addTodoDTO.getEmail()),"UserNotFound Error");
            throw ApiError.UserNotFound(String.format(" User with email %s not found", addTodoDTO.getEmail()));
        }
        Tasks task = modelMapper.map(addTodoDTO, Tasks.class);
        task.setTaskcreateddate(java.time.LocalDateTime.now());
        task.setTaskupdateddate(java.time.LocalDateTime.now());
        task.setUser(user);
        Tasks savedTask = taskRepo.save(task);
        AddTodoDTO returnTask = modelMapper.map(savedTask,AddTodoDTO.class);
        returnTask.setEmail(addTodoDTO.getEmail());
        ActivityLogs log = new ActivityLogs("ApiResponse.txt");
        log.logActivity(addTodoDTO.getEmail(), "Adding the task", "Task Added Successfully");
        return returnTask;
    }

    @Override
    public AddTodoDTO updateTask(AddTodoDTO task,String email, Long taskid) {
        Tasks tasks = taskRepo.findByUser_EmailAndTaskid(email,taskid);
        if(tasks == null){
            ActivityLogs log = new ActivityLogs("ApiErrors.txt");
            log.logActivity(email, String.format(" Task with id %s not found for User %s", taskid, email), "TaskNotFound or UserNotFound Error");
            throw ApiError.TaskNotFound(String.format(" Task with id %s not found for User %s", taskid, email));
        }
        tasks.setTaskname(task.getTaskName());
        tasks.setTaskdescription(task.getTaskDescription());
        tasks.setTaskstatus(task.getTaskStatus());
        tasks.setTaskpriority(task.getTaskPriority());
        tasks.setTaskduedate(task.getTaskDueDate());
        tasks.setTaskupdateddate(java.time.LocalDateTime.now(ZoneId.of("Asia/Kolkata")));
        ActivityLogs log = new ActivityLogs("ApiResponse.txt");
        log.logActivity(task.getEmail(), "Updating the task", "Task Updated Successfully");
        return modelMapper.map(taskRepo.save(tasks),AddTodoDTO.class);
    }

    @Override
    public List<Tasks> getTasks(String email) {
        List<Tasks> tasks = taskRepo.findByUser_Email(email);
        if(tasks.isEmpty()){
            ActivityLogs log = new ActivityLogs("ApiErrors.txt");
            log.logActivity(email, String.format(" User with email %s not found", email), "UserNotFound or TaskNotFound Error");
            throw ApiError.UserNotFound(String.format(" User with email %s not found", email));
        }
        ActivityLogs log = new ActivityLogs("ApiResponse.txt");
        log.logActivity(email, "Getting the tasks", "Tasks Retrieved Successfully");
        return tasks;
    }

    @Override
    public Tasks deleteTask(String email, Long taskid) {
        Tasks tasks = taskRepo.findByUser_EmailAndTaskid(email,taskid);
        if(tasks == null){
            throw ApiError.TaskNotFound(String.format(" Task with id %s not found for User %s", taskid, email));
        }
        taskRepo.delete(tasks);
        return tasks;
    }
}
