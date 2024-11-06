package com.example.moveme;

import java.sql.*;
import java.util.ArrayList;

public class ConnectToDatabase{

    public void SaveNewUserData(String name, String phone, String NID, String password, String CardNumber){
        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/movemeproject","root","");
            String sql = "insert into userdatatable values(?,?,?,?,?)";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1,CardNumber);
            pst.setString(2,name);
            pst.setString(3,phone);
            pst.setString(4,NID);
            pst.setString(5,password);
            pst.executeUpdate();
            System.out.println("Data updated in User Table");
            new  ConnectToDatabase().SetBalanceTable(CardNumber,phone,0);
        }
        catch (Exception e){
            System.out.println(e);

        }
    }

    public void SetBalanceTable(String card,String phone,double amount){
        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/movemeproject","root","");
            String sql = "insert into balancetable values(?,?,?)";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1,card);
            pst.setString(2,phone);
            pst.setString(3,String.valueOf(amount));

            pst.executeUpdate();
            System.out.println("Data updated in Balance Table");

        }
        catch (Exception e){
            System.out.println(e);

        }
    }
    public void UpdateBalanceTable(String card,double balance){
        try{

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/movemeproject","root","");
            String sql = "update balancetable set Card_Number ='"+card+"',Balance ='"+balance+"' where balancetable.Card_Number=?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1,card);
            pst.execute();

            System.out.println("update successfully");

        }
        catch(Exception e){
            System.out.println(e);
        }
    }
    public double FindBalance(String card) {
        ResultSet rs = null;
        PreparedStatement pst = null;
        Connection conn = null;
        double balance = 0;
        try {


            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/movemeproject", "root", "");
            pst = conn.prepareStatement("select * from balancetable where balancetable.Card_Number=?");
            pst.setString(1, card);
            rs = pst.executeQuery();
            while (rs.next()) {
                String name = rs.getString("Card_Number");
                String pass = rs.getString("Phone");
                balance = Double.parseDouble(rs.getString("Balance"));
                System.out.println(name);
                System.out.println(pass);

                System.out.println("retrieve successfully");

            }


        } catch (Exception e) {
            System.out.println(e);
        } finally {
            try {

                pst.close();
                rs.close();
                conn.close();

            } catch (Exception e) {
                e.printStackTrace();

            }


        }
        return balance;
    }
    public void DeleteAccount(String card){
        try{

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/movemeproject","root","");
            String sql = "DELETE FROM userdatatable WHERE userdatatable.Card_Number=?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1,card);
            pst.execute();

            System.out.println("delete successfully");
            String sql1 = "DELETE FROM balancetable WHERE balancetable.Card_Number=?";
            PreparedStatement pst1 = conn.prepareStatement(sql1);
            pst1.setString(1,card);
            pst1.execute();

            System.out.println("delete successfully");
        }
        catch(Exception e){
            System.out.println(e);
        }
    }

}
