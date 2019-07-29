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
import javafx.scene.Scene;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import static libraryassistant.signup.UserHomepageController.bookInfoList;

/**
 * FXML Controller class
 *
 * @author Hasan Sabuj
 */
public class ShowAllissuedBooksController implements Initializable {

    @FXML
    private TableColumn<issuedBooks, String> titleColumn;
    @FXML
    private TableColumn<issuedBooks, String> ISBNColumn;
    @FXML
    private TableColumn<issuedBooks, String> authorColumn;
    @FXML
    private TableColumn<issuedBooks, String> publisherColumn;
    @FXML
    private TableView<issuedBooks> issuedBookstable;

    /**
     * Initializes the controller class.
     */
    ObservableList<issuedBooks> issuedBookList=FXCollections.observableArrayList();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        issuedBookstable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        DatabaseAction dbAction=new DatabaseAction();
        try {
            issuedBookList= dbAction.getAllissuedbooks();
        } catch (SQLException ex) {
            Logger.getLogger(ShowallBooksController.class.getName()).log(Level.SEVERE, null, ex);
        }
     
        titleColumn.setCellValueFactory(new PropertyValueFactory<issuedBooks, String>("title"));    
        ISBNColumn.setCellValueFactory(new PropertyValueFactory<issuedBooks,String>("Isbn"));
        authorColumn.setCellValueFactory(new PropertyValueFactory<issuedBooks,String>("author"));
        publisherColumn.setCellValueFactory(new PropertyValueFactory<issuedBooks,String>("publisher"));
        
        
        issuedBookstable.setItems(issuedBookList);
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


    @FXML
    private void issueBookAction(ActionEvent event) {
        try {

                    //add you loading or delays - ;-)
                    Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow();
                    //stage.setMaximized(true);
                    stage.close();
                    Scene scene = new Scene(FXMLLoader.load(getClass().getResource("issuebook.fxml")));
                    stage.setScene(scene);
                    stage.show();

                } catch (IOException ex) {
                    System.err.println(ex.getMessage());
                }
    }

    @FXML
    private void returnbookAction(MouseEvent event) {
        try {

                    //add you loading or delays - ;-)
                    Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow();
                    //stage.setMaximized(true);
                    stage.close();
                    Scene scene = new Scene(FXMLLoader.load(getClass().getResource("returnBook.fxml")));
                    stage.setScene(scene);
                    stage.show();

                } catch (IOException ex) {
                    System.err.println(ex.getMessage());
                }
    }
    
}
