package mj.todolistfx.entity;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class IssueList {

    private static ObservableList<Issue> issueList = FXCollections.observableArrayList();

    public static ObservableList<Issue> getIssueList() {
        return issueList;
    }

    public static void setIssueList(ObservableList<Issue> issueList) {
        IssueList.issueList = issueList;
    }
}
