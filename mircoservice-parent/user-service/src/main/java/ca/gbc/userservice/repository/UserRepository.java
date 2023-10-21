package ca.gbc.userservice.repository;

import ca.gbc.userservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

// UserRepository.java
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}

