package com.example.moveme;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class BalanceInterface {

    @FXML
    private TextField Balance;

    @FXML
    private Label cardNumLabel;
    @FXML
    private Button Exit;

    @FXML
    void ClickOnExit(ActionEvent event) {
        System.exit(0);
    }
    @FXML
    public void initialize(ActionEvent event){


    }
    @FXML
    void ClickOnTopUp(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("MoveME");
        if (Balance.getText().isBlank()) {
            alert.setContentText("Enter your Balance");
        } else {
            alert.setContentText("You have recharged " + Balance.getText() + " Taka");
        }
        alert.show();
    }
    public void initialize(){
    }
}






