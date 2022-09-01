package com.example.smartgym.dao;

public class GymLocation {
    private final String province;
    private final String city;
    private final String postCode;
    private final String street;
    private final int streetNumber;

    public GymLocation(String province, String city, String postCode, String street,
                       int streetNumber) {
        this.province = province;
        this.city = city;
        this.postCode = postCode;
        this.street = street;
        this.streetNumber = streetNumber;
    }

    public String getProvince() {
        return province;
    }

    public String getCity() {
        return city;
    }

    public String getPostCode() {
        return postCode;
    }

    public String getStreet() {
        return street;
    }

    public int getStreetNumber() {
        return streetNumber;
    }

    @Override
    public String toString() {
        return "GymLocation{" +
                "province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", postCode='" + postCode + '\'' +
                ", street='" + street + '\'' +
                ", streetNumber=" + streetNumber +
                '}';
    }
}
