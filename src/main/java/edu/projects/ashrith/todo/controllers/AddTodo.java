package edu.projects.ashrith.todo.controllers;

import edu.projects.ashrith.todo.exceptions.ActivityLogs;
import edu.projects.ashrith.todo.models.Tasks;
import edu.projects.ashrith.todo.payload.AddTodoDTO;
import edu.projects.ashrith.todo.service.TaskService;
import jakarta.validation.Valid;
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
    public ResponseEntity<AddTodoDTO> addTodo(@Valid @RequestBody AddTodoDTO task) {
        ActivityLogs log = new ActivityLogs("ApiRequests.txt");
        log.logActivity(task.getEmail(), "Adding");
        return new ResponseEntity<>(taskService.addTask(task), HttpStatus.CREATED);
    }

    @GetMapping("/getTasks/{email}")
    public List<Tasks> getTasks(@PathVariable String email) {
        ActivityLogs log = new ActivityLogs("ApiRequests.txt");
        log.logActivity(email, "Getting Tasks", "Getting Tasks for the user"+email);
        List<Tasks> tasks = taskService.getTasks(email);
        return tasks;
    }

    @PutMapping("/{email}/update/{taskid}")
    public ResponseEntity<AddTodoDTO> updateTask(@RequestBody AddTodoDTO task, @PathVariable String email, @PathVariable Long taskid) {
        ActivityLogs log = new ActivityLogs("ApiRequests.txt");
        log.logActivity(email, "Updating", String.format("Updating %s Task with id %d", email, taskid));
        return new ResponseEntity<>(taskService.updateTask(task,email,taskid), HttpStatus.OK);
    }

    @DeleteMapping("/{email}/delete/{taskid}")
    public ResponseEntity<Tasks> deleteTask(@PathVariable String email, @PathVariable Long taskid) {
        return new ResponseEntity<>(taskService.deleteTask(email,taskid), HttpStatus.OK);
    }
}
