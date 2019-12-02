package messenger;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
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
