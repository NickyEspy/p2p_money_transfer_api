package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.Transfer;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import java.math.BigDecimal;
import java.math.RoundingMode;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class JdbcAccountDao implements AccountDao{

    private BigDecimal userBalance;
    private final JdbcTemplate jdbcTemplate;

    public JdbcAccountDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }





    @Override
    public BigDecimal getAccountBalance(int id) {
        String sql = "SELECT account_id, user_id, balance FROM account WHERE user_id = ?;";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, id);

        Account account = new Account();
        BigDecimal accountBalance = null;

        try {
            result = jdbcTemplate.queryForRowSet(sql, id);
            if (result.next()) {
                accountBalance = result.getBigDecimal("balance");
            }
        } catch (DataAccessException e){
            System.out.println("Error accessing data");
        }
        return accountBalance;
    }

    public int getAccountId(int user_id) {
        String sql = "SELECT account_id, user_id, balance FROM account WHERE user_id = ?;";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, user_id);

        int accountId = 0;
        Account account = new Account();
        if (result.next()) {
            account = mapRowToAccount(result);
        }
        accountId = account.getId();
        return accountId;
    }


    @Override
    public void update(Transfer transfer){
        BigDecimal balanceFrom = new BigDecimal(0);
        BigDecimal toBalance = new BigDecimal(0);

        int senderAccount = transfer.getAccountFrom();
        int receiverAccount = transfer.getAccountTo();
        BigDecimal transferAmount = transfer.getAmount();

        BigDecimal senderBalance = getAccountBalance(senderAccount);
        BigDecimal receiverBalance = getAccountBalance(receiverAccount);

        if (transfer.getType() == 1) {
            senderBalance = senderBalance.add(transferAmount);
            receiverBalance = receiverBalance.subtract(transferAmount);
        }
        if (transfer.getType() == 2) {
            senderBalance = senderBalance.subtract(transferAmount);
            receiverBalance = receiverBalance.add(transferAmount);
        }
    }


    private Account mapRowToAccount (SqlRowSet rs){
        Account account = new Account();
        account.setId(rs.getInt("account_id"));
        account.setUser(rs.getInt("user_id"));
        account.setUserBalance(rs.getBigDecimal("balance"));

        return account;
    }
}
