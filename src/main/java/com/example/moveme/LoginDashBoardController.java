package com.example.moveme;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class LoginDashBoardController {

    @FXML
    private TextField AccountNumber;

    @FXML
    private TextField Amount;

    @FXML
    private ImageView BkashImage;

    @FXML
    private TextField CardNumberToDelete;

    @FXML
    private AnchorPane DeleteAccountStage;

    @FXML
    private AnchorPane MainStage;

    @FXML
    private ImageView NagadImage;

    @FXML
    private TextField Otp;

    @FXML
    private AnchorPane PaymentStage;

    @FXML
    private AnchorPane TopUpStage;

    @FXML
    private Label UserName;

    @FXML
    private RadioButton bkash , nagad;

    @FXML
    private ImageView BusImage;
    private String card;
    @FXML
    public void DisplayImage(){

    }
    @FXML
    public void initialize(){
        Image Bi= new Image("C:\\Users\\Zobaer Ibn Razzaque\\Desktop\\Java\\JavaFx\\MoveMe\\MoveMe\\src\\main\\java\\com\\example\\moveme\\image\\Daco_2337118.png");
        BusImage.setImage(Bi);
        BusImage.setVisible(true);
    }
    @FXML
    void ClickOnBalanceButton() {
        TopUpStage.setVisible(false);
        DeleteAccountStage.setVisible(false);
        BusImage.setVisible(true);
        Alert balance = new Alert(Alert.AlertType.INFORMATION);
        double money=new ConnectToDatabase().FindBalance(card);
        balance.setContentText("Your Current Balance is : "+money+"taka");
        balance.setHeaderText("Balance Inquiry");
        balance.show();
    }

    @FXML
    void ClickOnConfirmButton(ActionEvent event) {
        // this one to set the balance into the database.
        new ConnectToDatabase().UpdateBalanceTable(card, Double.parseDouble(Amount.getText()));
    }

    @FXML
    void ClickOnConfirmToDelete(ActionEvent event) {
        //to delete Account from database.
        new ConnectToDatabase().DeleteAccount(CardNumberToDelete.getText());
    }

    @FXML
    void ClickOnDeleteButton(ActionEvent event) {
        DeleteAccountStage.setVisible(true);
        TopUpStage.setVisible(false);
        BusImage.setVisible(false);
    }

    @FXML
    void ClickOnExit(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    void ClickOnTopUpButton(ActionEvent event) {
        DeleteAccountStage.setVisible(false);
        TopUpStage.setVisible(true);

    }

    @FXML
    void GetMethod(ActionEvent event) {
        Image bkIm = new Image("C:\\Users\\Zobaer Ibn Razzaque\\Desktop\\Java\\JavaFx\\MoveMe\\MoveMe\\src\\main\\java\\com\\example\\moveme\\image\\bkash.gif");
        BkashImage.setImage(bkIm);
        Image naIm = new Image("C:\\Users\\Zobaer Ibn Razzaque\\Desktop\\Java\\JavaFx\\MoveMe\\MoveMe\\src\\main\\java\\com\\example\\moveme\\image\\nagad.png");
        NagadImage.setImage(naIm);
        if(bkash.isSelected()){
            System.out.println("bkash");
            BkashImage.setVisible(true);
            NagadImage.setVisible(false);
        } else if (nagad.isSelected()) {
            System.out.println("nagad");
            NagadImage.setVisible(true);
            BkashImage.setVisible(false);
        }
        PaymentStage.setVisible(true);
        DeleteAccountStage.setVisible(false);
    }
    public void setTextFieldData(String data,String cardNumber){
        UserName.setText(data);
        card=cardNumber;
    }

}
