/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Thai Vinh
 */
public class ManufacturerModel {

    int ManuID;
    String ManuName;
    String MfgLicense;
    String ManuAddress;
    String Phone;

    public ManufacturerModel() {
    }

    public ManufacturerModel(int ManuID, String ManuName, String MfgLicense, String ManuAddress, String Phone) {
        this.ManuID = ManuID;
        this.ManuName = ManuName;
        this.MfgLicense = MfgLicense;
        this.ManuAddress = ManuAddress;
        this.Phone = Phone;
    }

    public ManufacturerModel(String ManuName, String MfgLicense, String ManuAddress, String Phone) {
        this.ManuName = ManuName;
        this.MfgLicense = MfgLicense;
        this.ManuAddress = ManuAddress;
        this.Phone = Phone;
    }

    public int getManuID() {
        return ManuID;
    }

    public void setManuID(int ManuID) {
        this.ManuID = ManuID;
    }

    public String getManuName() {
        return ManuName;
    }

    public void setManuName(String ManuName) {
        this.ManuName = ManuName;
    }

    public String getMfgLicense() {
        return MfgLicense;
    }

    public void setMfgLicense(String MfgLicense) {
        this.MfgLicense = MfgLicense;
    }

    public String getManuAddress() {
        return ManuAddress;
    }

    public void setManuAddress(String ManuAddress) {
        this.ManuAddress = ManuAddress;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String Phone) {
        this.Phone = Phone;
    }

//    @Override
//    public String toString() {
//        return "ManufacturerModel{" + "ManuID=" + ManuID + ", ManuName=" + ManuName + ", MfgLicense=" + MfgLicense + ", ManuAddress=" + ManuAddress + ", Phone=" + Phone + '}';
//    }
    @Override
    public String toString() {
        return ManuID+"-"+ManuName;
    }
}
