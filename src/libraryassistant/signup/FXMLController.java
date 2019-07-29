/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libraryassistant.signup;

import java.io.IOException;
import java.math.BigInteger;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;


/**
 * FXML Controller class
 *
 * @author Hasan Sabuj
 */
public class FXMLController implements Initializable {
   

    @FXML
    private TextField ConfirmpassField;
    @FXML
    private TextField NameField;
    @FXML
    private TextField PasswordField;
    @FXML
    private TextField userIDField;
    @FXML
    private TextField EmailField;
    @FXML
    private Label notification;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    static ObservableList<userInfo> userInfoList=FXCollections.observableArrayList();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        DatabaseAction dbAction=new DatabaseAction();
        try {
            userInfoList=dbAction.getAlluserInfo();
        } catch (SQLException ex) {
            Logger.getLogger(FXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
    
    

    @FXML
    private void signUpAction(ActionEvent event) throws IOException, SQLException {
        
        if(userIDField.getText().equals("")) return;
        
        else {
        int id=Integer.parseInt(userIDField.getText());
        String name=NameField.getText();
        String email=EmailField.getText();
        String password=encryptThisString(PasswordField.getText());
        String confirmpass=encryptThisString(ConfirmpassField.getText());
        
        if(!email.contains("@gmail.com")){
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Invalid Email");
            
            alert.showAndWait();
            
        }
       
        
          if((!password.equals(confirmpass)) || (PasswordField.getText().length()<7)){
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Password doesnt match or length is short or invalid email");
            
            alert.showAndWait();
            
          
                    }
        else{
              userInfo userobj=new userInfo(id,name,email,password,confirmpass);
        
        
        
        //database action
        DatabaseAction dbAction=new DatabaseAction();
        dbAction.adduserInfo(userobj);
        
        
        NameField.clear();
        EmailField.clear();
        userIDField.clear();
        ConfirmpassField.clear();
        PasswordField.clear();
        }
        
        //if(name.equals("") && email.equals("")) return;
        }
    
}

    @FXML
    private void emailfieldAction(KeyEvent event) {
    notification.setText("");
        String email=EmailField.getText();
        
        for(userInfo userobj:userInfoList){
            if(userobj.getEmail().equals(email)){
            notification.setText("Not available");
            break;
            }
        }
    }
    
    public static String encryptThisString(String pass) 
    { 
        try { 
            // getInstance() method is called with algorithm MD2 
            MessageDigest md = MessageDigest.getInstance("MD2"); 
  
            // digest() method is called 
            // to calculate message digest of the input string 
            // returned as array of byte 
            byte[] messageDigest = md.digest(pass.getBytes()); 
  
            // Convert byte array into signum representation 
            BigInteger no = new BigInteger(1, messageDigest); 
  
            // Convert message digest into hex value 
            String hashtext = no.toString(16); 
  
            // Add preceding 0s to make it 32 bit 
            while (hashtext.length() < 32) { 
                hashtext = "0" + hashtext; 
            } 
  
            // return the HashText 
            return hashtext; 
        } 
  
        // For specifying wrong message digest algorithms 
        catch (NoSuchAlgorithmException e) { 
            throw new RuntimeException(e); 
        } 
    } 
  
}
