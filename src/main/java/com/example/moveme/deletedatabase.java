package com.example.moveme;
import com.sun.tools.javac.Main;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;

 class DeleteAllData {

    public static void main(String[] args) {


        try{

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/movemeproject","root","");
            String sql = "DELETE FROM balancetable";
            PreparedStatement pst = conn.prepareStatement(sql);

            pst.execute();
            String sql1 = "DELETE FROM userdatatable";
            PreparedStatement pst1 = conn.prepareStatement(sql1);

            pst1.execute();
            System.out.println("delete all info successfully");

        }
        catch(Exception e){
            System.out.println(e);
        }
    }

}
public class deletedatabase {

}
