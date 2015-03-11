package mj.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Created by martynasjankauskas on 11/03/15.
 */
public class IssueList {

    private static ObservableList<Issue> sampleList = FXCollections.observableArrayList();

    public IssueList() {
        makeSampleList();
    }

    private void makeSampleList() {

        Issue i1 = new Issue("Buy some milk");
        i1.setTaskRemarks("Don't forget that shops close here by 10 pm...");
        Issue i2 = new Issue("Repair that old bike");
        i2.setTaskRemarks("There are some pretty helpful youtube videos, need to check them out");
        Issue i3 = new Issue("Prepare for THE FINAL EXAM!");
        i3.setTaskRemarks("Still have 3 books to read, write some papers and receive notes from classmates.");

        sampleList.addAll(i1, i2, i3);

    }

    public ObservableList<Issue> getSampleList() {
        return sampleList;
    }

    public void createNewIssue(String taskTitle) {
        sampleList.add(new Issue(taskTitle));
    }

}
