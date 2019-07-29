/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libraryassistant.signup;

import java.net.URL;
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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import static libraryassistant.signup.FXMLController.userInfoList;

/**
 * FXML Controller class
 *
 * @author Hasan Sabuj
 */
public class ForgotPassController implements Initializable {

    @FXML
    private Label notification;
    @FXML
    private TextField emailField;
    @FXML
    private TextField passField;
    @FXML
    private TextField confirmpassField;

    /**
     * Initializes the controller class.
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
    private void resetButtonAction(MouseEvent event) throws SQLException {
        String email=emailField.getText();
        String password=passField.getText();
        String confirmpass=confirmpassField.getText();
        
        
        if(!password.equals(confirmpass)){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Password doesnt match");
            
            alert.showAndWait();
            }
        
        else{
            
            for(userInfo obj:userInfoList){
            if(emailField.getText().equals(obj.getEmail())){
                userInfo userobj=new userInfo(email,password,confirmpass);
        
        //database action
        DatabaseAction dbAction=new DatabaseAction();
        dbAction.updateuserInfo(userobj);
        
        
        
        emailField.clear();
        confirmpassField.clear();
        passField.clear();
            }
        else{ 
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Email doesnt match");
            
            alert.showAndWait();
                }
            }
        }
            
        
        }
    }
