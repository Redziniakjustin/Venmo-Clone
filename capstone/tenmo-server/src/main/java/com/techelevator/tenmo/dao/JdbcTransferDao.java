package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.Transfer;
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

    @Override
    public List<Transfer> getAllList(int userId) {
        List<Transfer> transfers = new ArrayList<>();
        String sql = "SELECT transfer_id, transfer_type_id, transfer_status_id, account_from, account_to, amount \n" +
                "FROM transfer AS t INNER JOIN account AS a ON a.account_id = t.account_from WHERE a.user_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);
        while(results.next()){
            Transfer transferForList = mapToRow(results);
            transfers.add(transferForList);
        }
        return transfers;
    }

    @Override
    public Transfer getSingleTransfer(int transferId) {
        Transfer transfer = null;
        String sql = "SELECT * FROM transfer WHERE transfer_id = ?;";
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

    @Override
    public boolean createTransaction(int transfer_type_id, int transfer_status_id, int account_from, int account_to, BigDecimal amount) {
        String sql = "INSERT INTO transfer (transfer_type_id, transfer_status_id, account_from, account_to, amount) VALUES (?,?,?,?,?);";
        return jdbcTemplate.update(sql, transfer_type_id, transfer_status_id, account_from, account_to, amount) == 1;

    }

   /* @Override
    public boolean delete(int transferId) {
        String sql = "DELETE FROM transfer WHERE transferId=?";
        return jdbcTemplate.update(sql, transferId) == 1;
    }*/

    // Add and subtract to balance methods here? these would update amount


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
