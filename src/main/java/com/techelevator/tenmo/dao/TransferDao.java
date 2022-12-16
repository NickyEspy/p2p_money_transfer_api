package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Transfer;
import com.techelevator.tenmo.model.User;

import java.math.BigDecimal;
import java.util.List;

public interface TransferDao {

    Transfer createTransfer(Transfer transfer);

    Transfer requestTransfer(int id, BigDecimal amount);

    List<Transfer> listTransfersByUserId(int user_id);

    List<Transfer> listAllTransfers();

    Transfer getTransferById(int transfer_id);

}