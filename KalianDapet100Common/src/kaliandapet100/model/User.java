package kaliandapet100.model;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import java.io.Serializable;
import java.time.LocalDate;

/**
 *
 * @author ACER
 */
public class User implements Serializable{
    private String fullname;
    String username;
    String password;
    private String email;
    private LocalDate dob;
    private LocalDate member_since;
    
    public User(String fullname, String username, String password, String email, LocalDate dob, LocalDate member_since ) {
        this.fullname = fullname;
        this.username = username;
        this.password = password;
        this.email = email;
        this.dob = dob;
        this.member_since = member_since;
    }

    public User() {
        this.fullname = "";
        this.username = "";
        this.password = "";
        this.email = "";
        this.dob = LocalDate.now();
        this.member_since = LocalDate.now();
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public LocalDate getMember_since() {
        return member_since;
    }

    public void setMember_since(LocalDate member_since) {
        this.member_since = member_since;
    }
    
}
