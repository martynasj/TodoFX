package mj.todolistfx.entity;

import javafx.beans.property.*;
import mj.todolistfx.service.LocalDatePersistenceConverter;
import mj.todolistfx.service.Priority;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;

/**
 * Created by martynasjankauskas on 11/03/15.
 */

@Entity
@Table(name = "Issue")
@Access(AccessType.PROPERTY)
public class Issue {

    private Long id;
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
        this("New task");
    }

    public Issue(String taskTitle) {

        this.taskTitle = new SimpleStringProperty(taskTitle);
        this.taskRemarks = new SimpleStringProperty("");
        this.isCompleted = new SimpleBooleanProperty(false);    // initial state is false
        this.responsiblePerson = new SimpleObjectProperty<>(null);
        this.taskDate = new SimpleObjectProperty<>(LocalDate.now());
        this.dateDue = new SimpleObjectProperty<>(LocalDate.now());
        this.priority = Priority.NORMAL;

    }

    @Id
    @GeneratedValue
    @Column(name = "issue_id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    @Transient
    public LocalDate getTaskDate() {
        return taskDate.get();
    }

    public ObjectProperty<LocalDate> taskDateProperty() {
        return taskDate;
    }

    public void setTaskDate(LocalDate taskDate) {
        this.taskDate.set(taskDate);
    }

    @Convert(converter = LocalDatePersistenceConverter.class)
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

    @Transient
    public BooleanProperty isCompletedProperty() {
        return isCompleted;
    }

    public void setIsCompleted(boolean isCompleted) {
        this.isCompleted.set(isCompleted);
    }

    @OneToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "person_id")
    public Person getResponsiblePerson() {
        return (Person) responsiblePerson.get();
    }

    public ObjectProperty responsiblePersonProperty() {
        return responsiblePerson;
    }

    public void setResponsiblePerson(Person responsiblePerson) {
        this.responsiblePerson.set(responsiblePerson);
    }

    @Transient
    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    @Override
    public String toString() {
        return "id: " + getId() + "   title: " + getTaskTitle() + "   person: " + getResponsiblePerson();
    }
}
