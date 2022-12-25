package liftup.user.model;

import javax.persistence.*;

@Entity
public class User {
    @Id
    private String username;
    private String fname;
    private String lname;
    private String email;
    private String password;
    private boolean trainer;
    private boolean admin;

    public User() {
    }

    public User(String username, String fname, String lname, String email, String password, boolean trainer, boolean admin) {
        this.username = username;
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.password = password;
        this.trainer = trainer;
        this.admin = admin;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getFname() {
        return fname;
    }
    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }
    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isTrainer() {
        return trainer;
    }
    public boolean isAdmin() {
        return admin;
    }

    public void swapTrainerStatus() {
        trainer = !trainer;
    }
    public void acceptTrainerApp() {
        trainer = true;
    }

    public void swapAdminStatus() {
        admin = !admin;
    }
}
