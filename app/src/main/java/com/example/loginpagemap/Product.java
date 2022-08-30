package com.example.loginpagemap;

public class Product {
    private String RetailerName;
    private int RetailerID;
    private String Address1;
    private String ContactNumber;
    private String Latitude;
    private String Longitude;
    private String pincode;
    private String Phno1;

    public Product(String retailerName, int retailerID, String address1, String contactNumber, String latitude, String longitude, String pincode, String phno1) {
        this.RetailerName = retailerName;
        this.RetailerID = retailerID;
        this.Address1 = address1;
        this.ContactNumber = contactNumber;
        this.Latitude = latitude;
        this.Longitude = longitude;
        this.pincode = pincode;
        this.Phno1 = phno1;
    }

    public String getRetailerName() {
        return RetailerName;
    }

    public void setRetailerName(String retailerName) {
        RetailerName = retailerName;
    }

    public int getRetailerID() {
        return RetailerID;
    }

    public void setRetailerID(int retailerID) {
        RetailerID = retailerID;
    }

    public String getAddress1() {
        return Address1;
    }

    public void setAddress1(String address1) {
        Address1 = address1;
    }

    public String getContactNumber() {
        return ContactNumber;
    }

    public void setContactNumber(String contactNumber) {
        ContactNumber = contactNumber;
    }

    public String getLatitude() {
        return Latitude;
    }

    public void setLatitude(String latitude) {
        Latitude = latitude;
    }

    public String getLongitude() {
        return Longitude;
    }

    public void setLongitude(String longitude) {
        Longitude = longitude;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getPhno1() {
        return Phno1;
    }

    public void setPhno1(String phno1) {
        Phno1 = phno1;
    }
}

