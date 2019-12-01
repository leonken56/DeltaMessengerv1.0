package sample;

import com.mongodb.DB;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import com.mongodb.MongoClient;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class NewAccount implements Initializable {
    @FXML
    private Label status;
    @FXML
    private TextField textfield_userid;
    @FXML
    private TextField passwordfield_userpass;
    @FXML
    private TextField passwordfield_userpassconfirm;

    public void initialize(URL url, ResourceBundle rb)
    {
            LoadData();

    }
    public void LoadData(){
        System.out.println("Trying to connect to mongo");
        try {
            MongoClient mongoClient = new MongoClient("localhost",27017);
            DB db = mongoClient.getDB("userdatabase");
            System.out.println("Connected to Database");

        }
        catch (Exception e) {
            System.out.println("exception occured");
            System.out.println(e);
        }
        System.out.println("Server is ready");
    }
    public void Register(ActionEvent event) throws Exception
    {


            //if SUCCESS
            //Hide register windows
            ((Node) (event.getSource())).getScene().getWindow().hide();

            //import stage
            Stage secondaryStage = new Stage();
            Parent MainStage = FXMLLoader.load(getClass().getResource("Authentication.fxml"));
            secondaryStage.setTitle("Delta Messenger");
            secondaryStage.setScene(new Scene(MainStage, 400, 200));
            secondaryStage.show();

            //else

    }
}
