package edu.projects.ashrith.todo.repository;

import edu.projects.ashrith.todo.models.Tasks;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Tasks, Long> {
    public List<Tasks> findByUser_Userid(Long userId);
    public Tasks findByUser_UseridAndTaskid(Long userId, Long taskId);
    public Tasks findByUser_EmailAndTaskid(String email, Long taskId);
    public List<Tasks> findByUser_Email(String email);
}
