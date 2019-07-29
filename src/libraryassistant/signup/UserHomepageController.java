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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import static libraryassistant.signup.ShowallBooksController.bookInfoList;

/**
 * FXML Controller class
 *
 * @author Hasan Sabuj
 */
public class UserHomepageController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private TableView<bookInfo> bookInfoTable;
    @FXML
    private TableColumn<bookInfo, String> titleColumn;
    @FXML
    private TableColumn<bookInfo, String> IDColumn;
    @FXML
    private TableColumn<bookInfo, String> AuthorColumn;
    @FXML
    private TableColumn<bookInfo, String> PublisherColumn;
    @FXML
    private TableColumn<bookInfo, Integer> numCopies;
    
    
    @FXML
    private TextField searchField;
    @FXML
    private RadioButton rb1ID;
    @FXML
    private ToggleGroup Mygroup;
    @FXML
    private RadioButton rb2Author;
    @FXML
    private Button searchButton;
    
    static ObservableList<bookInfo> bookInfoList=FXCollections.observableArrayList();
    static ObservableList<bookInfo> bookInfoListtwo=FXCollections.observableArrayList();
    @FXML
    private Button refreshButton;
    @FXML
    private Button IssueBooks;
    
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
        IDColumn.setCellValueFactory(new PropertyValueFactory<bookInfo,String>("Isbn"));
        AuthorColumn.setCellValueFactory(new PropertyValueFactory<bookInfo,String>("author"));
        PublisherColumn.setCellValueFactory(new PropertyValueFactory<bookInfo,String>("publisher"));
        numCopies.setCellValueFactory(new PropertyValueFactory<bookInfo,Integer>("numcopies"));
        
        bookInfoTable.setItems(bookInfoList);
    }    
    @FXML
    private void searchAction(MouseEvent event) throws SQLException {
        
        bookInfoListtwo.clear();

        Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/library management","root","");
        Statement statement=conn.createStatement();
        if(rb2Author.isSelected()){
        String query= " SELECT * FROM bookinfo WHERE bookAuthor LIKE '%"+searchField.getText()+"%'";
        ResultSet rs=statement.executeQuery(query);
        
        if(rs.next()){
           String title= rs.getString("bookTitle");
           String isbn=rs.getString("bookISBN");
           String author=rs.getString("bookAuthor");
           String publisher =rs.getString("bookPublisher");
           int numcopies =rs.getInt("NumCopies");
           
           bookInfo bookinfo=new bookInfo(title,isbn,author,publisher,numcopies);
            bookInfoListtwo.add(bookinfo);
            
            titleColumn.setCellValueFactory(new PropertyValueFactory<bookInfo, String>("title"));    
        IDColumn.setCellValueFactory(new PropertyValueFactory<bookInfo,String>("isbn"));
        AuthorColumn.setCellValueFactory(new PropertyValueFactory<bookInfo,String>("author"));
        PublisherColumn.setCellValueFactory(new PropertyValueFactory<bookInfo,String>("publisher"));
        numCopies.setCellValueFactory(new PropertyValueFactory<bookInfo,Integer>("numcopies"));
            
        bookInfoTable.setItems(bookInfoListtwo);
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Book is not Available");
            alert.showAndWait();
                }
        }
        if(rb1ID.isSelected()){
        String query= " SELECT * FROM bookinfo WHERE bookTitle LIKE '%"+searchField.getText()+"%'";
        ResultSet rs=statement.executeQuery(query);
        
        if(rs.next()){
           String title= rs.getString("bookTitle");
           String isbn=rs.getString("bookIsbn");
           String author=rs.getString("bookAuthor");
           String publisher =rs.getString("bookPublisher");
           int numcopies =rs.getInt("NumCopies");
           
           bookInfo bookinfo=new bookInfo(title,isbn,author,publisher,numcopies);
            bookInfoListtwo.add(bookinfo);
            
            titleColumn.setCellValueFactory(new PropertyValueFactory<bookInfo, String>("title"));    
        IDColumn.setCellValueFactory(new PropertyValueFactory<bookInfo,String>("Isbn"));
        AuthorColumn.setCellValueFactory(new PropertyValueFactory<bookInfo,String>("author"));
        PublisherColumn.setCellValueFactory(new PropertyValueFactory<bookInfo,String>("publisher"));
        numCopies.setCellValueFactory(new PropertyValueFactory<bookInfo,Integer>("numcopies"));
        
        bookInfoTable.setItems(bookInfoListtwo);
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Book is not Available");
            alert.showAndWait();
                }
        }
    }
    

    @FXML
    private void radioSelect(ActionEvent event) {
        if(rb2Author.isSelected()){
        
        }
    }

    @FXML
    private void refreshAction(MouseEvent event) {
        bookInfoTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        DatabaseAction dbAction=new DatabaseAction();
        try {
            bookInfoList= dbAction.getAllbookInfo();
        } catch (SQLException ex) {
            Logger.getLogger(ShowallBooksController.class.getName()).log(Level.SEVERE, null, ex);
        }
     
        titleColumn.setCellValueFactory(new PropertyValueFactory<bookInfo, String>("title"));    
        IDColumn.setCellValueFactory(new PropertyValueFactory<bookInfo,String>("Isbn"));
        AuthorColumn.setCellValueFactory(new PropertyValueFactory<bookInfo,String>("author"));
        PublisherColumn.setCellValueFactory(new PropertyValueFactory<bookInfo,String>("publisher"));
        numCopies.setCellValueFactory(new PropertyValueFactory<bookInfo,Integer>("numcopies"));
            
        bookInfoTable.setItems(bookInfoList);
        
    }

    @FXML
    private void issuebooksAction(MouseEvent event) {
       /*bookInfoTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        DatabaseAction dbAction=new DatabaseAction();
        try {
            issuedBookList= dbAction.issuebooks();
        } catch (SQLException ex) {
            Logger.getLogger(ShowallBooksController.class.getName()).log(Level.SEVERE, null, ex);
        }
        titleColumn.setCellValueFactory(new PropertyValueFactory<bookInfo, String>("title"));    
        IDColumn.setCellValueFactory(new PropertyValueFactory<bookInfo,String>("Isbn"));
        AuthorColumn.setCellValueFactory(new PropertyValueFactory<bookInfo,String>("author"));
        PublisherColumn.setCellValueFactory(new PropertyValueFactory<bookInfo,String>("publisher"));
        numCopies.setCellValueFactory(new PropertyValueFactory<bookInfo,Integer>("numcopies"));
        bookInfoTable.setItems(issuedBookList);
        
       bookInfoListtwo.clear();

        Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/library management","root","");
        Statement statement=conn.createStatement();
        
        String query= " SELECT * FROM bookinfo WHERE bookISBN LIKE '%"+isbnIssue.getText()+"%'";
        ResultSet rs=statement.executeQuery(query);
        
        if(rs.next()){
           String title= rs.getString("bookTitle");
           String isbn=rs.getString("bookISBN");
           String author=rs.getString("bookAuthor");
           String publisher =rs.getString("bookPublisher");
           int numcopies =rs.getInt("NumCopies");
           
           bookInfo bookinfo=new bookInfo(title,isbn,author,publisher,numcopies);
            bookInfoListtwo.add(bookinfo);
            
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Book issued succesfully");
            alert.showAndWait();
        }*/
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
    private void showissuedAction(MouseEvent event) {
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
    private void logoutAction(MouseEvent event) {
        try {

                    //add you loading or delays - ;-)
                    Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow();
                    //stage.setMaximized(true);
                    stage.close();
                    Scene scene = new Scene(FXMLLoader.load(getClass().getResource("firstpage.fxml")));
                    stage.setScene(scene);
                    stage.show();

                } catch (IOException ex) {
                    System.err.println(ex.getMessage());
                }
    }

    @FXML
    private void returnBookAction(MouseEvent event) {
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
    
