/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Nguyen Hoang Nha - CE170092
 */
public class CartItem {
    private int CartID;
    private int CustomerID;
    private int ProId;
    private int Quantity;
    private int UnitID;

    public CartItem() {
    }

    public CartItem(int CartID, int CustomerID, int ProId, int Quantity, int UnitID) {
        this.CartID = CartID;
        this.CustomerID = CustomerID;
        this.ProId = ProId;
        this.Quantity = Quantity;
        this.UnitID = UnitID;
    }

    public int getCartID() {
        return CartID;
    }

    public int getCustomerID() {
        return CustomerID;
    }

    public int getProId() {
        return ProId;
    }

    public int getQuantity() {
        return Quantity;
    }

    public int getUnitID() {
        return UnitID;
    }

    public void setUnitID(int UnitProductID) {
        this.UnitID = UnitProductID;
    }
    
    
    

   
    
    public void subCartQuantity(){
        if(this.Quantity >1){
            this.Quantity = Quantity -1;
        }   
    }

    public void setCartID(int CartID) {
        this.CartID = CartID;
    }

    public void setCustomerID(int CustomerID) {
        this.CustomerID = CustomerID;
    }

    public void setProId(int ProId) {
        this.ProId = ProId;
    }

    public void setQuantity(int Quantity) {
        this.Quantity = Quantity;
    }
    public void addCartQuantity(){
        this.Quantity = Quantity +1;
    }
}
