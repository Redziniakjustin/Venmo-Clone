package com.techelevator.tenmo;

import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.AuthenticatedUser;
import com.techelevator.tenmo.model.User;
import com.techelevator.tenmo.model.UserCredentials;
import com.techelevator.tenmo.services.*;

import java.math.BigDecimal;
import java.security.Principal;

public class App {

    private static final String API_BASE_URL = "http://localhost:8080/";

    private final ConsoleService consoleService = new ConsoleService();
    private final AuthenticationService authenticationService = new AuthenticationService(API_BASE_URL);
    private AccountService accountService = new AccountService();
    private UserService userService = new UserService();
    private final TransferService transferService = new TransferService();

    private AuthenticatedUser currentUser;



    public static void main(String[] args) {
        App app = new App();
        app.run();
    }

    private void run() {
        consoleService.printGreeting();
        loginMenu();
        if (currentUser != null) {
            mainMenu();
        }
    }
    private void loginMenu() {
        int menuSelection = -1;
        while (menuSelection != 0 && currentUser == null) {
            consoleService.printLoginMenu();
            menuSelection = consoleService.promptForMenuSelection("Please choose an option: ");
            if (menuSelection == 1) {
                handleRegister();
            } else if (menuSelection == 2) {
                handleLogin();
            } else if (menuSelection != 0) {
                System.out.println("Invalid Selection");
                consoleService.pause();
            }
        }
    }

    private void handleRegister() {
        System.out.println("Please register a new user account");
        UserCredentials credentials = consoleService.promptForCredentials();
        if (authenticationService.register(credentials)) {
            System.out.println("Registration successful. You can now login.");
        } else {
            consoleService.printErrorMessage();
        }
    }

    private void handleLogin() {
        UserCredentials credentials = consoleService.promptForCredentials();
        currentUser = authenticationService.login(credentials);
        if (currentUser == null) {
            consoleService.printErrorMessage();
        }else{
            accountService.currentUser = currentUser;
            transferService.currentUser = currentUser;
        }
    }

    private void mainMenu() {
        int menuSelection = -1;
        while (menuSelection != 0) {
            consoleService.printMainMenu();
            menuSelection = consoleService.promptForMenuSelection("Please choose an option: ");
            if (menuSelection == 1) {
                viewCurrentBalance();
            } else if (menuSelection == 2) {
                viewTransferHistory();
            } else if (menuSelection == 3) {
                viewPendingRequests();
            } else if (menuSelection == 4) {
                sendBucks();
            } else if (menuSelection == 5) {
                requestBucks();
            } else if (menuSelection == 0) {
                continue;
            } else {
                System.out.println("Invalid Selection");
            }
            consoleService.pause();
        }
    }

	private void viewCurrentBalance() {

        if(currentUser != null){
            System.out.println(accountService.getBalance());
        }else{
            consoleService.printErrorMessage();
        }
		
	}

	private void viewTransferHistory() {
		if(currentUser != null){
            System.out.println(transferService.getAllTransfers(currentUser.getUser().getId()));
        }else{
            consoleService.printErrorMessage();
       }
    }

	private void viewPendingRequests() {
		//if()
	}

	private void sendBucks() {
        int menuSelection = -1;
        while (menuSelection != 0) {
            consoleService.printTEBucksMenu();
            menuSelection = consoleService.promptForMenuSelection("Please choose an option: ");
            if (menuSelection == 1) {
                //THIS WORKS NOW - however is not authorized ( I commented it out on the Users Controller- Server Side
                User[] allUsers = userService.getAllUsers();
                consoleService.printUserMenu(allUsers);
            }
            else if (menuSelection == 2) {
                //this will be where our transfer methods will now go
                int recipentId = consoleService.promptForInt("Who will you send money to? Please enter their id and press enter to continue (0 to cancel): ");
                BigDecimal amount = consoleService.promptForBigDecimal("Enter amount: ");

                transferService.sendBucks(recipentId, amount);

            } else if (menuSelection != 0) {
                System.out.println("Invalid Selection");
                consoleService.pause();
            }
    }
	}


	private void requestBucks() {
		// TODO Auto-generated method stub
		
	}

}
