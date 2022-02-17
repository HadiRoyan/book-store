package book.store.entity;

import java.util.Arrays;
import java.util.Objects;

public class User {

    private String id;
    private String name;
    private String email;
    private String username;
    private String hashPassword;
    private String salt;

    private char[] password;
    
    public User() {

    }

    public User(String name, String email, String username, char[] password) {
        this.name = name;
        this.email = email;
        this.username = username;
        this.password = password;
    }
    
    

    public User(String username, char[] password) {
        this.username = username;
        this.password = password;
    }

    public char[] getPassword() {
        return password;
    }

    public void setPassword(char[] password) {
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHashPassword() {
        return hashPassword;
    }

    public void setHashPassword(String hashPassword) {
        this.hashPassword = hashPassword;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.id);
        hash = 29 * hash + Objects.hashCode(this.name);
        hash = 29 * hash + Objects.hashCode(this.email);
        hash = 29 * hash + Objects.hashCode(this.username);
        hash = 29 * hash + Objects.hashCode(this.hashPassword);
        hash = 29 * hash + Objects.hashCode(this.salt);
        hash = 29 * hash + Arrays.hashCode(this.password);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final User other = (User) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        if (!Objects.equals(this.username, other.username)) {
            return false;
        }
        if (!Objects.equals(this.hashPassword, other.hashPassword)) {
            return false;
        }
        if (!Objects.equals(this.salt, other.salt)) {
            return false;
        }
        return Arrays.equals(this.password, other.password);
    }

    
    

    @Override
    public String toString() {
        return "User{" + "id=" + id + 
                ", name=" + name + 
                ", username=" + username + 
                ", email=" + email + 
                ", hashPassword=" + hashPassword + 
                ", salt=" + salt + '}';
    }
    
    
}
