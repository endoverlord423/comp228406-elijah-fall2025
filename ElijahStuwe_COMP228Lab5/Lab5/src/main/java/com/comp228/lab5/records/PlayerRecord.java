package com.comp228.lab5.records;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class PlayerRecord {
    private final StringProperty firstName = new SimpleStringProperty();
    private final StringProperty lastName = new SimpleStringProperty();
    private final StringProperty address = new SimpleStringProperty();
    private final StringProperty postalCode = new SimpleStringProperty();
    private final StringProperty province = new SimpleStringProperty();
    private final IntegerProperty phoneNumber = new SimpleIntegerProperty();

    public PlayerRecord(String firstName, String lastName, String address, String postalCode, String province, int phoneNumber) {
        this.firstName.set(firstName);
        this.lastName.set(lastName);
        this.address.set(address);
        this.postalCode.set(postalCode);
        this.province.set(province);
        this.phoneNumber.set(phoneNumber);
    }

    public StringProperty firstNameProperty() { return firstName; }
    public StringProperty lastNameProperty() { return lastName; }
    public StringProperty addressProperty() { return address; }
    public StringProperty postalCodeProperty() { return postalCode; }
    public StringProperty provinceProperty() { return province; }
    public IntegerProperty phoneNumberProperty() { return phoneNumber; }

    public String getFirstName() { return firstName.get(); }
    public String getLastName() { return lastName.get(); }
    public String getAddress() { return address.get(); }
    public String getPostalCode() { return postalCode.get(); }
    public String getProvince() { return province.get(); }
    public int getPhoneNumber() { return phoneNumber.get(); }
}