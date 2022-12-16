package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.dao.UserDao;
import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.User;
import com.techelevator.tenmo.dao.JdbcUserDao;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
@PreAuthorize("isAuthenticated()")
public class UserController {

    private final JdbcUserDao dao;
    public UserController(JdbcUserDao dao) {
        this.dao = dao;
    }

    @PreAuthorize("permitAll")
    @RequestMapping(path = "?username={username}", method = RequestMethod.GET)
    public int findIdByUsername(@PathVariable String username) {
        int id = dao.findIdByUsername(username);
        return id;
    }

    @PreAuthorize("permitAll")
    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public User getUserById(@PathVariable int id) {
        User user = dao.getUserById(id);
        return user;
    }

    @PreAuthorize("permitAll")
    @RequestMapping(path = "", method = RequestMethod.GET)
    public List<User> findAll() {
        return dao.findAll();
    }

    @PreAuthorize("permitAll")
    @RequestMapping(path = "/username?username={username}", method = RequestMethod.GET)
    public User findByUsername(@PathVariable String username) {
        User user = dao.findByUsername(username);
        return user;
    }

}