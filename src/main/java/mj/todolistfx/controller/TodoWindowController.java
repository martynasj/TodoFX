package mj.todolistfx.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import mj.todolistfx.entity.Issue;
import mj.todolistfx.entity.IssueList;
import mj.todolistfx.entity.Person;
import mj.todolistfx.dao.PersonDao;
import mj.todolistfx.service.Priority;
import mj.todolistfx.service.IssueService;
import org.reactfx.EventStream;
import org.reactfx.EventStreams;

import javax.sql.StatementEventListener;
import java.time.Duration;
import java.time.LocalDate;

public class TodoWindowController {

    private Issue selectedIssue;


    @FXML
    private AnchorPane detailsPane;

    @FXML
    private TableView<Issue> todoTableView;

    @FXML
    private TableColumn<Issue, String> taskColumn;

    @FXML
    private TableColumn<Issue, LocalDate> dateColumn;

    @FXML
    private TableColumn<Issue, Boolean> stateColumn;

    @FXML
    private TableColumn<Issue, String> personColumn;

    @FXML
    private TextField taskTitleField;


    @FXML
    private Button addIssueButton;

    @FXML
    private Button completeButton;

    @FXML
    private Button deleteButton;

    @FXML
    private TextArea remarksTextArea;

    @FXML
    private ComboBox<Person> personPicker;

    @FXML
    private DatePicker datePicker;

    @FXML
    private ComboBox<Priority> priorityPicker;

    @FXML
    private TextArea taskTitleArea;

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

        setTaskDetails(null);

        // Main table
        todoTableView.setItems(FXCollections.observableArrayList(IssueService.listIssues()));
        taskColumn.setCellValueFactory(cellData -> cellData.getValue().taskTitleProperty());
        dateColumn.setCellValueFactory(cellData -> cellData.getValue().dateDueProperty());
        personColumn.setCellValueFactory(cellData -> cellData.getValue().responsiblePersonProperty());
        stateColumn.setCellFactory(CheckBoxTableCell.forTableColumn(stateColumn));
        stateColumn.setCellValueFactory(f -> f.getValue().isCompletedProperty());

        // Priorities list
        ObservableList<Priority> priorities = FXCollections.observableArrayList();
        priorities.addAll(Priority.HIGH, Priority.NORMAL, Priority.LOW);
        priorityPicker.setItems(priorities);

        // Person list
        personPicker.setItems(FXCollections.observableArrayList(PersonDao.listPersons()));


        /**
         *  ACTIONS
         */

        addIssueButton.setOnAction((event) -> {
            Issue issue = new Issue("New Task");
            todoTableView.getItems().add(issue);
            IssueService.addIssue(issue);
            todoTableView.getSelectionModel().selectLast();
            taskTitleField.requestFocus();
            selectedIssue = todoTableView.getSelectionModel().getSelectedItem();
        });

        taskTitleField.setOnKeyPressed((event) -> {
            if (event.getCode().equals(KeyCode.ENTER)) {
                todoTableView.requestFocus();
                selectedIssue.setTaskTitle(taskTitleField.getText());
                IssueService.updateIssue(selectedIssue);
            }
        });

        completeButton.setOnAction((event) -> {
            if (selectedIssue.getIsCompleted()) {
                selectedIssue.setIsCompleted(false);
            } else {
                selectedIssue.setIsCompleted(true);
            }
        });

        // DELETE BUTTON
        deleteButton.setOnAction((event) -> {
            IssueService.deleteIssue(selectedIssue);
            todoTableView.getItems().remove(selectedIssue);
        });

        // DELETE WITH BACKSPACE
        todoTableView.setOnKeyPressed((event) -> {
            if (event.getCode().equals(KeyCode.BACK_SPACE)) {
                IssueService.deleteIssue(selectedIssue);
            }
        });



        /**
         * LISTENERS
         */

        // Table selection listener, updates right pane with details
        todoTableView.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    this.selectedIssue = newValue;
                    setTaskDetails(newValue);
        });

        // UPDATES TABLE WITH NEW TASK TITLE ON KEY PRESSED
        taskTitleField.textProperty().addListener((observable, oldValue, newValue) -> {
            this.selectedIssue = todoTableView.getSelectionModel().selectedItemProperty().getValue();
            selectedIssue.setTaskTitle(taskTitleField.getText());
        });


        // UPDATES DATABASE ENTITY REMARKS EVERY 2 SECONDS AFTER CHANGED
        // USING REACTFX LIBRARY
        EventStreams.valuesOf(remarksTextArea.textProperty()).successionEnds(Duration.ofSeconds(1)).subscribe(s -> {
            if (selectedIssue != null) {
                selectedIssue.setTaskRemarks(remarksTextArea.getText());
                IssueService.updateIssue(selectedIssue);
                System.out.println("saved to db");
            }
        });



        /**
         * PICKERS
         */

        personPicker.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    selectedIssue.setResponsiblePerson(newValue);
                    IssueService.updateIssue(selectedIssue);
                }
        );

        priorityPicker.setOnAction(event -> {
            selectedIssue.setPriority(priorityPicker.getValue());
        });

        datePicker.setOnAction(event -> {
            selectedIssue.setDateDue(datePicker.getValue());
            IssueService.updateIssue(selectedIssue);
        });

    }


    private void setTaskDetails(Issue selectedIssue) {
        if (selectedIssue != null) {
            detailsPane.setDisable(false);
            Person responsiblePerson = (Person) selectedIssue.getResponsiblePerson();
            taskTitleField.setText(selectedIssue.getTaskTitle());
            remarksTextArea.setText(selectedIssue.getTaskRemarks());
            personPicker.getSelectionModel().select(responsiblePerson);
            dateCreatedLabel.setText(selectedIssue.getTaskDate().toString());
            datePicker.setValue(selectedIssue.getDateDue());
            priorityPicker.setValue(selectedIssue.getPriority());
        } else {
            detailsPane.setDisable(true);
        }
    }

    // To be implemented one day
    private void changeStyleToCompleted() {
        dateCreatedLabel.setStyle("-fx-background-color: green");
    }

}
