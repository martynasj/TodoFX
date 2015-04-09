package mj.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import mj.util.HibernateUtil;
import org.hibernate.Session;

import java.util.List;

/**
 * Created by martynasjankauskas on 11/03/15.
 */
public class PersonList {

    private static ObservableList<Person> personList = FXCollections.observableArrayList();

    public static void loadPersons() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        List<Person> persons = session.createQuery("from Person").list();
        personList = FXCollections.observableArrayList(persons);
        session.close();
    }

    public static ObservableList<Person> getPersonList() {
        return personList;
    }
}
