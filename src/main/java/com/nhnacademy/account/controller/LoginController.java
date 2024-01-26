package com.nhnacademy.account.controller;

import com.nhnacademy.account.dto.UserAuthDto;
import com.nhnacademy.account.request.LoginRequest;
import com.nhnacademy.account.request.LoginResponse;
import com.nhnacademy.account.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    private final UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> doLogin(@RequestBody LoginRequest loginRequest) {


        System.out.println(loginRequest.getPassword()+" "+loginRequest.getUserId());

        UserAuthDto userAuthDto = userService.match(loginRequest);

        if (userAuthDto.isLoginUser()) {
            LoginResponse loginResponse = new LoginResponse(true, "Login success");
            return ResponseEntity.status(HttpStatus.OK).body(loginResponse);
        } else {
            LoginResponse loginResponse = new LoginResponse(false, "Login failed");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(loginResponse);
        }
    }


}
