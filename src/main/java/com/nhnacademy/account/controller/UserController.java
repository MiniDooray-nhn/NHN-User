package com.nhnacademy.account.controller;

import com.nhnacademy.account.dto.DeleteResponse;
import com.nhnacademy.account.dto.UserModifyRequest;
import com.nhnacademy.account.dto.UserRegisterRequest;
import com.nhnacademy.account.dto.UserResponse;
import com.nhnacademy.account.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public UserResponse getUser(@PathVariable("id") String id) {
        return userService.getUserById(id);
    }

    @PostMapping
    public ResponseEntity<UserResponse> createUser(@RequestBody UserRegisterRequest userRegisterRequest) {


        UserResponse userResponse = userService.createUser(userRegisterRequest);

        return new ResponseEntity<>(userResponse, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> modifyUser(@PathVariable(name = "id") String id,
                                                   @RequestBody UserModifyRequest userModifyRequest) {

        UserResponse userResponse = userService.modifyUserById(id, userModifyRequest);
        return new ResponseEntity<>(userResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DeleteResponse> deleteUser(@PathVariable("id") String id) {
        DeleteResponse deleteResponse = userService.deleteUserById(id);
        return new ResponseEntity<>(deleteResponse, HttpStatus.OK);
    }


}
