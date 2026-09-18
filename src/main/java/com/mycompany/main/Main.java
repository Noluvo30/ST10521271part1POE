package com.mycompany.main;

// Imports Scanner so we can get user input
import java.util.Scanner;

// Creates the main class
public class Main {

    // Creates Scanner for user input
    static Scanner input = new Scanner(System.in);
    // Stores the user's entered details
    static String username;
    static String password;
    static String cellphone;

    // Creates the registered username
    static String registeredUsername;

    // Stores the registered password
    static String registeredPassword;

    // Stores registerd cellphone number
    static String registeredCellphone;

    // Create the username checking method
    public static boolean checkUserName(String username) {
        // Username must have exactly 5 characters
        // and must contain an underscore
        if (username.length() == 5 && username.contains("_")) {
            return true;
        } else {
        }
        // Temporary return
        return false;
    }

    // Creates the password checking method
    public static boolean checkPasswordComplexity(String password) {
        // password must have at least 8 characters
        // and contain uppercase, lowercase, number and special character
        if (password.length() >= 8
                && password.matches(".*[A-Z].*")
                && password.matches(".*[a-z].*")
                && password.matches(".*[0-9].*")
                && password.matches(".*[^a-zA-Z0-9].*")) {
            return true;
        } else {
        }
        // Temporary return value
        return false;
    }

    // Creates the Cellphone checking method
    public static boolean checkCellPhoneNumber(String number) {
        // Number must start with +27
        // followed by exactly 9 digits
        if (number.matches("^\\+27[0-9]{9}$")) {
            return true;
        } else {
        }
        // Temporary return value
        return false;
    }

    // Creates the registration method
// Creates the login method
    public static boolean loginUser(String username, String password) {
        // Compare entered details with registered details
        while (true) {
            while (true) {
            // Compare entered details with registered details
            if (username.equals(registeredUsername)
                    && password.equals(registeredPassword)) {
                return true;
            } else {

                // Login details are incorrect
                System.out.println("Username or password incorrect, please try again.");
                // Re-prompt the user for username
                System.out.println("Enter your username to login");
                username = input.nextLine();
                // Re-prompt the user for password
                System.out.println("Enter your password to login:");
                password = input.nextLine();
            }
        }
        }
    }

// Creates the login status method
    public static String returnloginStatus(boolean loginSuccessful) {
        // Check if login was successful
        if (loginSuccessful) {
            return "Welcome, it is great to sse you again.";
        } else {
            return "Username or password incorrect, please try again.";
        }
    }

    public static void registerUser() {
        // Infinite loop for username
        while (true) {
            // Ask for username
            System.out.println("Enter your username: ");
            username = input.nextLine();
            // Check username 
            boolean usernameCorrect = checkUserName(username);
            // Check if usernamr is correct 
            if (usernameCorrect) {
                break;
            } else {
                System.out.println("Username is incorrectly formated, Please try again");

            }
        }

        // infinite loop for password
        while (true) {
            //Ask for password
            System.out.print("enter your password: ");
            password = input.nextLine();
            // Check password
            boolean passwordCorrect = checkPasswordComplexity(password);
            // Check if password is correct
            if (passwordCorrect) {
                break;
            } else {
                System.out.println("password is incorrectly formated. please try again");
            }

        }
        // infinite loop for cell[hone number
        while (true) {
            // Ask for cellphone number
            System.out.print("Enter your cellphone number (+27): ");
            cellphone = input.nextLine();
            // Check cellphone number
            boolean phoneCorrect = checkCellPhoneNumber(cellphone);
            // Check if cellphone number is correct 
            if (phoneCorrect) {
                break;
            } else {
                System.out.println("cellphone number is incorrectly formated. please try agin");

            }

            // Save the valid username
            registeredUsername = username;
            // Save the valid password
            registeredPassword = password;
            // save the valid cellphone number
            registeredCellphone = cellphone;
            System.out.println("User registered successfully.");
        }
    }
    // Checks the login details

 

    // Main method where the program starts
    public static void main(String[] args) {
        // Call registration method
        registerUser();
        boolean loginSuccessful = loginUser(username, password);
        // Call Login status method
        System.out.println(returnloginStatus(loginSuccessful));

        // Calls the username method
        checkUserName("");

        // Calls the password method
        checkPasswordComplexity("");

        // Calls the cellphone method
        checkCellPhoneNumber("");

        // Calls the registration method
        loginUser("", "");

        // Calls the login status method
        returnloginStatus(false);

    }
}
