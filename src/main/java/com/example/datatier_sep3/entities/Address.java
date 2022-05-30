package com.example.datatier_sep3.entities;

import java.io.Serializable;

public class Address implements Serializable {

    private String firstLine;
    private String secondLine;
    private String city;
    private int zipCode;


    public Address() {
    }

    public Address(String firstLine, String secondLine, String city, int zipCode) {
        this.firstLine = firstLine;
        this.secondLine = secondLine;
        this.city = city;
        this.zipCode = zipCode;

    }

    public String getFirstLine() {
        return firstLine;
    }

    public void setFirstLine(String firstLine) {
        this.firstLine = firstLine;
    }

    public String getSecondLine() {
        return secondLine;
    }

    public void setSecondLine(String secondLine) {
        this.secondLine = secondLine;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getZipCode() {
        return zipCode;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

    @Override
    public String toString() {
        return "Address{" +
                "firstLine='" + firstLine + '\'' +
                ", secondLine='" + secondLine + '\'' +
                ", city='" + city + '\'' +
                ", zipCode=" + zipCode +
                '}';
    }
}
