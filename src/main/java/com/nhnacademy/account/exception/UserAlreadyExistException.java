package com.nhnacademy.account.exception;

public class UserAlreadyExistException extends RuntimeException{
    public UserAlreadyExistException() {
        super("중복된 아이디입니다");
    }
}
