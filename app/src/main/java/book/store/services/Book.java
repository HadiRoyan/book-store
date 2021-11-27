package book.store.services;

import java.util.ArrayList;

public class Book {
    
    final private String ADMIN  = "admin"; 
    final private int idADMIN = 000101;
    
    private String bookName;
    private String author;
    private String publisher;
    private int quantity;

    boolean isValidUser;
    User user = new User(ADMIN);
    

    public Book(String bookName, String author, String publisher, int quantity){
        isValidUser = user.isValid(idADMIN);
        this.bookName = bookName;
        this.author = author;
        this.publisher = publisher;
        this.quantity = quantity;
    }   

    public ArrayList<Object> getBook(String user){
        if (!isValidUser) {
            return null;
        }

        ArrayList<Object> book = new ArrayList<>();
        book.add(getBookName());
        book.add(getAuthor());
        book.add(getPublisher());
        book.add(getQuantity());

        return book;
    }

    public void setBookName(String bookName){
        this.bookName = bookName;
    }

    public String getBookName(){
        return this.bookName;
    }

    public void setAuthor(String auhtor){
        this.author = auhtor;
    }

    public String getAuthor(){
        return this.author;
    }

    public void setPublisher(String publisher){
        this.publisher = publisher;
    }

    public String getPublisher(){
        return this.publisher;
    }

    public void setQuantity(int quantity){
        this.quantity = quantity;
    }

    public int getQuantity(){
        return this.quantity;
    }
}
