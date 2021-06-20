package com.walking.user.userservice.service;

import com.walking.user.userservice.Exception.InvalidEmailException;
import com.walking.user.userservice.entity.User;
import com.walking.user.userservice.repository.UserRepository;
import com.walking.user.userservice.utility.Constants;
import com.walking.user.userservice.utility.UserUtil;
import com.walking.user.userservice.vo.UserCreationStatusVO;
import com.walking.user.userservice.vo.UserServiceVo;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.JDBCException;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.regex.Pattern;

@Service
@Slf4j
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private UserUtil userUtil;

    /**
     * Takes user as parameter and saves the user to backend
     * @param user
     * @return
     * @throws InvalidEmailException
     */
    public UserCreationStatusVO saveUser(User user) throws InvalidEmailException {
        if (log.isInfoEnabled()) {
            log.info("In saveUser method of userService");
        }
        UserCreationStatusVO statusVO = new UserCreationStatusVO();
        if (!userUtil.isValidEmail(user.getEmailId())) {
            throw new InvalidEmailException(user.getEmailId());
        }
        bcryptPassword(user);


        try {
            statusVO.setUser(userRepository.save(user));
            statusVO.setCreated(true);
            return statusVO;
        } catch (JDBCException ex) {
            log.error("Could not Create user, unique key violated");
            statusVO.setException(ex);
            statusVO.setError("uniqueKey");
            statusVO.setUser(null);
            return statusVO;
        }
    }

    /**
     * Takes input param as long id and returns User object wrapped in UserServiceVo
     * @param userId
     * @return
     */
    public UserServiceVo getUserWithId (Long userId) {
        if (log.isInfoEnabled()) {
            log.info("In getUserWithId method of userService");
        }
        UserServiceVo vo = new UserServiceVo();
        User user = userRepository.findByUserId(userId);
        vo.setUser(user);
        return vo;
    }

    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    /**
     * This method is used to save list of users to backend
     * @param users
     * @return
     * @throws InvalidEmailException
     */
    public List<User> saveAll(List<User> users) throws InvalidEmailException{
        for (User user : users) {
            if (!userUtil.isValidEmail(user.getEmailId())) {
                throw new InvalidEmailException(user.getEmailId());
            }
            updateUserName(user);
            bcryptPassword(user);
        }
        if (log.isInfoEnabled()) {
            log.info("Ending saveAll method of userService");
        }
        return userRepository.saveAll(users);
    }

    /**
     * This method updates the user name of the user object.
     * @param user
     */
    public void updateUserName (User user) {
        UUID uniqueKey = UUID.randomUUID();
//        user.setUserName(user.getFirstName().toLowerCase() + user.getLastName().toLowerCase()
//                  + uniqueKey);
        user.setUserName(user.getFirstName().toLowerCase() + user.getLastName().toLowerCase()
                  + user.getUserId());
        userRepository.save(user);
    }

    public User findByUserName(String username) {
        return userRepository.findByUserName(username);
    }

    /**
     * Takes the user objects and updates the password in bcrypt format
     * @param user
     */
    public void bcryptPassword (User user) {
        String salt = BCrypt.gensalt(Constants.WORKLOAD);
        String hashed_password = BCrypt.hashpw(user.getPassword(), salt);
        user.setPassword(hashed_password);
        //return(hashed_password);
    }
}
