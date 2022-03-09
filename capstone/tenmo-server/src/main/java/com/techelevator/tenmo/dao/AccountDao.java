package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;

import javax.security.auth.login.AccountNotFoundException;
import java.math.BigDecimal;

public interface AccountDao {
    Account findAccountById(int id) throws AccountNotFoundException;

    BigDecimal getBalance(int id);

}
