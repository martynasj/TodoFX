package mj.todolistfx.dao;

import mj.todolistfx.entity.Issue;
import mj.todolistfx.service.HibernateUtil;
import org.hibernate.Session;

import java.util.List;

public class IssueDao {

    public void addIssue(Issue issue) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.save(issue);
        session.getTransaction().commit();
    }

    public void deleteIssue(Issue issue) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.delete(issue);
        session.getTransaction().commit();
    }

    public void updateIssue(Issue issue) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.update(issue);
        session.getTransaction().commit();
    }

    public List<Issue> listIssues() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        List<Issue> list = session.createQuery("from Issue").list();
        session.getTransaction().commit();
        return list;
    }

}
