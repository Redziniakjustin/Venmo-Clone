package com.techelevator.tenmo.controller;


import com.techelevator.tenmo.Services.AccountService;
import com.techelevator.tenmo.dao.AccountDao;
import com.techelevator.tenmo.model.Account;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.PrintWriter;
import java.math.BigDecimal;
import java.security.Principal;

@RestController
@RequestMapping("/account")
@PreAuthorize("isAuthenticated()")

public class AccountController {
    private AccountDao dao;
    private AccountService accountService;

    public AccountController(AccountDao dao, AccountService accountService){
        this.dao=dao;
        this.accountService=accountService;
    }

    //GetListofAllAccounts? List<User>

    @RequestMapping(value = "", method = RequestMethod.GET)
    public Account getAccount(@PathVariable int account_id){
        return dao.findAccountById(account_id);
    }

   @RequestMapping(value = "/balance", method = RequestMethod.GET)
   public BigDecimal getBalance(@PathVariable int account_id) {
        return dao.findAccountById(account_id).getBalance();
    }


}

