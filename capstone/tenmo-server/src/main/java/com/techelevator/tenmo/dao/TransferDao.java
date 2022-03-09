package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Transfer;

import java.util.List;

public interface TransferDao {
    List<Transfer> list(int userId);
    Transfer get(int transferId);
    Transfer create(Transfer transfer);
    //boolean delete(int transferId);
}
