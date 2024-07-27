package edu.projects.ashrith.todo.service;

import edu.projects.ashrith.todo.models.Tasks;
import edu.projects.ashrith.todo.payload.AddTodoDTO;

import java.util.List;

public interface TaskService {
    public AddTodoDTO addTask(AddTodoDTO addTodoDTO);

    public AddTodoDTO updateTask(AddTodoDTO task, String email, Long taskid);

    public List<Tasks> getTasks(String email);

    public Tasks deleteTask(String email, Long taskid);
}
