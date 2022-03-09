package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import javax.security.auth.login.AccountNotFoundException;
import java.math.BigDecimal;

public class JdbcAccountDao implements AccountDao{

    private JdbcTemplate jdbcTemplate;

    public JdbcAccountDao(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Account findAccountById(int account_id) throws AccountNotFoundException{
        Account accountId = null;
        String sql = "SELECT * FROM account WHERE account_id = ?;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, account_id);
        if (results.next()) {
            accountId = mapToRow(results);
        }else{
            throw new AccountNotFoundException();
        }
            return accountId;
    }

    @Override
    public BigDecimal getBalance(int id) {
        return null;
    }

    private Account mapToRow(SqlRowSet rs){
        Account acct = new Account();
        acct.setAccount_id(rs.getInt("account_id"));
        acct.setUser_id(rs.getInt("user_id"));
        acct.setBalance(rs.getBigDecimal("balance"));
        return acct;
    }
}
