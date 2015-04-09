package mj.util;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import mj.model.Issue;
import org.hibernate.Session;

import java.util.List;

/**
 * Created by martynasjankauskas on 16/03/15.
 */
public class SaverLoader {

    private static ObservableList<Issue> issueList = FXCollections.observableArrayList();

    public static void loadInformation() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        List<Issue> issues = session.createQuery("from Issue").list();
        issueList = FXCollections.observableArrayList(issues);
        session.close();
    }

    public static ObservableList<Issue> getIssueList() {
        return issueList;
    }
}
