package com.techelevator.tenmo.model;

import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;

public class Account {
    public int account_id;
    @NotEmpty

    public int user_id;
    @NotEmpty

    public BigDecimal balance;

    public Account (){

    }
   // public Account(int account_id, int user_id, BigDecimal balance){
    //    this.account_id = account_id;
    //    this.user_id = user_id;
    //    this.balance = balance;
   // }

    public int getAccount_id() {
        return account_id;
    }
    public int getUser_id(){
        return user_id;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setAccount_id(int account_id) {
        this.account_id = account_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}
