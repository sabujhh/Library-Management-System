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
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import libraryassistant.signup.bookInfo;

/**
 * FXML Controller class
 *
 * @author Hasan Sabuj
 */
public class ShowallBooksController implements Initializable {

    @FXML
    private TableColumn<bookInfo, String> titleColumn;
    @FXML
    private TableColumn<bookInfo, String> isbnColumn;
    @FXML
    private TableColumn<bookInfo, String> authorColumn;
    @FXML
    private TableColumn<bookInfo, String> publisherColumn;
    @FXML
    private TableColumn<bookInfo, Integer> numcopies;
    @FXML
    private TableView<bookInfo> bookInfoTable;
    @FXML
    private TextField titleEditField;
    @FXML
    private TextField AuthorEditField;
    @FXML
    private TextField PublisherEditField;

    /**
     * Initializes the controller class.
     */
    static ObservableList<bookInfo> bookInfoList=FXCollections.observableArrayList();
    
    @FXML
    private TextField numofCopies;
    @FXML
    private TextField ISBNEditField;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        bookInfoTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        DatabaseAction dbAction=new DatabaseAction();
        try {
            bookInfoList= dbAction.getAllbookInfo();
        } catch (SQLException ex) {
            Logger.getLogger(ShowallBooksController.class.getName()).log(Level.SEVERE, null, ex);
        }
     
        titleColumn.setCellValueFactory(new PropertyValueFactory<bookInfo, String>("title"));    
        isbnColumn.setCellValueFactory(new PropertyValueFactory<bookInfo,String>("Isbn"));
        authorColumn.setCellValueFactory(new PropertyValueFactory<bookInfo,String>("author"));
        publisherColumn.setCellValueFactory(new PropertyValueFactory<bookInfo,String>("publisher"));
        numcopies.setCellValueFactory(new PropertyValueFactory<bookInfo,Integer>("numcopies"));
            
        bookInfoTable.setItems(bookInfoList);
    } 

    
    @FXML
    private void updateButtonAction(MouseEvent event) throws SQLException {
        if(titleEditField.getText().equals("") || ISBNEditField.getText().equals("") || AuthorEditField.getText().equals("") || 
           PublisherEditField.getText().equals("") ) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please give all info");
            alert.showAndWait();
            return;
        }
        
        String newTitle = titleEditField.getText();
        String bookisbn=ISBNEditField.getText();
        String newAuthor = AuthorEditField.getText();
        String newPublisher = PublisherEditField.getText();
        int numofcopies=Integer.parseInt(numofCopies.getText());
        
        bookInfo bookinfoobj=new bookInfo(newTitle, bookisbn, newAuthor, newPublisher,numofcopies);
        
        
        
        //database action
        DatabaseAction dbAction=new DatabaseAction();
        dbAction.updatebookInfo(bookinfoobj);
        
            
        
        
        titleEditField.clear();
        ISBNEditField.clear();
        AuthorEditField.clear();
        PublisherEditField.clear();
        
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Book info Updated Successfully");
            alert.showAndWait();
        
        
    }
    
    
    @FXML
    private void deleteButtonAction(MouseEvent event) throws SQLException {
        ObservableList<bookInfo> selectedBooks=FXCollections.observableArrayList();
        selectedBooks=bookInfoTable.getSelectionModel().getSelectedItems();
        AddbookInfoController.bookInfoList.removeAll(selectedBooks);
        DatabaseAction dbAction=new DatabaseAction();
        dbAction.deletebooks(selectedBooks);
        }

    

    
}
