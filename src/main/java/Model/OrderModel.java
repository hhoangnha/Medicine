/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.sql.Date;

/**
 *
 * @author Nguyen Hoang Nha - CE170092
 */
public class OrderModel {
    private int OrderID;
    private String OrderDate;
    private String Username;
    private int OrderTotal;
    private int OrderStatus;
    private String OrderAddress;
    private String OrderPhone;
    private String OrderNote;
    public OrderModel() {
    }

    public OrderModel(int OrderID, String OrderDate, String Username, int OrderTotal, int OrderStatus, String OrderAddress, String OrderPhone, String OrderNote) {
        this.OrderID = OrderID;
        this.OrderDate = OrderDate;
        this.Username = Username;
        this.OrderTotal = OrderTotal;
        this.OrderStatus = OrderStatus;
        this.OrderAddress = OrderAddress;
        this.OrderPhone = OrderPhone;
        this.OrderNote = OrderNote;
    }

    

    public String getOrderAddress() {
        return OrderAddress;
    }

    public void setOrderAddress(String OrderAddress) {
        this.OrderAddress = OrderAddress;
    }

    public String getOrderPhone() {
        return OrderPhone;
    }

    public void setOrderPhone(String OrderPhone) {
        this.OrderPhone = OrderPhone;
    }

    public String getOrderNote() {
        return OrderNote;
    }

    public void setOrderNote(String OrderNote) {
        this.OrderNote = OrderNote;
    }

    public int getOrderID() {
        return OrderID;
    }

    public void setOrderID(int OrderID) {
        this.OrderID = OrderID;
    }

    public String getOrderDate() {
        return OrderDate;
    }

    public void setOrderDate(String OrderDate) {
        this.OrderDate = OrderDate;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String Username) {
        this.Username = Username;
    }

    public int getOrderTotal() {
        return OrderTotal;
    }

    public void setOrderTotal(int OrderTotal) {
        this.OrderTotal = OrderTotal;
    }

    public int getOrderStatus() {
        return OrderStatus;
    }

    public void setOrderStatus(int OrderStatus) {
        this.OrderStatus = OrderStatus;
    }
    
}
