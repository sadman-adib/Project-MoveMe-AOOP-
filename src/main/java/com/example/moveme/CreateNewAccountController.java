package com.example.moveme;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class CreateNewAccountController extends BalanceInterface{

    @FXML
    private TextField CardNumber;

    @FXML
    private PasswordField ConfirmPassword;

    @FXML
    private Button Exit;

    @FXML
    private TextField FirstName;

    @FXML
    private TextField LastName;

    @FXML
    private TextField NIDNumber;

    @FXML
    private PasswordField Password;

    @FXML
    private TextField PhoneNumber;

    /*public CreateNewAccountController(TextField CardNumber){
        this.CardNumber = CardNumber;
        this.NIDNumber =NIDNumber;
        this.Password = Password;
        this.FirstName = FirstName;
        this.LastName = LastName;
        this.PhoneNumber = PhoneNumber;
        this.ConfirmPassword = ConfirmPassword;

    }*/

    public String GetCardNumber(){
        String s = CardNumber.getText();
        return s;
    }
    @FXML
    void ClickOnExit(ActionEvent event) {
        System.exit(0);
    }
    @FXML
    void ClickOnSignUp(ActionEvent event) throws IOException, ClassNotFoundException {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("MoveME");
        if(Password.getText().isBlank() || FirstName.getText().isEmpty() || LastName.getText().isBlank() || NIDNumber.getText().isBlank()
                || PhoneNumber.getText().isBlank() || CardNumber.getText().isBlank() || ConfirmPassword.getText().isBlank()){
                     alert.setContentText("Please fill all the information Box");
        }
        else if(Password.getText().equals(ConfirmPassword.getText())){


            String Name=FirstName.getText()+" "+LastName.getText();
            String phone=PhoneNumber.getText();
            String NID=NIDNumber.getText();
            String pass=Password.getText();
            String card=CardNumber.getText();



                new ConnectToDatabase().SaveNewUserData(Name,phone,NID,pass,card);


            Alert alert1=new Alert(Alert.AlertType.INFORMATION);
            alert1.setContentText("Your account has been created \n Please login to recharge");
            alert1.show();

        }
        else{
                   alert.setContentText("Sign Up unsuccessful!\nplease check that your Entered password and confirmed password are same");
                   ConfirmPassword.clear();Password.clear();
        }
        alert.setHeaderText("Successfully Your Account has created");
        alert.show();

    }




}

