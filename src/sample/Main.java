package sample;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.ListView;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

/**
 * <h1>Main of Delta Messenger Application</h1>
 * Delta Messenger Program implements an application that let users communicate with each other.
 * @author  Anh Pham
 * @version 1.0
 * @since 2019-12-01
 */
public class Main extends Application {
    /**
     * This is the start method which launches the first FXML windows.
     * @param primaryStage Load the primary stage.
     * @exception Exception on file not found.
     * @see Exception
     
     */
    @Override
    public void start(Stage primaryStage) throws Exception{
        //sample.CreateUserDatabase.create();
        Parent root = FXMLLoader.load(getClass().getResource("Authentication.fxml"));
        primaryStage.setTitle("Delta Messenger");
        primaryStage.setScene(new Scene(root, 400, 200));
        primaryStage.show();

    }

    /**
     * This is the main method which launches the application.
     * @param args Unused.
     
     */

    public static void main(String[] args) {

        launch(args);
    }
}
