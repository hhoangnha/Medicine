/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Nguyen Hoang Nha - CE170092
 */
public class BrandModel {

    private int BrandID;
    private String BrandName;
    private String origin;
    private int BrandStatus;

    public BrandModel() {
    }

    public BrandModel(int BrandID, String BrandName, String origin, int BrandStatus) {
        this.BrandID = BrandID;
        this.BrandName = BrandName;
        this.origin = origin;
        this.BrandStatus = BrandStatus;
    }

    public int getBrandStatus() {
        return BrandStatus;
    }

    public void setBrandStatus(int BrandStatus) {
        this.BrandStatus = BrandStatus;
    }

    
    public int getBrandID() {
        return BrandID;
    }

    public void setBrandID(int BrandID) {
        this.BrandID = BrandID;
    }

    public String getBrandName() {
        return BrandName;
    }

    public void setBrandName(String BrandName) {
        this.BrandName = BrandName;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    @Override
    public String toString() {
        return "BrandModel{" + "BrandID=" + BrandID + ", BrandName=" + BrandName + ", origin=" + origin + '}';
    }

  
}
