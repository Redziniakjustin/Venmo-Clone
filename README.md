# Tenmo-Clone

## 💸 Summary
This Capstone simulates a program similar to Venmo, demonstrating basic concepts of a fullstack application. This project was a pair-programming project my partner and I had done during week 9 at Tech Elevator. 


## 💥 Features

- As a user of the system, you are able to register with a username and password.
- A new registered user starts with an initial balance of 1,000 TE Bucks.
- As a user of the system, you are able to log in using your registered username and password.
- Logging in returns an Authentication Token. 
- As an authenticated user of the system, you are able to see your Account Balance.
- As an authenticated user of the system, you are able to send a transfer of a specific amount of TE Bucks to a registered user.
- You are able to choose from a list of users to send TE Bucks to.
- A transfer includes the User IDs of the from and to users and the amount of TE Bucks.
![image](https://user-images.githubusercontent.com/47091126/160251016-4e464c09-b982-4a1f-b4e2-98764b970d47.png)
- The receiver's account balance is increased by the amount of the transfer.
- The sender's account balance is decreased by the amount of the transfer.
- You cannot send more TE Bucks than in your account.
- You cannot send a zero or negative amount.
- As an authenticated user of the system, you are able to see transfers I have sent or received.
- As an authenticated user of the system, you can retrieve the details of any transfer based upon the transfer ID.

## 📅 Planning
<p align ="center">
<img src="https://user-images.githubusercontent.com/47091126/160249955-55877d19-da45-4e96-974f-4f0520cc7a81.png" width = "700" height="800" />
  
![image](https://user-images.githubusercontent.com/47091126/160251079-0ebea963-7a9d-4a7b-8a05-74a78e652e08.png)
  
## 🥵 Challenges
In the beginning, communicating from our server to our client was a bit challenging however gradually achieved success using a step by step approach with Postman. We created test data that the client would send from forms and began building the pieces that we needed in order to save it to our database.

## 🐛 Known Bugs
- Currently not able to see pending or requested transfers from user. 
- If you do no enter a user and enter a dollar about, 401 Exception in main thread.
- When viewing past transfers, if a transfer is incorrectly inputed and does not exist NullPointerException occurs.
- When sending money, if the incorrect user ID is entered, HttpClientErrorExceptionUnauthorized because the userID does not exist. 
  
## 🚀 Technologies Used
CLI and main application uses Java, while the databases (creating, reading, updating, and deleting) use PostgreSQL. Postman.

## ✊🏼 Team
Justin Redziniak & Kelsey Lau

