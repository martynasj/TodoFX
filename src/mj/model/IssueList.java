package mj.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import mj.util.HibernateUtil;
import org.hibernate.Session;

/**
 * Created by martynasjankauskas on 11/03/15.
 */
public class IssueList {

    public static void insertIssueToDb(Issue issue) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        session.save(issue);
    }

    public static void createNewIssue(String s) {
    }

    public static void deleteIssue(int selectedIndex) {
    }
}
