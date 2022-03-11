package com.techelevator.tenmo.controller;


import com.techelevator.tenmo.Services.RestAccountService;
import com.techelevator.tenmo.dao.TransferDao;
import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.Transfer;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/account/transfers/")
@PreAuthorize("isAuthenticated()")
public class TransferController {
    private TransferDao dao;
    private RestAccountService accountService;

    // Finished up the constructor and Request Mapping on this page

    public TransferController(TransferDao dao, RestAccountService accountService){
        this.dao = dao;
        this.accountService=accountService;
    }

    @RequestMapping( value = "{id}/list", method = RequestMethod.GET)
    public List <Transfer> getAllTransfers(@PathVariable int id){
      return dao.getAllList(id);
    }

    @RequestMapping ( value ="{id}", method = RequestMethod.GET)
    public Transfer getOneTransfer(@PathVariable int id){
        return dao.getSingleTransfer(id);
    }


   //This would be a POST to update the transactions
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "{id}",method = RequestMethod.POST)
            public void newTransaction(@Valid @RequestBody Transfer completedTransfer){

        if (! dao.createTransaction(completedTransfer.getTransferTypeId(),completedTransfer.getTransferStatusId(),
             completedTransfer.getAccountFrom(),completedTransfer.getAccountTo(), completedTransfer.getAmount())){
         throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Transfer Request Failed.");
        }
    }

/*
   //This would be a PUT to update the balance - maybe this would go into Account Controller

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(value = "",method = RequestMethod.PUT)
    public void updateBalance(@Valid @RequestBody Transfer completedTranscation){
        dao.create(completedTranscation);
    }
*/




}
