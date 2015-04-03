package mj.util;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import mj.model.Issue;
import mj.model.IssueList;
import mj.model.Person;
import org.hibernate.Session;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by martynasjankauskas on 16/03/15.
 */
public class SaverLoader {

    private static ObservableList<Issue> issueList = FXCollections.observableArrayList();

    static Path issueFile = Paths.get("issues.txt");

    public static void saveInformation() {
        for (Issue task: issueList) {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            session.save(task);
            session.getTransaction().commit();
        }
    }

    public static void loadInformation() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        List<Issue> issues = session.createQuery("from Issue").list();
        issueList = FXCollections.observableArrayList(issues);
    }

    public static ObservableList<Issue> getIssueList() {
        return issueList;
    }
}
