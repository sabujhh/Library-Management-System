/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libraryassistant.signup;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import static libraryassistant.signup.ForgotPassController.userInfoList;

/**
 * FXML Controller class
 *
 * @author Hasan Sabuj
 */
public class IssuebookController implements Initializable {

    @FXML
    private Button issuebooks;
    @FXML
    private Button showissuedBooks;
    @FXML
    private TextField ISBN;
    @FXML
    private DatePicker issuedate;
    @FXML
    private DatePicker returndate;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void issueAction(MouseEvent event) throws SQLException {
        Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/library management","root","");
        Statement statement=conn.createStatement();
        
        String query= " SELECT * FROM bookinfo WHERE bookISBN LIKE '%"+ISBN.getText()+"%'";
        ResultSet rs=statement.executeQuery(query);
        
        if(rs.next()){
           String title= rs.getString("bookTitle");
           String isbn=rs.getString("bookISBN");
           String author=rs.getString("bookAuthor");
           String publisher =rs.getString("bookPublisher");
           int numcopies =rs.getInt("NumCopies");
           
           issuedBooks bookobj=new issuedBooks(title,isbn,author,publisher);
         // System.out.println("pawa gese");
           bookInfo bookinfo = new bookInfo(title,isbn,author,publisher,numcopies);
        
        //database action
        DatabaseAction dbAction=new DatabaseAction();
        dbAction.addissuedbooks(bookobj);
        DatabaseAction dbAction2=new DatabaseAction();
        dbAction2.decrementCopies(bookinfo);
        
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Book issued successfully :-)");
            
            alert.showAndWait();
        
       }
    }

    @FXML
    private void showissuedBooksAction(MouseEvent event) {
        try {

                    //add you loading or delays - ;-)
                    Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow();
                    //stage.setMaximized(true);
                    stage.close();
                    Scene scene = new Scene(FXMLLoader.load(getClass().getResource("showAllissuedBooks.fxml")));
                    stage.setScene(scene);
                    stage.show();

                } catch (IOException ex) {
                    System.err.println(ex.getMessage());
                }
    }

    @FXML
    private void BackAction(MouseEvent event) {
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

    
}