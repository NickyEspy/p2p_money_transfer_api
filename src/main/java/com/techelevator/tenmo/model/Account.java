package com.techelevator.tenmo.model;

import java.math.BigDecimal;

public class Account {

    private int id;
    private int user;
    private BigDecimal userBalance;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }

    public BigDecimal getUserBalance() {
        return userBalance;
    }

    public void setUserBalance(BigDecimal userBalance) {
        this.userBalance = userBalance;
    }

//    public Account() {
//        this.id = id;
//        this.user = user;
//        this.userBalance = userBalance;
//    }

}
