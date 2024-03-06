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
    private int UnitID;

    public OrderDetailModel() {
    }

    public OrderDetailModel(int OrderDetailID, int OrderID, int ProID, int Quantity, int OrderDetailStatus, int UnitID) {
        this.OrderDetailID = OrderDetailID;
        this.OrderID = OrderID;
        this.ProID = ProID;
        this.Quantity = Quantity;
        this.OrderDetailStatus = OrderDetailStatus;
        this.UnitID = UnitID;
    }

    public int getOrderDetailID() {
        return OrderDetailID;
    }

    public int getOrderID() {
        return OrderID;
    }

    public int getProID() {
        return ProID;
    }

    public int getQuantity() {
        return Quantity;
    }

    public int getOrderDetailStatus() {
        return OrderDetailStatus;
    }

    public int getUnitID() {
        return UnitID;
    }

    public void setOrderDetailID(int OrderDetailID) {
        this.OrderDetailID = OrderDetailID;
    }

    public void setOrderID(int OrderID) {
        this.OrderID = OrderID;
    }

    public void setProID(int ProID) {
        this.ProID = ProID;
    }

    public void setQuantity(int Quantity) {
        this.Quantity = Quantity;
    }

    public void setOrderDetailStatus(int OrderDetailStatus) {
        this.OrderDetailStatus = OrderDetailStatus;
    }

    public void setUnitID(int UnitID) {
        this.UnitID = UnitID;
    }
    
    
    



}
