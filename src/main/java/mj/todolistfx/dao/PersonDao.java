package mj.todolistfx.dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import mj.todolistfx.entity.Person;
import mj.todolistfx.service.HibernateUtil;
import org.hibernate.Session;

import java.util.List;

/**
 * Created by martynasjankauskas on 11/03/15.
 */
public class PersonDao {

    public static List<Person> listPersons() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        List<Person> persons = session.createQuery("from Person").list();
        session.getTransaction().commit();
        return persons;
    }

}
