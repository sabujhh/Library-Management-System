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
public class issuedBooks {
    private String title;
    private String isbn;
    private String author;
    private String publisher;
    private String datepicker;

    public issuedBooks() {
    }

    public issuedBooks(String title, String isbn, String author, String publisher) {
        this.title = title;
        this.isbn = isbn;
        this.author = author;
        this.publisher = publisher;
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

    @Override
    public String toString() {
        return "issuedBooks{" + "title=" + title + ", isbn=" + isbn + ", author=" + author + ", publisher=" + publisher + '}';
    }
    
    
}
