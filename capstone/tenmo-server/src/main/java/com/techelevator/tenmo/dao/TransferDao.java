package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Transfer;

import java.math.BigDecimal;
import java.util.List;

public interface TransferDao {

    //Updated list name from list to get All List so that it makes more sense in the controller

    List<Transfer> getAllList(int userId);
    Transfer getSingleTransfer(int transferId);
    boolean createTransaction(int transfer_type_id, int transfer_status_id, int account_from, int account_to, BigDecimal amount);
    Integer updateBalance(int account_id);

    //Transfer getId (int transferId);
    //boolean delete(int transferId);
}
