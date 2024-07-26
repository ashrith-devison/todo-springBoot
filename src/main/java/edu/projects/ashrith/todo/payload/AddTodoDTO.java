package edu.projects.ashrith.todo.payload;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddTodoDTO {
    private String taskName;
    private String taskDescription;
    private String taskStatus;
    private String taskPriority;
    private String taskDueDate;
    private String email;
}
