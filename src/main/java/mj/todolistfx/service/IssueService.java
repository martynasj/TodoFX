package mj.todolistfx.service;

import mj.todolistfx.dao.IssueDao;
import mj.todolistfx.entity.Issue;
import mj.todolistfx.entity.IssueList;

import java.util.List;

public class IssueService {

    private static IssueDao issueDao = new IssueDao();

    public static void deleteIssue(Issue issue) {
        issueDao.deleteIssue(issue);
    }

    public static void addIssue(Issue issue) {
        issueDao.addIssue(issue);
    }

    public static void updateIssue(Issue issue) {
        issueDao.updateIssue(issue);
    }

    public static List<Issue> listIssues() {
        return issueDao.listIssues();
    }

}
