package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Transfer;
import com.techelevator.tenmo.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcTransferDao implements TransferDao {

    private final JdbcTemplate jdbcTemplate;
    public JdbcTransferDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Transfer createTransfer(Transfer transfer) {
        String sql = "INSERT INTO transfer (transfer_id, transfer_type_id, transfer_status_id, account_from, account_to, amount) " +
                "VALUES (DEFAULT, 2, 1, ?, ?, ?)";
        System.out.println(transfer.toString());
        jdbcTemplate.update(sql, transfer.getAccountFrom(), transfer.getAccountTo(), transfer.getAmount());
//        jdbcTemplate.update(sql, transfer.getType(), transfer.getStatus(), transfer.getAccountFrom(), transfer.getAccountTo(), transfer.getAmount());
        return transfer;
    };

    //This method will be for requesting money from other users
    @Override
    public Transfer requestTransfer(int id, BigDecimal amount) {
        return null;
    }

    @Override
    public List<Transfer> listTransfersByUserId(int account_id) {
        List<Transfer> transfers = new ArrayList<>();
        String sql = "SELECT transfer_id, transfer_type_id, transfer_status_id, account_from, account_to, amount FROM transfer t " +
                "JOIN account a ON t.account_from = a.account_id " +
                "WHERE a.account_id = ?;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, account_id);
        while (results.next()) {
            Transfer transfer = mapRowToTransfer(results);
            transfers.add(transfer);
        }
        return transfers;
    }

    @Override
    public List<Transfer> listAllTransfers() {
        List<Transfer> transfers = new ArrayList<>();
        String sql = "SELECT * FROM transfer;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while (results.next()) {
            Transfer transfer = mapRowToTransfer(results);
            transfers.add(transfer);
        }
        return transfers;
    }

    @Override
    public Transfer getTransferById(int transfer_id) {
        Transfer transfer = new Transfer();
        String sql = "SELECT transfer_id, t.transfer_type_id, t.transfer_status_id, account_from, account_to, amount " +
                "FROM transfer t " +
                "JOIN transfer_type tp ON t.transfer_type_id = tp.transfer_type_id " +
                "JOIN transfer_status ts ON t.transfer_status_id = ts.transfer_status_id " +
                "WHERE transfer_id = ?;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, transfer_id);
        while (results.next()) {
            transfer = mapRowToTransfer(results);
        }
        return transfer;
    };

    private Transfer mapRowToTransfer(SqlRowSet rs) {
        Transfer transfer = new Transfer();
        transfer.setId(rs.getInt("transfer_id"));
        transfer.setType(rs.getInt("transfer_type_id"));
        transfer.setStatus(rs.getInt("transfer_status_id"));
        transfer.setAccountFrom(rs.getInt("account_from"));
        transfer.setAccountTo(rs.getInt("account_to"));
        transfer.setAmount(rs.getBigDecimal("amount"));
        return transfer;
    }
}