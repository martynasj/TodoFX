package mj.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import mj.util.HibernateUtil;
import mj.util.SaverLoader;
import org.hibernate.Session;

/**
 * Created by martynasjankauskas on 11/03/15.
 */
public class IssueManager {

    public static void createNewIssue() {
        Issue issue = new Issue();
        SaverLoader.getIssueList().add(issue);
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.save(issue);
        session.getTransaction().commit();
    }

    public static void deleteIssue(Issue issue) {
        SaverLoader.getIssueList().remove(issue);
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.delete(issue);
        session.getTransaction().commit();
    }

    public static void updateIssue(Issue issue) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.update(issue);
        session.getTransaction().commit();
    }

}
