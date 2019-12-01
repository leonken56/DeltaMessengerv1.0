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

    }
    public void Register(ActionEvent event) throws Exception
    {
        System.out.println("Trying to connect to mongo");
        try {
            //Online host URL
            MongoClientURI uri = new MongoClientURI(
                    "mongodb+srv://generalaccess:user@cluster0-7hpzc.mongodb.net/test:27017?retryWrites=true&w=majority");
            //Online host
            MongoClient mongoClient = new MongoClient(uri);

            // Localhost
            // MongoClient mongoClient = new MongoClient("localhost", 27017);
            DB db = mongoClient.getDB("userdatabase");
            DBCollection userlist = db.getCollection("userlist");
            System.out.println("Connected to Database");
            System.out.println("Server is ready");

            //Fetching new user information
            BasicDBObject user = new BasicDBObject();
            //Push into database
            user.put("userid", textfield_userid.getText());

            DBCursor found = userlist.find(user);
            if (found.hasNext())
            {
                status.setText("ERROR: User account already exists");
            }
            else {
                if (passwordfield_userpass.getText().equals(passwordfield_userpassconfirm.getText())) {
                    user.put("password", passwordfield_userpass.getText());
                    userlist.insert(user);
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
                } else status.setText("ERROR: Your password is not matches");
            }

        }
        catch (Exception e) {
            System.out.println("exception occured");
            System.out.println(e);
        }






    }
}
