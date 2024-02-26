/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Nguyen Hoang Nha - CE170092
 */
public class OrderDetailModel {

    private int OrderDetailID;
    private int OrderID;
    private int ProID;
    private int Quantity;
    private int OrderDetailStatus;

    public int getOrderDetailStatus() {
        return OrderDetailStatus;
    }

    public void setOrderDetailStatus(int OrderDetailStatus) {
        this.OrderDetailStatus = OrderDetailStatus;
    }

    public OrderDetailModel() {
    }

    public OrderDetailModel(int OrderDetailID, int OrderID, int ProID, int Quantity) {
        this.OrderDetailID = OrderDetailID;
        this.OrderID = OrderID;
        this.ProID = ProID;
        this.Quantity = Quantity;
    }

    public int getOrderDetailID() {
        return OrderDetailID;
    }

    public void setOrderDetailID(int OrderDetailID) {
        this.OrderDetailID = OrderDetailID;
    }

    public int getOrderID() {
        return OrderID;
    }

    public void setOrderID(int OrderID) {
        this.OrderID = OrderID;
    }

    public int getProID() {
        return ProID;
    }

    public void setProID(int ProID) {
        this.ProID = ProID;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int Quantity) {
        this.Quantity = Quantity;
    }

}
