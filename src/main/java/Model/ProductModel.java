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
public class ProductModel {

    private int ProID;
    private String ProCode;
    private String ProName;
    private String ProDescription;
    private int CateID;
    private int BrandID;
    private int ManuID;
    private String ManufactureDate;
    private String ExpirationDate;
    private String Element;
    private int Quantity;
    private String Indication;
    private String Contraindication;
    private String Using;
    private String MadeIn;
    private String ProImage;

    public ProductModel() {
    }

    public ProductModel(int ProID, String ProCode, String ProName, String ProDescription, int CateID, int BrandID, int ManuID, String ManufactureDate, String ExpirationDate, String Element, int Quantity, String Indication, String Contraindication, String Using, String MadeIn, String ProImage) {
        this.ProID = ProID;
        this.ProCode = ProCode;
        this.ProName = ProName;
        this.ProDescription = ProDescription;
        this.CateID = CateID;
        this.BrandID = BrandID;
        this.ManuID = ManuID;
        this.ManufactureDate = ManufactureDate;
        this.ExpirationDate = ExpirationDate;
        this.Element = Element;
        this.Quantity = Quantity;
        this.Indication = Indication;
        this.Contraindication = Contraindication;
        this.Using = Using;
        this.MadeIn = MadeIn;
        this.ProImage = ProImage;
    }
    
    public int getProID() {
        return ProID;
    }
    
    public String getProCode() {
        return ProCode;
    }
    
    public String getProName() {
        return ProName;
    }

    public String getProDescription() {
        return ProDescription;
    }

    public int getCateID() {
        return CateID;
    }

    public int getBrandID() {
        return BrandID;
    }

    public int getManuID() {
        return ManuID;
    }

    public String getManufactureDate() {
        return ManufactureDate;
    }

    public String getExpirationDate() {
        return ExpirationDate;
    }

    public String getElement() {
        return Element;
    }

    public int getQuantity() {
        return Quantity;
    }

    public String getIndication() {
        return Indication;
    }

    public String getContraindication() {
        return Contraindication;
    }

    public String getUsing() {
        return Using;
    }

    public String getMadeIn() {
        return MadeIn;
    }

    public String getProImage() {
        return ProImage;
    }

    public void setProID(int ProID) {
        this.ProID = ProID;
    }
    
    public void setProCode(String ProCode) {
        this.ProCode = ProCode;
    }

    public void setProName(String ProName) {
        this.ProName = ProName;
    }

    public void setProDescription(String ProDescription) {
        this.ProDescription = ProDescription;
    }

    public void setCateID(int CateID) {
        this.CateID = CateID;
    }

    public void setBrandID(int BrandID) {
        this.BrandID = BrandID;
    }

    public void setManuID(int ManuID) {
        this.ManuID = ManuID;
    }

    public void setManufactureDate(String ManufactureDate) {
        this.ManufactureDate = ManufactureDate;
    }

    public void setExpirationDate(String ExpirationDate) {
        this.ExpirationDate = ExpirationDate;
    }

    public void setElement(String Element) {
        this.Element = Element;
    }

    public void setQuantity(int Quantity) {
        this.Quantity = Quantity;
    }

    public void setIndication(String Indication) {
        this.Indication = Indication;
    }

    public void setContraindication(String Contraindication) {
        this.Contraindication = Contraindication;
    }

    public void setUsing(String Using) {
        this.Using = Using;
    }

    public void setMadeIn(String MadeIn) {
        this.MadeIn = MadeIn;
    }

    public void setProImage(String ProImage) {
        this.ProImage = ProImage;
    }

    
}