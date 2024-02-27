/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author macbook
 */
public class UnitProductModel {
    private int UnitProductID;
    private int UnitID;
    private int ProID;
    private double Price;

    public UnitProductModel() {
    }

    public UnitProductModel(int UnitProductID, int UnitID, int ProID, double Price) {
        this.UnitProductID = UnitProductID;
        this.UnitID = UnitID;
        this.ProID = ProID;
        this.Price = Price;
    }

    public int getUnitProductID() {
        return UnitProductID;
    }

    public int getUnitID() {
        return UnitID;
    }

    public int getProID() {
        return ProID;
    }

    public double getPrice() {
        return Price;
    }

    public void setUnitProductID(int UnitProductID) {
        this.UnitProductID = UnitProductID;
    }

    public void setUnitID(int UnitID) {
        this.UnitID = UnitID;
    }

    public void setProID(int ProID) {
        this.ProID = ProID;
    }

    public void setPrice(double Price) {
        this.Price = Price;
    }
    
    
}
