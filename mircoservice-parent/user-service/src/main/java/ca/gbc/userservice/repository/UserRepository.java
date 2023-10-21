package ca.gbc.userservice.repository;

import ca.gbc.userservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long> {
//    User findByUsername(String username);
//    void deleteById(String userId);
}