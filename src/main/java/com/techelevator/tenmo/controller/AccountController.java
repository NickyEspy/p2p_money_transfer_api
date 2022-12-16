package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.dao.JdbcUserDao;
import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.dao.JdbcAccountDao;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import javax.validation.Valid;
import java.math.BigDecimal;

@RestController
@RequestMapping("/account")
@PreAuthorize("isAuthenticated()")
public class AccountController {

    private JdbcAccountDao dao;
    public AccountController(JdbcAccountDao dao) {
        this.dao = dao;
    }

    @PreAuthorize("permitAll")
    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public BigDecimal get(@PathVariable int id) {
        BigDecimal accountBalance = dao.getAccountBalance(id);
        return accountBalance;
    }

    @PreAuthorize("permitAll")
    @RequestMapping(path = "/account/{user_id}", method = RequestMethod.GET)
    public int getAccountId(@PathVariable int user_id) {
        int accountId = dao.getAccountId(user_id);
        return accountId;
    }


//    @RequestMapping(path = "/{id}", method = RequestMethod.PUT)
//    public void update(@PathVariable int id) {
//        Account account = dao.get(id);
//    }
}