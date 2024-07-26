package edu.projects.ashrith.todo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tasks")
public class Tasks {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long taskid;
    private String taskname;
    private String taskdescription;
    private String taskstatus;
    private String taskpriority;
    private String taskduedate;
    private LocalDateTime taskcreateddate;
    private LocalDateTime taskupdateddate;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userid", referencedColumnName = "userid")
    @JsonIgnore
    private Users user;
}
