package com.example.hellofx;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class StudentRecord {
    private final StringProperty firstName = new SimpleStringProperty();
    private final StringProperty lastName = new SimpleStringProperty();
    private final IntegerProperty age = new SimpleIntegerProperty();
    private final StringProperty favoriteColor = new SimpleStringProperty();
    private final StringProperty collegeProgram = new SimpleStringProperty();

    public StudentRecord(String firstName, String lastName, int age, String favoriteColor, String collegeProgram) {
        this.firstName.set(firstName);
        this.lastName.set(lastName);
        this.age.set(age);
        this.favoriteColor.set(favoriteColor);
        this.collegeProgram.set(collegeProgram);
    }

    public StringProperty firstNameProperty() { return firstName; }
    public StringProperty lastNameProperty() { return lastName; }
    public IntegerProperty ageProperty() { return age; }
    public StringProperty favoriteColorProperty() { return favoriteColor; }
    public StringProperty collegeProgramProperty() { return collegeProgram; }

    public String getFirstName() {
        return firstName.get();
    }
    public String getLastName() {
        return lastName.get();
    }
    public int getAge() {
        return age.get();
    }
    public String getFavoriteColor() {
        return favoriteColor.get();
    }
    public String getCollegeProgram() {
        return collegeProgram.get();
    }
}