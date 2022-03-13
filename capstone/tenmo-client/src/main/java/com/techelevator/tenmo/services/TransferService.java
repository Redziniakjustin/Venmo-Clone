package com.techelevator.tenmo.services;

import com.techelevator.tenmo.model.AuthenticatedUser;
import com.techelevator.tenmo.model.Transfer;
import com.techelevator.tenmo.model.User;
import com.techelevator.util.BasicLogger;
import io.cucumber.java.bs.A;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

public class TransferService {
    public static final String API_BASE_URL = "http://localhost:8080/";
    private RestTemplate restTemplate = new RestTemplate();

    private String authToken = null;
    public AuthenticatedUser currentUser = new AuthenticatedUser();
    public AccountService accountService = new AccountService();

    public Transfer[] getAllTransfers(int id){
      //  Transfer[] allTransfer = null;
       // try{
            ResponseEntity<Transfer[]> response = restTemplate.exchange(API_BASE_URL + "account/transfers/" + id + "/list", HttpMethod.GET, makeAuthEntity(), Transfer[].class);
            return response.getBody();
      //  }catch(RestClientResponseException | ResourceAccessException e){
        //    BasicLogger.log(e.getMessage());
       // }
      //  return response.getBody();
    }

   // public Transfer viewPendingRequests()

    public void sendBucks(int recipientId, BigDecimal amount){
        Transfer sendMoney = new Transfer();
        String RESTUrl = API_BASE_URL + "users/user/" + recipientId;
        User recipient = restTemplate.exchange(API_BASE_URL + "users/user/" + recipientId, HttpMethod.GET, makeAuthEntity(), User.class).getBody();

        if(!recipient.getId().equals(currentUser.getUser().getId()) && (amount.compareTo(accountService.getBalance()) == 0 || amount.compareTo(accountService.getBalance()) == -1)){
            sendMoney.setAccountTo(recipient.getId());
            sendMoney.setAccountFrom(currentUser.getUser().getId());
            sendMoney.setAmount(amount);
            executeTransfer(sendMoney);
        }
        else if(recipient.getId().equals(currentUser.getUser().getId())){
            System.out.println("Cannot transfer funds to yourself.");
        }
        else if(amount.compareTo(accountService.getBalance()) == 1){
            System.out.println("Insufficient funds.");
        }

        System.out.println("[FOR TESTING REMOVE LATER] Transaction Complete!");
      }

    public void requestBucks(){

    }

    public void executeTransfer(Transfer transaction){
        //Add a transfer to the transfer table
        //HttpEntity<Transfer> sendRequest = new HttpEntity<>(transaction);
        restTemplate.exchange(API_BASE_URL + "/account/transfers/sendTransfer", HttpMethod.POST, makeAuthTransferEntity(transaction), Transfer.class);


        //Update recipient's account balance
        //Update the sender's account balance
    }



    private HttpEntity<Void> makeAuthEntity(){
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(currentUser.getToken());
        return new HttpEntity<>(headers);
    }

    private HttpEntity<Transfer> makeAuthTransferEntity(Transfer transfer){
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(currentUser.getToken());
        return new HttpEntity<>(transfer, headers);
    }


}
