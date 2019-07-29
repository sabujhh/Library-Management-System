/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libraryassistant.signup;

/**
 *
 * @author Hasan Sabuj
 */
public class bookInfo {

    
    
    private String title;
    private String isbn;
    private String author;
    private String publisher;
    public int numcopies;

    public bookInfo() {
    }
    
    public bookInfo(String author) {
        this.author = author;
    }

    public bookInfo(String title, String isbn, String author, String publisher, int numcopies) {
        this.title = title;
        this.isbn = isbn;
        this.author = author;
        this.publisher = publisher;
        this.numcopies = numcopies;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }
    public int getNumcopies() {
        return numcopies;
    }

    public void setNumcopies(int numcopies) {
        this.numcopies = numcopies;
    }
    
    @Override
    public String toString() {
        return "bookInfo{" + "title=" + title + ", isbn=" + isbn+ ", author=" + author + ", publisher="+publisher+", numcopies="+numcopies+'}';
    }
    
}
