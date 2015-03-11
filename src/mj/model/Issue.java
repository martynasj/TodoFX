package mj.model;

import javafx.beans.property.*;

import java.util.Date;

/**
 * Created by martynasjankauskas on 11/03/15.
 */

public class Issue {

    private StringProperty taskTitle;
    private SimpleObjectProperty<Date> taskDate;
    private StringProperty taskRemarks;
    private BooleanProperty isCompleted;

    /**
    *   Constructor
    */

    public Issue(String taskTitle) {

        this.taskTitle = new SimpleStringProperty(taskTitle);
        this.taskDate = new SimpleObjectProperty<>(new Date());
        this.taskRemarks = new SimpleStringProperty();
        this.isCompleted = new SimpleBooleanProperty(true);    // initial state is false

    }

    public String getTaskRemarks() {
        return taskRemarks.get();
    }

    public StringProperty taskRemarksProperty() {
        return taskRemarks;
    }

    public void setTaskRemarks(String taskRemarks) {
        this.taskRemarks.set(taskRemarks);
    }

    public Date getTaskDate() {
        return taskDate.get();
    }

    public SimpleObjectProperty<Date> taskDateProperty() {
        return taskDate;
    }

    public void setTaskDate(Date taskDate) {
        this.taskDate.set(taskDate);
    }

    public String getTaskTitle() {
        return taskTitle.get();
    }

    public StringProperty taskTitleProperty() {
        return taskTitle;
    }

    public void setTaskTitle(String taskTitle) {
        this.taskTitle.set(taskTitle);
    }

    public boolean getIsCompleted() {
        return isCompleted.get();
    }

    public BooleanProperty isCompletedProperty() {
        return isCompleted;
    }

    public void setIsCompleted(boolean isCompleted) {
        this.isCompleted.set(isCompleted);
    }


}
