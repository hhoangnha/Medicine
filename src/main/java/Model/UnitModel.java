
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author macbook
 */
public class UnitModel {
    private int UnitID;
    private String UnitName;
    private int UnitStatus;
    public UnitModel() {
    }

    public UnitModel(int UnitID, String UnitName, int UnitStatus) {
        this.UnitID = UnitID;
        this.UnitName = UnitName;
        this.UnitStatus = UnitStatus;
    }

    public int getUnitStatus() {
        return UnitStatus;
    }

    public void setUnitStatus(int UnitStatus) {
        this.UnitStatus = UnitStatus;
    }

    

    public void setUnitID(int UnitID) {
        this.UnitID = UnitID;
    }

    public void setUnitName(String UnitName) {
        this.UnitName = UnitName;
    }

    public int getUnitID() {
        return UnitID;
    }

    public String getUnitName() {
        return UnitName;
    }

   
    
    
}

