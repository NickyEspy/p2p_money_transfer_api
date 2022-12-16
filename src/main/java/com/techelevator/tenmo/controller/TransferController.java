package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.dao.JdbcTransferDao;
import com.techelevator.tenmo.model.Transfer;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@PreAuthorize("isAuthenticated()")
@RequestMapping("/transfer")
public class TransferController {

    private final JdbcTransferDao dao;
    public TransferController(JdbcTransferDao dao) {
        this.dao = dao;
    }

//    public TransferController(TransferDao transferDao) {
//        this.transferDao = transferDao;
//    }

    @PreAuthorize("permitAll")
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "/createTransfer", method = RequestMethod.POST)
    public Transfer createTransfer(@RequestBody Transfer transfer) {
        return dao.createTransfer(transfer);
    }

    @PreAuthorize("permitAll")
    @RequestMapping(path = "/account/{account_id}", method = RequestMethod.GET)
    public List<Transfer> getMyTransfers(@PathVariable int account_id) {
        return dao.listTransfersByUserId(account_id);
    }

    @PreAuthorize("permitAll")
    @RequestMapping(path = "", method = RequestMethod.GET)
    public List<Transfer> getAllTransfers() {
        return dao.listAllTransfers();
    }


    @RequestMapping(path = "/{transfer_id}", method = RequestMethod.GET)
    public Transfer getTransfer(@PathVariable int transfer_id) {
        return dao.getTransferById(transfer_id);
    }

}