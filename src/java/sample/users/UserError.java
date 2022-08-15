/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sample.users;

/**
 *
 * @author ASUS
 */
public class UserError {
    private String userID;
    private String fullName;
    private String passwprd;
    private String confirm;
    private String address;
    private String phone;
    private String enail;

    public UserError() {
        this.userID = "";
        this.fullName = "";
        this.passwprd = "";
        this.confirm = "";
        this.address = "";
        this.phone = "";
        this.enail = "";
    }

    public UserError(String userID, String fullName, String passwprd, String confirm, String address, String phone, String enail) {
        this.userID = userID;
        this.fullName = fullName;
        this.passwprd = passwprd;
        this.confirm = confirm;
        this.address = address;
        this.phone = phone;
        this.enail = enail;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPasswprd() {
        return passwprd;
    }

    public void setPasswprd(String passwprd) {
        this.passwprd = passwprd;
    }

    public String getConfirm() {
        return confirm;
    }

    public void setConfirm(String confirm) {
        this.confirm = confirm;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEnail() {
        return enail;
    }

    public void setEnail(String enail) {
        this.enail = enail;
    }
    


     
}
