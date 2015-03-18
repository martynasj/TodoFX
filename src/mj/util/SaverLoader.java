package mj.util;

import javafx.collections.ObservableList;
import mj.model.Issue;
import mj.model.IssueList;
import mj.model.Person;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;

/**
 * Created by martynasjankauskas on 16/03/15.
 */
public class SaverLoader {

    private static ObservableList<Issue> issueList = IssueList.getSampleList();

    static Path issueFile = Paths.get("issues.txt");

    public static void saveInformation() {

        try (PrintWriter bw = new PrintWriter(issueFile.toFile())) {

            for (Issue task: issueList) {

                bw.println(task.getTaskTitle());
                bw.println(task.getTaskRemarks());
                bw.println(task.getIsCompleted());
                bw.println(task.getResponsiblePerson().toString());
                bw.println(task.getTaskDate().toString());
                bw.println(task.getDateDue().toString());
                bw.println(task.getPriority().getValue());

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void loadInformation() {

        try (BufferedReader reader = new BufferedReader(new FileReader(issueFile.toString()))) {

            String[] lines = reader.lines().toArray(size -> new String[size]);

            for (int i = 0; i < lines.length / 7; i++ ) {

                int j = i*7;

                Issue issue = new Issue();
                issue.setTaskTitle(lines[0 + j]);
                issue.setTaskRemarks(lines[1 + j]);
                issue.setIsCompleted(Boolean.valueOf(lines[2 + j]));
                issue.setResponsiblePerson(new Person(lines[3 + j]));
                issue.setTaskDate(LocalDate.parse(lines[4 + j]));
                issue.setDateDue(LocalDate.parse(lines[5 + j]));
                issue.setPriority(Priority.valueOf(lines[6 + j].toUpperCase()));

                issueList.add(issue);

//                for (int j = i*7; j <7 + i*7; j++) {
//                    System.out.println(lines[j]);
//                }
            }


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
