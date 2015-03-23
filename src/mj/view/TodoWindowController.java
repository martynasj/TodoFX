package mj.view;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.*;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import mj.model.Issue;
import mj.model.IssueList;
import mj.model.Person;
import mj.model.PersonList;
import mj.util.Priority;

import java.time.LocalDate;

public class TodoWindowController {

    private ObservableList<Issue> issueList;

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
    private Label taskListLabel;

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

        issueList = IssueList.getSampleList();

        PersonList personList = new PersonList();

        setTaskDetails(null);

        addIssueButton.setOnAction((event) -> {
            detailsPane.setDisable(false);
            taskTitleField.requestFocus();
            IssueList.createNewIssue("");
            todoTableView.getSelectionModel().select(IssueList.getSampleList().size() - 1);
            selectedIssue = todoTableView.getSelectionModel().getSelectedItem();
            taskTitleField.clear();
        });

        completeButton.setOnAction((event) -> {
            if (selectedIssue.getIsCompleted()) {
                selectedIssue.setIsCompleted(false);
            } else {
                selectedIssue.setIsCompleted(true);
            }
        });

        deleteButton.setOnAction((event) -> {
            IssueList.deleteIssue(todoTableView.getSelectionModel().getSelectedIndex());
        });

        // Don't know where to construct this list
        ObservableList<Priority> priorities = FXCollections.observableArrayList();
        priorities.addAll(Priority.HIGH, Priority.NORMAL, Priority.LOW);
        priorityPicker.setItems(priorities);

        todoTableView.setItems(issueList);
        personPicker.setItems(personList.getPersonList());

        taskColumn.setCellValueFactory(cellData -> cellData.getValue().taskTitleProperty());
        dateColumn.setCellValueFactory(cellData -> cellData.getValue().dateDueProperty());
        personColumn.setCellValueFactory(cellData -> cellData.getValue().responsiblePersonProperty());

        stateColumn.setCellFactory(CheckBoxTableCell.forTableColumn(stateColumn));
        stateColumn.setCellValueFactory(f -> f.getValue().isCompletedProperty());

//        // Marks the selected task completed
//        statusCheckBox.setOnAction(event -> {
//            selectedIssue.setIsCompleted(statusCheckBox.isSelected());
//        });

        // Listen for selection changes and show the task details when changed.
        todoTableView.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    this.selectedIssue = newValue;
                    setTaskDetails(newValue);
        });

        taskTitleField.setOnKeyPressed((event) -> {
            if (event.getCode().equals(KeyCode.ENTER)) {
                todoTableView.requestFocus();
            }
        });

        taskTitleField.textProperty().addListener((observable, oldValue, newValue) -> {
            this.selectedIssue = todoTableView.getSelectionModel().selectedItemProperty().getValue();
            selectedIssue.setTaskTitle(taskTitleField.getText());
        });

        // Deletes Issue from list
        // Detects when the delete key is pressed
        todoTableView.setOnKeyPressed((event) -> {
            if (event.getCode().equals(KeyCode.BACK_SPACE)) {
                IssueList.deleteIssue(todoTableView.getSelectionModel().getSelectedIndex());
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
