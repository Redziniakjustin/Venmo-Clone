package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.security.auth.login.AccountNotFoundException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcAccountDao implements AccountDao{

    private JdbcTemplate jdbcTemplate;

    public JdbcAccountDao(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Account findAccountById(int account_id){
        Account accountId = null;
        String sql = "SELECT * FROM account WHERE user_id = ?;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, account_id);
        try {
            if (results.next()) {
                accountId = mapToRow(results);
            }
        }catch(DataAccessException e){
            //Change message?
            System.out.println("Account "+ account_id +" was not found.");
        }
            return accountId;
    }

    @Override
    public BigDecimal getBalance(int account_id) {
        BigDecimal balance = new BigDecimal("0.00");
        String sql = "SELECT balance FROM account WHERE account_id = ?;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, account_id);
        try {
            if (results.next()) {
                // map to Row?
                balance = results.getBigDecimal("balance");
            }
        } catch (DataAccessException e){

            //Change message?
                System.out.println("Account " + account_id + " was not found.");
            }
            return balance;
        }

    @Override
    public List<Account> getAllAccounts() {
        List<Account> getAllAcounts = new ArrayList<>();
        String sql = "SELECT username FROM tenmo_user AS tu LEFT JOIN account AS a ON tu.user_id = a.account_id;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while (results.next()) {
            Account account = mapToRow(results);
            getAllAcounts.add(account);
        }
        return getAllAcounts;
    }

    private Account mapToRow(SqlRowSet rs){
        Account acct = new Account();
        acct.setAccount_id(rs.getInt("account_id"));
        acct.setUser_id(rs.getInt("user_id"));
        acct.setBalance(rs.getBigDecimal("balance"));
        return acct;
    }
}
