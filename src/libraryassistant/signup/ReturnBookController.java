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
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Hasan Sabuj
 */
public class ReturnBookController implements Initializable {

    @FXML
    private TextField isbn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void returnAction(MouseEvent event) throws SQLException{
        Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/library management","root","");
        Statement statement=conn.createStatement();
        
        String query= " SELECT * FROM bookinfo WHERE bookISBN LIKE '%"+isbn.getText()+"%'";
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
           
           DatabaseAction dbAction=new DatabaseAction();
        dbAction.removeissuedbooks(bookobj);
        DatabaseAction dbAction3=new DatabaseAction();
        dbAction3.incrementCopies(bookinfo);
        
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Book returned successfully :-)");
            
            alert.showAndWait();
    
     }
    }

    @FXML
    private void backAction(MouseEvent event) {
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
