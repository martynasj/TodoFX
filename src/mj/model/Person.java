package mj.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by martynasjankauskas on 11/03/15.
 */

public class Person {

    private StringProperty fName;
    private StringProperty lName;

    public Person(String fName, String lName) {
        this.fName = new SimpleStringProperty(fName);
        this.lName = new SimpleStringProperty(lName);
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
