/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libraryassistant.signup;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import static libraryassistant.signup.FXMLController.userInfoList;

/**
 * FXML Controller class
 *
 * @author Hasan Sabuj
 */
public class LoginController implements Initializable {

    @FXML
    private PasswordField passField;
    @FXML
    private TextField emailField;
    @FXML
    private Button loginbutton;
    @FXML
    private Button forgotpassbutton;
    @FXML
    private Label notification;

    /**
     * Initializes the controller class.
     */
    static ObservableList<userInfo> userInfoList=FXCollections.observableArrayList();
    static ObservableList<adminInfo> adminInfoList=FXCollections.observableArrayList();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        DatabaseAction dbAction=new DatabaseAction();
        try {
            userInfoList=dbAction.getAlluserInfo();
        } catch (SQLException ex) {
            Logger.getLogger(FXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            adminInfoList=dbAction.getAlladminInfo();
        } catch (SQLException ex) {
            Logger.getLogger(FXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void loginbuttonAction(MouseEvent event) {
        notification.setText("");
        String email=emailField.getText();
        String pass=passField.getText();
        
        for(userInfo userobj:userInfoList){
            if(userobj.getEmail().equals(email) && userobj.getPassword().equals(pass)){
            try {

                    //add you loading or delays - ;-)
                    Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow();
                    //stage.setMaximized(true);
                    stage.close();
                    Scene scene = new Scene(FXMLLoader.load(getClass().getResource("userHomepage.fxml")));
                    stage.setScene(scene);
                    stage.show();

                } catch (IOException ex) {
                    System.err.println(ex.getMessage());
                }
            }
            /*else{
            notification.setText("Wrong E/P");
            break;
            }*/
        }
        for(adminInfo adminobj:adminInfoList){
            if(adminobj.getEmail().equals(email) && adminobj.getPassword().equals(pass)){
            try {

                    //add you loading or delays - ;-)
                    Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow();
                    //stage.setMaximized(true);
                    stage.close();
                    Scene scene = new Scene(FXMLLoader.load(getClass().getResource("adminHomepage.fxml")));
                    stage.setScene(scene);
                    stage.show();

                } catch (IOException ex) {
                    System.err.println(ex.getMessage());
                }
            }
            else{
            notification.setText("Wrong E/P");
            break;
            }
        }
        
        
    }

    @FXML
    private void ForgotpassAction(MouseEvent event) {
        try {

                    //add you loading or delays - ;-)
                    Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow();
                    //stage.setMaximized(true);
                    stage.close();
                    Scene scene = new Scene(FXMLLoader.load(getClass().getResource("forgotPass.fxml")));
                    stage.setScene(scene);
                    stage.show();

                } catch (IOException ex) {
                    System.err.println(ex.getMessage());
                }
    }

    
}
