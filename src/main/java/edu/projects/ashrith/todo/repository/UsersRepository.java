package edu.projects.ashrith.todo.repository;

import edu.projects.ashrith.todo.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users, Long> {
    public Users findByUsername(String username);
    public Users findByEmail(String email);
}
