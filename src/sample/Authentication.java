package sample;

import com.mongodb.*;
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
import org.bson.Document;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Authentication{
    @FXML
    private Label status;
    @FXML
    private TextField userid;
    @FXML
    private TextField userpass;
/*    public void initialize(URL url, ResourceBundle rb)
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
        System.out.println("Server is ready to check for ID");
    }*/

    public void Login(ActionEvent event) throws Exception {
        // Initialize connection with Database
        System.out.println("Trying to connect to mongo");
        try {
            MongoClient mongoClient = new MongoClient("localhost", 27017);
            DB db = mongoClient.getDB("userdatabase");
            DBCollection userlist = db.getCollection("userlist");
            System.out.println("Connected to Database");



        System.out.println("Server is ready to check for ID");
        System.out.println("Checking for ID");
        //#Authentication Main: Check for ID and PASSWORD

            //Query for id and password
            BasicDBObject andQuery = new BasicDBObject();
            List<BasicDBObject> obj = new ArrayList<BasicDBObject>();
            obj.add(new BasicDBObject("userid", userid.getText()));
            obj.add(new BasicDBObject("password", userpass.getText()));
            andQuery.put("$and", obj);

            DBCursor found = userlist.find(andQuery);
            if (found.hasNext())
            {
                status.setText("Login Success");
                // hide login windows
                ((Node) (event.getSource())).getScene().getWindow().hide();

                //import stage
                Stage secondaryStage = new Stage();
                Parent MainStage = FXMLLoader.load(getClass().getResource("sample.fxml"));
                secondaryStage.setTitle("Delta Messenger");
                secondaryStage.setScene(new Scene(MainStage, 840, 550));
                secondaryStage.show();

            } else status.setText("Invalid Username or password");
        }
        catch (Exception e) {
        System.out.println("exception occured");
        System.out.println(e);
    }
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
