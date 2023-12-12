package ca.gbc.userservice.repository;

import ca.gbc.userservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long> {
//    User findByUsername(String username);
//    void deleteById(String userId);
    Optional<User> findByEmail(String email);
}