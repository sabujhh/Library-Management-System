/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libraryassistant.signup;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
/**
 *
 * @author Hasan Sabuj
 */
public class DatabaseAction {
    Connection getConnection() throws SQLException{
        Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/library management","root","");
        return conn;
    }
    
    ObservableList<userInfo> getAlluserInfo() throws SQLException{
        
        ObservableList<userInfo> userInfoList=FXCollections.observableArrayList();
        
        Connection conn = getConnection();
        Statement statement=conn.createStatement();
        String query="select * from userinfo";
        
        ResultSet rs=statement.executeQuery(query);
        
        while(rs.next()){
            int id=rs.getInt("userID");
            String name=rs.getString("Name");
            String email=rs.getString("userEmail");
            String password=rs.getString("userPassword");
            String confirmpass=rs.getString("confirmPass");
            
            userInfo userinfo=new userInfo(id,name,email,password,confirmpass);
            userInfoList.add(userinfo);
        }
        return userInfoList;
        
    }
    String adduserInfo(userInfo userinfo) throws SQLException{
        Connection conn=getConnection();
        Statement statement=conn.createStatement();
        
        String query="insert into userinfo "
                + "values("+userinfo.getId()+",'"+userinfo.getName()+"','"+userinfo.getEmail()+"','"+userinfo.getPassword()+"','"+userinfo.getConfirmpass()+"')";
        
        if(statement.executeUpdate(query)>0){
            return "userinfo added successfully";
        }else{
            return "failed";
        }     
        
    }
    String updateuserInfo(userInfo userinfo) throws SQLException{
      Connection conn=getConnection();
        Statement statement=conn.createStatement();
        
        String query= "UPDATE userinfo "
+ "SET userPassword ='"+userinfo.getPassword()+"',confirmPass='"+userinfo.getConfirmpass()+"'"
+ "WHERE userEmail ='"+userinfo.getEmail()+"'" ;
        if(statement.executeUpdate(query)>0){
            return "userinfo added successfully";
        }else{
            return "failed";
        } 
    }
  
  ObservableList<adminInfo> getAlladminInfo() throws SQLException{
        
        ObservableList<adminInfo> adminInfoList=FXCollections.observableArrayList();
        
        Connection conn = getConnection();
        Statement statement=conn.createStatement();
        String query="select * from admininfo";
        
        ResultSet rs=statement.executeQuery(query);
        
        while(rs.next()){
            int id=rs.getInt("adminID");
            String email=rs.getString("adminEmail");
            String password=rs.getString("adminPassword");
            
            adminInfo admininfo=new adminInfo(id,email,password);
            adminInfoList.add(admininfo);
        }
        return adminInfoList;
        
    } 
  ObservableList<bookInfo> getAllbookInfo() throws SQLException{
        
        ObservableList<bookInfo> bookInfoList=FXCollections.observableArrayList();
        
        Connection conn = getConnection();
        Statement statement=conn.createStatement();
        String query="select * from bookinfo";
        
        ResultSet rs=statement.executeQuery(query);
        
        while(rs.next()){
            String title=rs.getString("bookTitle");
            String isbn=rs.getString("bookISBN");
            String author=rs.getString("bookAuthor");
            String publisher=rs.getString("bookPublisher");
            int numcopies =rs.getInt("NumCopies");
            bookInfo bookinfo=new bookInfo(title,isbn,author,publisher,numcopies);
            bookInfoList.add(bookinfo);
        }
        return bookInfoList;
        
    }
  
  String addbookInfo(bookInfo bookinfo) throws SQLException{
        Connection conn=getConnection();
        Statement statement=conn.createStatement();
        
        String query="insert into bookinfo "
                + "values('"+bookinfo.getTitle()+"','"+bookinfo.getIsbn()+"','"+bookinfo.getAuthor()+"','"+bookinfo.getPublisher()+"',"+bookinfo.getNumcopies()+")";
        
        if(statement.executeUpdate(query)>0){
            return "Bookinfo added successfully";
        }else{
            return "failed";
        }     
        
    }
  
  String deletebooks(ObservableList<bookInfo> selectedBooks) throws SQLException{
        Connection conn=getConnection();
        Statement statement=conn.createStatement();
        
        for(bookInfo bookinfo:selectedBooks){
            String query="delete from bookinfo WHERE bookISBN = '"+bookinfo.getIsbn()+"'";
            statement.executeUpdate(query);
            }
        return null;
        }
  
  String updatebookInfo(bookInfo bookinfo) throws SQLException{
      Connection conn=getConnection();
        Statement statement=conn.createStatement();
        
        String query= "UPDATE bookinfo "
+ "SET bookTitle ='"+bookinfo.getTitle()+"',bookAuthor='"+bookinfo.getAuthor()+"',bookPublisher='"+bookinfo.getPublisher()+"',NumCopies="+bookinfo.getNumcopies()
+ "WHERE bookISBN ='"+bookinfo.getIsbn()+"'";
        if(statement.executeUpdate(query)>0){
            return "bookinfo updated successfully";
        }else{
            return "failed";
        } 
    }
  
  String decrementCopies(bookInfo bookinfo) throws SQLException{
      Connection conn=getConnection();
        Statement statement=conn.createStatement();
        
        String query= "UPDATE bookinfo "
+ "SET NumCopies=("+bookinfo.getNumcopies()+"-1)"
+ "WHERE bookISBN ='"+bookinfo.getIsbn()+"'";
        if(statement.executeUpdate(query)>0){
            return "bookinfo updated successfully";
        }else{
            return "failed";
        } 
    }
  
  String incrementCopies(bookInfo bookinfo) throws SQLException{
      Connection conn=getConnection();
        Statement statement=conn.createStatement();
        
        String query= "UPDATE bookinfo "
+ "SET NumCopies=("+bookinfo.getNumcopies()+"+1)"
+ "WHERE bookISBN ='"+bookinfo.getIsbn()+"'";
        if(statement.executeUpdate(query)>0){
            return "bookinfo updated successfully";
        }else{
            return "failed";
        } 
    }
  
  String searchbookInfo(bookInfo bookinfo) throws SQLException{
      
      Connection conn=getConnection();
        Statement statement=conn.createStatement();
        
        String query= " SELECT * FROM bookinfo WHERE bookAuthor LIKE '"+bookinfo.getAuthor()+"%'";
        ResultSet rs=statement.executeQuery(query);
        
        if(rs.next()){
            System.out.println(rs.getString("bookAuthor"));
        }
        else{
            System.out.println("pawa jay nai");
        }
        return "failed";
  }
    ObservableList<issuedBooks> getAllissuedbooks() throws SQLException{
        
        ObservableList<issuedBooks> issuedbookList=FXCollections.observableArrayList();
        
        Connection conn = getConnection();
        Statement statement=conn.createStatement();
        String query="select * from issuedBooks";
        
        ResultSet rs=statement.executeQuery(query);
        
        while(rs.next()){
            String title=rs.getString("bookTitle");
            String isbn=rs.getString("bookISBN");
            String author=rs.getString("bookAuthor");
            String publisher=rs.getString("bookPublisher");
            
            issuedBooks bookinfo=new issuedBooks(title,isbn,author,publisher);
            issuedbookList.add(bookinfo);
        }
        return issuedbookList;
        
    }
    String addissuedbooks(issuedBooks bookobj) throws SQLException{
        Connection conn=getConnection();
        Statement statement=conn.createStatement();
        
        String query="insert into issuedBooks "
                + "values('"+bookobj.getTitle()+"','"+bookobj.getIsbn()+"','"+bookobj.getAuthor()+"','"+bookobj.getPublisher()+"')";
        
        if(statement.executeUpdate(query)>0){
            return "Bookinfo added successfully";
        }else{
            return "failed";
        }     
    }
    String removeissuedbooks(issuedBooks bookobj) throws SQLException{
        Connection conn=getConnection();
        Statement statement=conn.createStatement();
        
        String query="delete from issuedBooks WHERE bookISBN = '"+bookobj.getIsbn()+"'";
        
        if(statement.executeUpdate(query)>0){
            return "Bookinfo added successfully";
        }else{
            return "failed";
        }     
    }
}
