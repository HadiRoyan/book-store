package book.store.services;

public class User {
    
    private String name;
    private int id;
    
    public User (String name) {
        this.name = name;
        this.id = 0;
    }   

    public String getUserName() {
        return this.name;
    }

    public int getUserId() {
        return this.id;
    }

    public boolean isValid(int idUser) {
        return false;
    }
    
}
