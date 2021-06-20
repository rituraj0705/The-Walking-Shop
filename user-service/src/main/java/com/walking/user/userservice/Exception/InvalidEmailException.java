package com.walking.user.userservice.Exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class InvalidEmailException extends Exception{
    public InvalidEmailException(String s) {
        super(s);
        log.error("Given email {} is not a valid email id", s);
    }
}
