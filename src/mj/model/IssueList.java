package mj.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import mj.util.HibernateUtil;
import mj.util.SaverLoader;
import org.hibernate.Session;

/**
 * Created by martynasjankauskas on 11/03/15.
 */
public class IssueList {

    public static void insertIssueToDb(Issue issue) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        session.save(issue);
        session.getTransaction().commit();
        session.close();
    }

    public static void createNewIssue() {
        Issue issue = new Issue();
        SaverLoader.getIssueList().add(issue);
    }

    public static void deleteIssue(int selectedIndex) {
    }

    public static void updateIssue(Issue issue) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(issue);
        session.getTransaction().commit();
        session.close();
    }

}
