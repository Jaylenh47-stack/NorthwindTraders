package com.pluralsight;

public class Customer {
    private String contactName;
    private String companyName;
    private String city;
    private String country;
    private String phone;

    public Customer(String contactName, String companyName, String city, String country, String phoneNumber) {
        this.contactName = contactName;
        this.companyName = companyName;
        this.city = city;
        this.country = country;
        this.phone = phoneNumber;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPhoneNumber() {
        return phone;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phone = phoneNumber;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "contactName='" + contactName + '\'' +
                ", companyName='" + companyName + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
