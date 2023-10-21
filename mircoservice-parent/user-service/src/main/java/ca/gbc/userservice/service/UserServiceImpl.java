package ca.gbc.userservice.service;

import ca.gbc.userservice.dto.UserRequest;
import ca.gbc.userservice.dto.UserResponse;
import ca.gbc.userservice.model.User;
import ca.gbc.userservice.model.UserLineItem;
import ca.gbc.userservice.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.postgresql.core.Query;
import org.springframework.data.jpa.repository.support.QuerydslJpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserServiceImpl implements UserService {
    private final QuerydslJpaRepository querydslJpaRepository;
    private final UserRepository userRepository;

    @Override
    public void createUser(UserRequest userRequest) {
        User user = User.builder()
                .username(userRequest.getUsername())
                .email(userRequest.getEmail())
                .build();

        userRepository.save(user);
    }

    @Override
    public String updateUser(String userId, UserRequest userRequest) {
        return null;
    }

    @Override
    public void deleteUser(String userId) {
        userRepository.deleteById(Long.valueOf(userId));
    }

    @Override
    public List<UserResponse> getAllUsers() {
        List<User> user = userRepository.findAll();
        return user.stream().map(this::mapToDto).toList();
    }

    private UserLineItem mapToDto(UserLineItem userLineItem){
        UserLineItem userLineItem1 = new UserLineItem();
        userLineItem1.setUsername(userLineItem.getUsername());
        userLineItem1.setEmail(userLineItem.getEmail());
        return userLineItem1;
    }

}