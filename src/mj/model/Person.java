package mj.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.io.Serializable;

/**
 * Created by martynasjankauskas on 11/03/15.
 */

public class Person {

    private StringProperty fName;
    private StringProperty lName;

    public Person() {
        this("Default", "Person");
    }

    public Person(String fName, String lName) {
        this.fName = new SimpleStringProperty(fName);
        this.lName = new SimpleStringProperty(lName);
    }

    public Person(String fullname) {
        String[] strings = fullname.split(" ");
        this.fName = new SimpleStringProperty(strings[0]);
        this.lName = new SimpleStringProperty(strings[1]);
    }

    public String getfName() {
        return fName.get();
    }

    public StringProperty fNameProperty() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName.set(fName);
    }

    public String getlName() {
        return lName.get();
    }

    public StringProperty lNameProperty() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName.set(lName);
    }

    @Override
    public String toString() {
        return getfName() + " " + getlName();
    }

}
