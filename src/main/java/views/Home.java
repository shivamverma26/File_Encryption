package views;

import dao.UserDAO;
import model.User;
import service.GenerateOTP;
import service.SendOTPService;
import service.UserService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.Scanner;

public class Home {
    public void HomeScreen(){
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Welcome to the app");
        System.out.println("Enter 1 to Login");
        System.out.println("Enter 2 to SignUp");
        System.out.println("Enter 0 to Exit");
        int choice =0;
        try {
            choice =Integer.parseInt(br.readLine());
        }
       catch (IOException ex){
           throw new RuntimeException(ex);
        }
        switch(choice){
            case 1 ->  Login();
            case 2 -> SignUp();
            case 0 -> System.exit(0);
        }
    }

    private void Login() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter email");
        String email = sc.nextLine();
        try {
            if(UserDAO.isExists(email)) {
                String genOTP = GenerateOTP.getOTP();
                SendOTPService.sendOTP(email, genOTP);
                System.out.println("Enter the otp");
                String otp = sc.nextLine();
                if(otp.equals(genOTP)) {
                    new UserView(email).home();
                    System.out.println("Welcome");

                } else {
                    System.out.println("Wrong OTP");
                }
            } else {
                System.out.println("User not found");
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
    private void SignUp() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter name");
        String name = sc.nextLine();
        System.out.println("Enter email");
        String email = sc.nextLine();
        String genOTP = GenerateOTP.getOTP();
        SendOTPService.sendOTP(email, genOTP);
        System.out.println("Enter the otp");
        String otp = sc.nextLine();
        if(otp.equals(genOTP)) {
            User user = new User(name, email);
            int response = UserService.saveUser(user);
            switch (response) {
                case 0 -> System.out.println("User registered");
                case 1 -> System.out.println("User already exists");
            }
        } else {
            System.out.println("Wrong OTP");
        }

    }
    }




