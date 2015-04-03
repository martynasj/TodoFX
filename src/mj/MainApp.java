package mj;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import mj.model.Issue;
import mj.model.IssueList;
import mj.model.Person;
import mj.util.HibernateUtil;
import mj.util.SaverLoader;
import mj.view.TodoWindowController;
import org.hibernate.Session;
import sun.plugin.javascript.navig.Anchor;

import java.io.IOException;

public class MainApp extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;

    @Override
    public void start(Stage primaryStage) {

        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("ToDo List");

//        SaverLoader.loadInformation();
        createAndStoreIssue("bandom1", "komentaras1");

//        initRootLayout();
        showTodoWindow();

        // SaverLoader - class containing some static methods for saving and loading information
        primaryStage.setOnCloseRequest((event) -> {
//            SaverLoader.saveInformation();
        });

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
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("view/TodoWindow.fxml"));
            AnchorPane todoWindow = (AnchorPane) loader.load();

            primaryStage.setScene(new Scene(todoWindow));
            primaryStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createAndStoreIssue(String title, String remark) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        Issue is = new Issue();
        is.setTaskTitle(title);
        is.setTaskRemarks(remark);
        session.save(is);

        session.getTransaction().commit();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
