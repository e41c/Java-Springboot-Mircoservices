package ca.gbc.userservice.controller;

import ca.gbc.userservice.dto.UserRequest;
import ca.gbc.userservice.dto.UserResponse;
import ca.gbc.userservice.service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
@Slf4j
public class UserController {

        private final UserServiceImpl userService;

        @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createUser(@RequestBody UserRequest userRequest){
        userService.createUser(userRequest);
    }

    @GetMapping({"/{userEmail}"})
    @ResponseStatus(HttpStatus.OK)
    public UserResponse getUser(@PathVariable("userEmail") String userEmail){
        return userService.getUser(userEmail);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<UserResponse> getAllUsers(){
        return userService.getAllUsers();
    }

    // http:localhost:8080/api/user/1
    @PutMapping({"/{userId}/"})
    public ResponseEntity<?> updateUser(@PathVariable("userId") String userId,
                                        @RequestBody UserRequest userRequest){

        String updateUserId = userService.updateUser(userId, userRequest);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "/api/user/" + updateUserId);

        return new ResponseEntity<>(headers, HttpStatus.NO_CONTENT);
    }

    //http://localhost:8080/api/user/1

    @DeleteMapping({"/{userId}/"})
    public ResponseEntity<?> deleteUser(@PathVariable("userId") String userId){
        userService.deleteUser(userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }





}
