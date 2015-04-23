package mj.todolistfx;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import mj.todolistfx.dao.IssueDao;
import mj.todolistfx.entity.IssueList;
import mj.todolistfx.dao.PersonDao;
import mj.todolistfx.service.HibernateUtil;

import java.io.IOException;

public class MainApp extends Application {

    private Stage primaryStage;

    @Override
    public void start(Stage primaryStage) {

        IssueDao issueDao = new IssueDao();

        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("ToDo List");

//        IssueList.setIssueList(FXCollections.observableArrayList(issueDao.listIssues()));

        showTodoWindow();

        // Close hibernate connection to db on exit
        primaryStage.setOnCloseRequest(event -> {
            HibernateUtil.getSessionFactory().close();
        });

    }


    /**
    *   Loads main window of To Do tasks into the root layout
    */
    public void showTodoWindow() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/fxml/TodoWindow.fxml"));
            AnchorPane todoWindow = (AnchorPane) loader.load();

            primaryStage.setScene(new Scene(todoWindow));
            primaryStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

}
