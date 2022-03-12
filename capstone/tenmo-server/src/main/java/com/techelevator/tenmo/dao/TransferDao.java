package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Transfer;

import java.math.BigDecimal;
import java.util.List;

public interface TransferDao {

    //Updated list name from list to get All List so that it makes more sense in the controller

    List<Transfer> getAllList(String username);
    Transfer getSingleTransfer(int transferId);
    public void createTransaction(Transfer transfer);
    Integer updateBalance(int id);

    Transfer sendTransfer (int account_id, int user_id, int user_from_id, int user_to_id, BigDecimal amount);
    Transfer requestTransfer (int user_from_id, int user_to_id, BigDecimal amount);


    //Transfer getId (int transferId);
    //boolean delete(int transferId);
}
