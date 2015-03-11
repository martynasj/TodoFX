package mj.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Created by martynasjankauskas on 11/03/15.
 */
public class PersonList {

    private ObservableList<Person> personList = FXCollections.observableArrayList();

    public PersonList() {
        makePersonList();
    }

    private void makePersonList() {
        personList.add(new Person());
        personList.add(new Person("Martynas", "Jankauskas"));
        personList.add(new Person("Domas", "Jurkonis"));
        personList.add(new Person("Gytis", "Kandrotas"));
    }

    public ObservableList<Person> getPersonList() {
        return personList;
    }
}
