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

        if(!recipient.getId().equals(currentUser.getUser().getId())){
            sendMoney.setAccountTo(recipient.getId());
            sendMoney.setAccountFrom(currentUser.getUser().getId());
            sendMoney.setAmount(amount);
        }
        else{
            System.out.println("Cannot transfer funds to yourself.");
        }
        System.out.println("[FOR TESTING REMOVE LATER] Transaction Complete!");
      }

      public void requestBucks(){

      }

    private HttpEntity<Void> makeAuthEntity(){
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(currentUser.getToken());
        return new HttpEntity<>(headers);
    }
}
