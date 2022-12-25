package com.example.liftup.model;

/**
 * Holds the user data for a specific user. Things that would display on the profile page.
 */
public class User {
    private String username;
    private String fname;
    private String lname;
    private String email;
    private String password;
    private boolean trainer;
    private boolean admin;

    public User() {
    }

    public User(String username, String fname, String lname, String email, String password) {
        this.username = username;
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.password = password;
        this.trainer = false;
        this.admin = false;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setNames(String names)
    {
        String[] arr = names.split(" ");

        setFname(arr[0]);

        if(arr.length > 1)
        {
            setLname(arr[0]);
        } else {
            setLname("...");
        }
    }

    public String getNames(){
        return fname + " " + lname;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        if(fname.isEmpty())
        {
            return;
        }

        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        if(lname.isEmpty())
        {
            return;
        }

        this.lname = lname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if(email.isEmpty())
        {
            return;
        }

        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        if(password.isEmpty())
        {
            return;
        }

        this.password = password;
    }

    public boolean isTrainer() {
        return trainer;
    }

    public void setTrainer(boolean trainer) {
        this.trainer = trainer;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", fname='" + fname + '\'' +
                ", lname='" + lname + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
