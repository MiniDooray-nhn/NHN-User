package com.nhnacademy.account.service;

import com.nhnacademy.account.domain.User;
import com.nhnacademy.account.domain.UserStatus;
import com.nhnacademy.account.dto.DeleteResponse;
import com.nhnacademy.account.dto.UserAuthDto;
import com.nhnacademy.account.dto.UserModifyRequest;
import com.nhnacademy.account.dto.UserRegisterRequest;
import com.nhnacademy.account.dto.UserResponse;
import com.nhnacademy.account.exception.UserAlreadyExistException;
import com.nhnacademy.account.exception.UserNotExistException;
import com.nhnacademy.account.exception.UserNotValidateException;
import com.nhnacademy.account.exception.UserStatusIsNotExistException;
import com.nhnacademy.account.repository.UserRepository;
import com.nhnacademy.account.repository.UserStatusRepository;
import com.nhnacademy.account.request.LoginRequest;
import java.time.LocalDate;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private static final Integer MEMBER = 1;
    private static final Integer NOT_MEMBER = 2;


    private final UserRepository userRepository;

    private final UserStatusRepository userStatusRepository;

    public UserService(UserRepository userRepository, UserStatusRepository userStatusRepository) {
        this.userRepository = userRepository;
        this.userStatusRepository = userStatusRepository;
    }

    public UserResponse getUserById(String userId) {


        User user = userRepository.findById(userId).orElseThrow(UserNotExistException::new);


        if (user.getUserStatus().getId().equals(NOT_MEMBER)) {
            throw new UserNotValidateException();
        }


        return user.convetToDto();
    }

    public DeleteResponse deleteUserById(String userId) {

        User user = userRepository.findById(userId).orElseThrow(UserNotExistException::new);

        if (user.getUserStatus().getId().equals(NOT_MEMBER)) {
            throw new UserNotValidateException();
        }


        UserStatus userStatus =
                userStatusRepository.findById(NOT_MEMBER).orElseThrow(UserNotExistException::new);

        user.setUserStatus(userStatus);

        userRepository.save(user);
        return new DeleteResponse("회원탈퇴 완료");
    }

    public UserAuthDto match(LoginRequest loginRequest) {


        User loginAuth = userRepository.findById(loginRequest.getUserId()).orElseThrow(UserNotExistException::new);

        if (loginAuth.getUserStatus().getId().equals(NOT_MEMBER)) {
            throw new UserNotValidateException();
        }

        Optional<User> user =
                userRepository.findByIdAndPassword(loginRequest.getUserId(), loginRequest.getPassword());


        return new UserAuthDto(user.isPresent());
    }

    public UserResponse modifyUserById(String userId, UserModifyRequest modifyRequest) {

        User user = userRepository.findById(userId).orElseThrow(UserNotExistException::new);

        if (user.getUserStatus().getId().equals(NOT_MEMBER)) {
            throw new UserNotValidateException();
        }
        user.setUserByModifyRequest(modifyRequest);

        userRepository.save(user);
        return user.convetToDto();
    }

    public UserResponse createUser(UserRegisterRequest userRegisterRequest) {


        UserStatus userStatus =
                userStatusRepository.findById(MEMBER).orElseThrow(UserStatusIsNotExistException::new);


        User user = new User();

        user.setCreatedAt(LocalDate.now());
        user.setUserStatus(userStatus);
        user.setPassword(userRegisterRequest.getPassword());
        user.setName(userRegisterRequest.getName());
        user.setId(userRegisterRequest.getId());
        user.setEmail(userRegisterRequest.getEmail());


        if (userRepository.findById(user.getId()).isPresent()) {
            throw new UserAlreadyExistException();
        }

        user = userRepository.save(user);

        return user.convetToDto();
    }


}
