package com.walking.user.userservice.controller;

import com.walking.user.userservice.Exception.InvalidEmailException;
import com.walking.user.userservice.entity.User;
import com.walking.user.userservice.service.UserService;
import com.walking.user.userservice.service.product.ProductService;
import com.walking.user.userservice.vo.Product;
import com.walking.user.userservice.vo.UserCreationStatusVO;
import com.walking.user.userservice.vo.UserServiceVo;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {
    private static Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;
    @Autowired
    private ProductService productService;


    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public String saveUser (@RequestBody User user) throws InvalidEmailException {
        if (log.isInfoEnabled()) {
            log.info("Inserting new user with user id {}, and name {} {}", user.getUserId(), user.getFirstName(), user.getLastName());
        }
        UserCreationStatusVO userCreationStatusVO = userService.saveUser(user);
        if (userCreationStatusVO.isCreated()) {
            return "User Created with user id: " + userCreationStatusVO.getUser().getUserId()
                + " and user name: " + userCreationStatusVO.getUser().getUserName();
        } else {
            if (userCreationStatusVO.getError().equals("uniqueKey")) {
                return "UserName already exists, please enter another user name";
            } else {
                return "Could not create user, exception occurred.";
            }
        }
    }

    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public List<User> saveUsers (@RequestBody List<User> users) throws InvalidEmailException {
        if (log.isInfoEnabled()) {
            log.info("In saveUsers method of UserController");
        }
        return userService.saveAll(users);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public UserServiceVo getUserWithId (@PathVariable Long id) {
        if (log.isInfoEnabled()) {
            log.info("In getUserWithId method of userController");
        }
        return userService.getUserWithId(id);
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<User> getAllUsers () {
        if (log.isInfoEnabled()) {
            log.info("In getUserWithId method of userController");
        }
        return userService.getAllUser();
    }

    @RequestMapping("/")
    public String getMainPage () {
        if (log.isInfoEnabled()) {
            log.info("In getMainPage method of user controller");
        }
        return "Hello User";
    }

    @RequestMapping("/products")
    public List<Product> getAllProducts () {
        if (log.isInfoEnabled()) {
            log.info("In getMainPage method of user controller");
        }
        return productService.getAllProducts();
    }

    @RequestMapping(value = "/product/{productId}", method = RequestMethod.GET)
    public Product getAllProductById (@PathVariable Long productId) {
        if (log.isInfoEnabled()) {
            log.info("In getAllProductById method of user controller");
        }
        return productService.getProductById(productId);
    }
}
