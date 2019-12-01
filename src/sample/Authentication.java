package sample;

import com.mongodb.MongoClient;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Authentication{
    @FXML
    private Label status;
    @FXML
    private TextField userid;
    @FXML
    private TextField userpass;

    public void Login(ActionEvent event) throws Exception
    {
        if (userid.getText().equals("nova") && userpass.getText().equals("nova"))
        {
            status.setText("Login Success");
            // hide login windows
            ((Node)(event.getSource())).getScene().getWindow().hide();

            //import stage
            Stage secondaryStage = new Stage();
            Parent MainStage = FXMLLoader.load(getClass().getResource("sample.fxml"));
            secondaryStage.setTitle("Delta Messenger");
            secondaryStage.setScene(new Scene(MainStage, 840, 550));
            secondaryStage.show();

        }

        else status.setText("Invalid Username or password");
    }
    public void Register(ActionEvent event) throws Exception
    {
            // hide login windows
            ((Node)(event.getSource())).getScene().getWindow().hide();

            //import stage
            Stage secondaryStage = new Stage();
            Parent MainStage = FXMLLoader.load(getClass().getResource("NewAccount.fxml"));
            secondaryStage.setTitle("Delta Messenger");
            secondaryStage.setScene(new Scene(MainStage, 400, 200));
            secondaryStage.show();
    }
}
