package com.nhnacademy.account.exception;

public class UserNotValidateException extends RuntimeException{
    public UserNotValidateException() {
        super("탈퇴한 회원입니다");
    }
}
