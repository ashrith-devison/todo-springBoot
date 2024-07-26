package edu.projects.ashrith.todo.controllers;

import edu.projects.ashrith.todo.models.Tasks;
import edu.projects.ashrith.todo.payload.AddTodoDTO;
import edu.projects.ashrith.todo.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todo")
public class AddTodo {

    @Autowired
    private TaskService taskService;
    @PostMapping("/add")
    public ResponseEntity<AddTodoDTO> addTodo(@RequestBody AddTodoDTO task) {
        return new ResponseEntity<>(taskService.addTask(task), HttpStatus.CREATED);
    }

    @GetMapping("/getTasks/{userid}")
    public List<Tasks> getTasks(@PathVariable Long userid) {
        List<Tasks> tasks = taskService.getTasks(userid);
        return tasks;
    }

    @PutMapping("/{email}/update/{taskid}")
    public ResponseEntity<AddTodoDTO> updateTask(@RequestBody AddTodoDTO task, @PathVariable String email, @PathVariable Long taskid) {
        return new ResponseEntity<>(taskService.updateTask(task,email,taskid), HttpStatus.OK);
    }


}
