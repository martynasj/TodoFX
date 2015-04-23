package mj.todolistfx.entity;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import javax.persistence.*;
import java.util.List;

/**
 * Created by martynasjankauskas on 11/03/15.
 */

@Entity
@Access(AccessType.PROPERTY)
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

    @Id
    @GeneratedValue
    @Column(name = "person_id")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

//    @Column(name = "firstName")
    public String getFirstName() {
        return firstName.get();
    }

    public StringProperty firstNameProperty() {
        return firstName;
    }

    public void setFirstName(String fName) {
        this.firstName.set(fName);
    }

//    @Column(name = "lastName")
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
        return getId() + " " + getFirstName() + " " + getLastName();
    }

}
