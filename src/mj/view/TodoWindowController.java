package mj.view;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.*;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.util.Callback;
import mj.model.Issue;
import mj.model.IssueList;
import mj.model.Person;
import mj.model.PersonList;
import mj.util.Priority;

import java.time.LocalDate;

public class TodoWindowController {

    public ObservableList<Issue> sampleList = FXCollections.observableArrayList();

    private Issue selectedIssue;

    @FXML
    private TableView<Issue> todoTableView;

    @FXML
    private TableColumn<Issue, String> taskColumn;

    @FXML
    private TableColumn<Issue, LocalDate> dateColumn;

    @FXML
    private TableColumn<Issue, Boolean> stateColumn;

    @FXML
    private TextField taskTitleField;

    @FXML
    private Label taskListLabel;

    @FXML
    private Button myButton;

    @FXML
    private TextArea remarksTextArea;

    @FXML
    private ComboBox<Person> personPicker;

    @FXML
    private DatePicker datePicker;

    @FXML
    private ComboBox<Priority> priorityPicker;

    @FXML
    private Label dateCreatedLabel;

    @FXML
    private CheckBox statusCheckBox;

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {



        IssueList issueList = new IssueList();
        PersonList personList = new PersonList();

        // Don't know where to construct this list
        ObservableList<Priority> priorities = FXCollections.observableArrayList();
        priorities.addAll(Priority.HIGH, Priority.NORMAL, Priority.LOW);
        priorityPicker.setItems(priorities);

        todoTableView.setItems(issueList.getSampleList());
        personPicker.setItems(personList.getPersonList());
        datePicker.setDisable(false);

        changeStyleToCompleted();

        taskColumn.setCellValueFactory(cellData -> cellData.getValue().taskTitleProperty());

        dateColumn.setCellValueFactory(cellData -> cellData.getValue().dateDueProperty());

        stateColumn.setCellValueFactory( f -> f.getValue().isCompletedProperty());
        stateColumn.setCellFactory( tc -> new CheckBoxTableCell<Issue, Boolean>());

        myButton.setOnAction((event) -> {
            // Button was clicked, do something...
            remarksTextArea.appendText(taskTitleField.getText());
        });

        statusCheckBox.setOnAction(event -> {
            selectedIssue.setIsCompleted(statusCheckBox.isSelected());
        });

        // Listen for selection changes and show the person details when changed.
        todoTableView.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    this.selectedIssue = newValue;
                    setTaskDetails();
                });

        // Detects only when the enter key is pressed
        taskTitleField.setOnKeyPressed((event) -> {
            if (event.getCode().equals(KeyCode.ENTER)) {
                issueList.createNewIssue(taskTitleField.getText());
                taskTitleField.clear();
            }
        });

        personPicker.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    todoTableView.getSelectionModel().getSelectedItem().setResponsiblePerson(newValue);
                }
        );

        priorityPicker.setOnAction(event -> {
            selectedIssue.setPriority(priorityPicker.getValue());
        });

        datePicker.setOnAction(event -> {
            selectedIssue.setDateDue(datePicker.getValue());
        });

//        Tried to enable datepicker when mouse enters
//        datePicker.setOnMouseEntered(event -> {
//            System.out.println(datePicker.isDisabled());
//        });

        remarksTextArea.textProperty().addListener((observable, oldValue, newValue) -> {
            Issue selectedIssue = todoTableView.getSelectionModel().selectedItemProperty().getValue();
            selectedIssue.setTaskRemarks(remarksTextArea.getText());
        });

    }

    private void setTaskDetails() {
        Person responsiblePerson = (Person) selectedIssue.getResponsiblePerson();
        remarksTextArea.setText(selectedIssue.getTaskRemarks());
        personPicker.getSelectionModel().select(responsiblePerson);
        dateCreatedLabel.setText(selectedIssue.getTaskDate().toString());
        statusCheckBox.setSelected(selectedIssue.getIsCompleted());
        datePicker.setValue(selectedIssue.getDateDue());
        priorityPicker.setValue(selectedIssue.getPriority());
    }

    // To be implemented one day
    private void changeStyleToCompleted() {
    }

}
