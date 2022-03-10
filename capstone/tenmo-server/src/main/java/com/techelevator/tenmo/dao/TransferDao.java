package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Transfer;

import java.util.List;

public interface TransferDao {

    //Updated list name from list to get All List so that it makes more sense in the controller

    List<Transfer> getAllList(int userId);
    Transfer getSingleTransfer(int transferId);
    Transfer create(Transfer transfer);
    //boolean delete(int transferId);
}
