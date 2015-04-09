package mj.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by martynasjankauskas on 11/03/15.
 */

public class Person {

    long id;
    private StringProperty firstName;
    private StringProperty lastName;

    public Person() {
        this("Default", "Person");
    }

    public Person(String firstName, String lastName) {
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
    }

    public Person(String fullname) {
        String[] strings = fullname.split(" ");
        this.firstName = new SimpleStringProperty(strings[0]);
        this.lastName = new SimpleStringProperty(strings[1]);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName.get();
    }

    public StringProperty firstNameProperty() {
        return firstName;
    }

    public void setFirstName(String fName) {
        this.firstName.set(fName);
    }

    public String getLastName() {
        return lastName.get();
    }

    public StringProperty lastNameProperty() {
        return lastName;
    }

    public void setLastName(String lName) {
        this.lastName.set(lName);
    }

    @Override
    public String toString() {
        return getFirstName() + " " + getLastName();
    }

}
