package Controller;

import Database.Db;
import Model.User;
import Model.UserProfile;
import java.sql.ResultSet;
import java.sql.SQLException;


public class UserOperation {
    Db db = new Db() ;
    
   
    

    public boolean registration( String userName , String email  ,String password , String street , String city , String country) throws SQLException{
        ResultSet result = db.executequery(String.format("SELECT * FROM User where Email = '%s' ",email )) ; 
          if (result.next() == false){
          int affectedRow = db.executeupdate(String.format("INSERT INTO User (UserName , Email , Password , Street ,City , Country ) VALUES('%s' , '%s' , '%s' , '%s' , '%s' , '%s')",
                  userName /*1*/ , 
                  email/*2*/,
                  password/*3*/ , 
                  street/*4*/ , 
                  city/*5*/ , 
                  country/*6*/));    
          db.close() ; 
          return true ; 
          }
          db.close() ; 
          return false ;
    }
    public boolean login(String email , String password) throws SQLException{
        ResultSet result = db.executequery(String.format("SELECT * FROM User WHERE Email='%s' AND Password='%s'" , email , password)) ;  
       
        if (result.next()) {
    if (UserProfile.user == null) {
        UserProfile.user = new User();
    }
    UserProfile.user.setName(result.getString("UserName"));
    UserProfile.user.setEmail(result.getString("Email")); 
    UserProfile.user.setStreet(result.getString("Street")); 
    UserProfile.user.setCity(result.getString("City"));
    UserProfile.user.setCountry(result.getString("Country"));
    return true;  // Login successful
}
return false; 
    
    }
    
}
