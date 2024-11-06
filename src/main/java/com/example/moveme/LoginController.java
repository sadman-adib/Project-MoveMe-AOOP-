package com.example.moveme;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginController {

    private  Stage stage;

    @FXML
    private Button Exit;

    @FXML
    private PasswordField Password;

    @FXML
    private TextField UserName;

    @FXML
    private Hyperlink ForgetPassword;

    @FXML
    private TextField cardField;
    @FXML
    private Button login;

    @FXML
    private Hyperlink signUp;

    @FXML
    void ClickOnExit(ActionEvent event) {
             System.exit(0);
    }

    @FXML
    void CreateAccount(ActionEvent event) throws IOException {

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("CreateNewAccount.fxml"));


            Scene scene = new Scene(fxmlLoader.load(), 800,500 );

            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setTitle("Create New Account");
            stage.setScene(scene);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.show();
    }


    @FXML
    void ForgetPassword(ActionEvent event) throws IOException {

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("loginPage.fxml"));


            Scene scene = new Scene(fxmlLoader.load(), 800,500 );

            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setTitle("Forget Password");
            stage.setScene(scene);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.show();

    }

    @FXML
    void LoginButton(ActionEvent event) throws IOException {
        String name=UserName.getText();
        String pass= Password.getText();
        String card= cardField.getText();
        String phone;
        String NID;
        ResultSet rs = null;
        PreparedStatement pst= null;
        Connection conn= null;

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
             conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/movemeproject","root","");
             pst= conn.prepareStatement("select * from userdatatable where userdatatable.Card_Number=?" );
            pst.setString(1,card);
            rs= pst.executeQuery();
            while(rs.next()){
                 name =rs.getString("Name");
                 phone=rs.getString("Phone");
                NID=rs.getString("NID");
                pass =rs.getString("Password");

                System.out.println(name);
                System.out.println(pass);
                System.out.println(phone);
                System.out.println(NID);
                System.out.println("retrieve successfully");

            }

        }
        catch(Exception e){
            System.out.println(e);
        }
        finally{
            try{

                pst.close();
                rs.close();
                conn.close();

            }
            catch(Exception e){
                e.printStackTrace();

            }
        }

        if(UserName.getText().equals(name) && Password.getText().equals(pass)) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("LoginDashBoard.fxml"));


            Scene scene = new Scene(fxmlLoader.load(), 604,400 );

            LoginDashBoardController controller= fxmlLoader.getController();
            controller.setTextFieldData(UserName.getText(),cardField.getText());
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setTitle("Welcome to Dash Board");
            stage.setScene(scene);
            //stage.initStyle(StageStyle.UNDECORATED);
            stage.show();
        }else{
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("MoveME");
                alert.setContentText("login unsuccessful!\nplease check your User Id or Password");
                alert.setHeaderText("Login");
                alert.show();
            }

        }
}
