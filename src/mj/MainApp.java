package mj;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import mj.model.Issue;
import mj.model.IssueList;
import sun.plugin.javascript.navig.Anchor;

import java.io.IOException;

public class MainApp extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;

    @Override
    public void start(Stage primaryStage) {

        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("ToDo List");

        initRootLayout();

        showTodoWindow();

    }


    /**
     *  Root layout is the basic layout that holds other scenes and contains the menu
     */
    public void initRootLayout() {

        try {
            this.rootLayout = FXMLLoader.load(getClass().getResource("view/RootLayout.fxml"));
            primaryStage.setScene(new Scene(rootLayout));
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
    *   Loads main window of To Do tasks into the root layout
    */
    public void showTodoWindow() {
        try {
            AnchorPane todoWindow = FXMLLoader.load(getClass().getResource("view/TodoWindow.fxml"));
            rootLayout.setCenter(todoWindow);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

}
