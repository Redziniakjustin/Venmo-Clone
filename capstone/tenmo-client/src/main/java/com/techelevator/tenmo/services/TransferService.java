package com.techelevator.tenmo.services;

import com.techelevator.tenmo.model.Transfer;
import com.techelevator.util.BasicLogger;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

public class TransferService {
    public static final String API_BASE_URL = "http://localhost:8080/account/transfers/";
    private RestTemplate restTemplate = new RestTemplate();

    private String authToken = null;

    public Transfer[] getAllTransfers(int id){
      //  Transfer[] allTransfer = null;
       // try{

            ResponseEntity<Transfer[]> response = restTemplate.exchange(API_BASE_URL +id+"/list", HttpMethod.GET, makeAuthEntity(), Transfer[].class);
            return response.getBody();
      //  }catch(RestClientResponseException | ResourceAccessException e){
        //    BasicLogger.log(e.getMessage());
       // }
      //  return response.getBody();
    }

   // public Transfer viewPendingRequests()

    public void sendBucks(){

    }
    //Ask hunter: Should this be a void method or a BigDecimal method?

    private HttpEntity<Void> makeAuthEntity(){
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(authToken);
        return new HttpEntity<>(headers);
    }
}
