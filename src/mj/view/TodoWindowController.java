package mj.view;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.*;
import javafx.scene.input.KeyCode;
import javafx.util.Callback;
import mj.model.Issue;
import mj.model.IssueList;
import mj.model.Person;
import mj.model.PersonList;

public class TodoWindowController {

    public ObservableList<Issue> sampleList = FXCollections.observableArrayList();

    @FXML
    private TableView<Issue> todoTableView;

    @FXML
    private TableColumn<Issue, String> taskColumn;

    @FXML
    private TableColumn<Issue, String> dateColumn;

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

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {

        IssueList issueList = new IssueList();
        PersonList personList = new PersonList();

        todoTableView.setItems(issueList.getSampleList());

        taskColumn.setCellValueFactory(cellData -> cellData.getValue().taskTitleProperty());

        dateColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().taskDateProperty().toString()));

        stateColumn.setCellValueFactory( f -> f.getValue().isCompletedProperty());
        stateColumn.setCellFactory( tc -> new CheckBoxTableCell<>());

        myButton.setOnAction((event) -> {
            // Button was clicked, do something...
            remarksTextArea.appendText(taskTitleField.getText());
        });

        // Listen for selection changes and show the person details when changed.
        todoTableView.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> setTaskDetails(newValue));

        personPicker.setItems(personList.getPersonList());

        // Detects only when the enter key is pressed
        taskTitleField.setOnKeyPressed((event) -> {
            if (event.getCode().equals(KeyCode.ENTER)) {
                issueList.createNewIssue(taskTitleField.getText());
                taskTitleField.clear();
            }
        });


        remarksTextArea.textProperty().addListener((observable, oldValue, newValue) -> {
            Issue selectedIssue = todoTableView.getSelectionModel().selectedItemProperty().getValue();
            selectedIssue.setTaskRemarks(remarksTextArea.getText());
        });

    }

    private void setTaskDetails(Issue selectedIssue) {
        remarksTextArea.setText(selectedIssue.getTaskRemarks());
    }

}
