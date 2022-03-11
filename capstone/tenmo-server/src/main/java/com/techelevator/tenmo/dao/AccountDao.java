package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;

import javax.security.auth.login.AccountNotFoundException;
import java.math.BigDecimal;
import java.util.List;

public interface AccountDao {
    Account findAccountById(int id);
    BigDecimal getBalance(int id);
    List<Account> getAllAccounts();
 //   String getUsernameByUserId(int user_id);

}
