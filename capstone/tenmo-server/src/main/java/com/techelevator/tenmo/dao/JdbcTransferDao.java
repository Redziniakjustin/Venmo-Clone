package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.Transfer;
import org.springframework.data.relational.core.sql.SQL;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcTransferDao implements TransferDao{
    private JdbcTemplate jdbcTemplate;

    public JdbcTransferDao(JdbcTemplate jdbcTemplate){

        this.jdbcTemplate = jdbcTemplate;
    }
    public static List<Transfer> listTransfers = new ArrayList<>();

    private static final String SQL_TRANSFER_BASE =
    "SELECT t.transfer_id, tt.transfer_type_desc, ts.transfer_status_desc, t.amount, "+
    "aFrom.account_id as fromAcc, "+
    "aFrom.user_id as fromU, aFrom.balance as fromB, "+
    "aTo.account_id as toAcc, aTo.user_id as toU, aTo.balance as toB "+
    "FROM transfer t "+
    "INNER JOIN transfer_type tt ON t.transfer_type_id = tt.transfer_type_id "+
    "INNER JOIN transfer_status ts ON t.transfer_status_id = ts.transfer_status_id "+
    "INNER JOIN account aFrom on account_from = aFrom.account_id "+
    "INNER JOIN account aTo on account_to = aTo.account_id;";

    //#6 GET ALL TRANSACTIONS FOR SPECIFIC USER
    @Override
    public List<Transfer> getAllList(int userId) {
        List<Transfer> transfers = new ArrayList<>();
     //   String sql = SQL_TRANSFER_BASE+ "WHERE a.user_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(SQL_TRANSFER_BASE, userId);
        while(results.next()){
            Transfer transferForList = mapToRow(results);
            transfers.add(transferForList);
        }
        return transfers;
    }

    // #5 INDIVIDUAL TRANSFERS USER COMPLETED
    @Override
    public Transfer getSingleTransfer(int transferId) {
        Transfer transfer = null;
      //  String sql = "SELECT transfer_type_id, transfer_status_id, account_from, account_to, amount FROM transfer WHERE transfer_id = ?;";
        String sql = SQL_TRANSFER_BASE+ "WHERE transfer_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, transferId);
        try{
        if(results.next()){
            transfer = mapToRow(results);
        }
        }catch(Exception e){
                System.out.println("The transfer: " + transferId + " was not found.");
        }
        return null;
    }

    // #4 NEW TRANSACTION POSTED
    @Override
    public boolean createTransaction(int transfer_type_id, int transfer_status_id, int account_from, int account_to, BigDecimal amount) {
        String sql = SQL_TRANSFER_BASE+"INSERT INTO transfer (transfer_type_id, transfer_status_id, account_from, account_to, amount) VALUES (?,?,?,?,?);";
        return jdbcTemplate.update(SQL_TRANSFER_BASE, transfer_type_id, transfer_status_id, account_from, account_to, amount) == 1;
    }

    // #4 ADD OR SUBTRACT USERS BALANCE
    public Integer updateBalance(int account_id){
        String sql = SQL_TRANSFER_BASE+"UPDATE account SET account_id = ?, balance =? WHERE account_id = ?;";
        return jdbcTemplate.update(sql,account_id);
    }

   // # REMOVE TRANSACTION
   // public boolean delete(int transferId) {
    //    String sql = "DELETE FROM transfer WHERE transferId=?";
     //   return jdbcTemplate.update(sql, transferId) == 1;
    //}


    private Transfer mapToRow(SqlRowSet rs){
        Transfer trns = new Transfer();
        trns.setTransferId(rs.getInt("transfer_id"));
        trns.setTransferTypeId(rs.getInt("transfer_type_id"));
        trns.setAccountFrom(rs.getInt("account_from"));
        trns.setAccountTo(rs.getInt("account_to"));
        trns.setAmount(rs.getBigDecimal("amount"));
        return trns;
    }
}
