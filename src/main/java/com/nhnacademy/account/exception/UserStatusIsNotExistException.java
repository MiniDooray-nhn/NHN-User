package com.nhnacademy.account.exception;

public class UserStatusIsNotExistException extends RuntimeException{
    public UserStatusIsNotExistException() {
        super("존재하지 않는 유저상태입니다");
    }
}
