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

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.ServerAddress;
import com.mongodb.MongoCredential;
import com.mongodb.MongoClientOptions;

public class Authentication{
    private static String display_username;

    public static String getUsername() {
        return display_username;
    }

    public static void setUsername(String display_thisusername) {
        Authentication.display_username = display_thisusername;
    }
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

//            String user="generalaccess";
//            String database="userdatabase";
//            char[] password="123".toCharArray();
//
//            MongoCredential credential = MongoCredential.createCredential(user,
//                    database,
//                    password);
//            MongoClient mongoClient = new MongoClient(new ServerAddress("125.025.125.2", 27017),
//                    Arrays.asList(credential));

            //Online host URL
            MongoClientURI uri = new MongoClientURI(
                    "mongodb+srv://userdataaccess:user@cluster0-7hpzc.mongodb.net/test:27017?retryWrites=true&w=majority/userdatabase");
            //Online host
            MongoClient mongoClient = new MongoClient(uri);
            // Localhost
            // MongoClient mongoClient = new MongoClient("localhost", 27017);
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
                setUsername(userid.getText());
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
            status.setText("Unable to connect to cloud server");
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
