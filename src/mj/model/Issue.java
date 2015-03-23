package mj.model;

import javafx.beans.property.*;
import mj.util.Priority;

import java.time.LocalDate;

/**
 * Created by martynasjankauskas on 11/03/15.
 */

public class Issue {

    private StringProperty taskTitle;
    private StringProperty taskRemarks;
    private BooleanProperty isCompleted;
    private ObjectProperty responsiblePerson;
    private ObjectProperty<LocalDate> taskDate;
    private ObjectProperty<LocalDate> dateDue;
    private Priority priority;

    /**
    *   Constructor
    */

    public Issue() {
        this("Default task");
    }

    public Issue(String taskTitle) {

        this.taskTitle = new SimpleStringProperty(taskTitle);
        this.taskRemarks = new SimpleStringProperty("");
        this.isCompleted = new SimpleBooleanProperty(false);    // initial state is false
        this.responsiblePerson = new SimpleObjectProperty<>();
        this.taskDate = new SimpleObjectProperty<>(LocalDate.now());
        this.dateDue = new SimpleObjectProperty<>(LocalDate.now());
        this.priority = Priority.NORMAL;

    }

    public String getTaskRemarks() {
        String remark = taskRemarks.get().replace("<br>", "\n");
        return remark;
    }

    public String getTaskRemarksOneLine() {
        String remark = taskRemarks.get().replace("\n", "<br>");
        return remark;
    }

    public StringProperty taskRemarksProperty() {
        return taskRemarks;
    }

    public void setTaskRemarks(String taskRemarks) {
        this.taskRemarks.set(taskRemarks);
    }

    public LocalDate getTaskDate() {
        return taskDate.get();
    }

    public ObjectProperty<LocalDate> taskDateProperty() {
        return taskDate;
    }

    public void setTaskDate(LocalDate taskDate) {
        this.taskDate.set(taskDate);
    }

    public LocalDate getDateDue() {
        return dateDue.get();
    }

    public ObjectProperty<LocalDate> dateDueProperty() {
        return dateDue;
    }

    public void setDateDue(LocalDate dateDue) {
        this.dateDue.set(dateDue);
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

    public Object getResponsiblePerson() {
        return responsiblePerson.get();
    }

    public ObjectProperty responsiblePersonProperty() {
        return responsiblePerson;
    }

    public void setResponsiblePerson(Object responsiblePerson) {
        this.responsiblePerson.set(responsiblePerson);
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

}
