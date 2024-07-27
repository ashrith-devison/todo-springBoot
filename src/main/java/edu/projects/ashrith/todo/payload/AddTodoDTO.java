package edu.projects.ashrith.todo.payload;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddTodoDTO {
    @NotBlank(message = "Task Name cannot be empty")
    private String taskName;
    @Size(max=10, message = "Task Description cannot be more than 10 characters")
    private String taskDescription;
    private String taskStatus;
    private String taskPriority;
    private String taskDueDate;
    @NotBlank(message = "Email cannot be empty")
    private String email;
}
