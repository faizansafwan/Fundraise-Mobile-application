package com.faizan.charityhand;

public class Information {

    private String Name;
    private String Address;
    private String Phone;

    public Information() {
    }
    public Information(String Address, String Name, String Phone) {
        this.Name = Name;
        this.Address = Address;
        this.Phone = Phone;
    }

    public String getPhone_no() {
        return Phone;
    }

    public void setPhone_no(String Phone) {
        this.Phone = Phone;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }
}
