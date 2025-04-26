package kaliandapet100.model;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import java.time.LocalDate;

/**
 *
 * @author 4a25
 */
public class Purchase {
    private String username;
    private String ticketID;
    private int quantity;
    private int subTotal;
    private LocalDate createdDate;
    private LocalDate updatedDate;

    public Purchase(String username, String ticketID, int quantity, int subTotal, LocalDate createdDate, LocalDate updatedDate) {
        this.username = username;
        this.ticketID = ticketID;
        this.quantity = quantity;
        this.subTotal = subTotal;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTicketID() {
        return ticketID;
    }

    public void setTicketID(String ticketID) {
        this.ticketID = ticketID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(int subTotal) {
        this.subTotal = subTotal;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDate getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(LocalDate updatedDate) {
        this.updatedDate = updatedDate;
    }
    
    
}
