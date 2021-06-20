package com.walking.user.userservice.vo;

import com.walking.user.userservice.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserCreationStatusVO {
    boolean isCreated;
    Exception exception;
    String Error;
    @Autowired
    User user;
}
