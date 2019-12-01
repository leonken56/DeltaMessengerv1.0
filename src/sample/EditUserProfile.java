package sample;
import com.mongodb.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
public class EditUserProfile implements Initializable {
    @FXML
    private TextField display_name;
    @FXML
    private TextField display_dob;
    @FXML
    private TextField display_phonenumber;
    @FXML
    private TextField display_email;
    @FXML
    private Button save;
    //Online host URL
    MongoClientURI uri = new MongoClientURI(
            "mongodb+srv://userdataaccess:user@cluster0-7hpzc.mongodb.net/test:27017?retryWrites=true&w=majority");
    //Online host
    MongoClient mongoClient = new MongoClient(uri);
    // Localhost
    // MongoClient mongoClient = new MongoClient("localhost", 27017);
    DB db = mongoClient.getDB("userdatabase");
    DBCollection userlist = db.getCollection("userlist");
    //System.out.println("Connected to mongodb");

    public void initialize(URL url, ResourceBundle rb) {
        try {
            loadData();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadData() throws IOException {

            DBObject query = new BasicDBObject("userid", Authentication.getUsername());
            DBCursor cursor = userlist.find(query);
            DBObject jo = cursor.one();
            //Display to textfield.
            display_name.setText((String) cursor.one().get("name"));
            display_dob.setText((String) cursor.one().get("dob"));
            display_phonenumber.setText((String) cursor.one().get("phonenumber"));
            display_email.setText((String) cursor.one().get("email"));

    }
    public void save(ActionEvent event) throws Exception {

        //Update user profile:
        //Query
        BasicDBObject query = new BasicDBObject();
        query.put("userid", Authentication.getUsername());

        //New User Information
        BasicDBObject newNAME = new BasicDBObject();
        newNAME.put("name", display_name.getText());
        BasicDBObject newDOB = new BasicDBObject();
        newDOB.put("dob", display_dob.getText());
        BasicDBObject newPHONE = new BasicDBObject();
        newPHONE.put("phonenumber", display_phonenumber.getText());
        BasicDBObject newEMAIL = new BasicDBObject();
        newEMAIL.put("email", display_email.getText());

        //Apply changes
        BasicDBObject updateObject = new BasicDBObject();
        updateObject.put("$set", newNAME);
        userlist.update(query, updateObject);
        updateObject.put("$set", newDOB);
        userlist.update(query, updateObject);
        updateObject.put("$set", newPHONE);
        userlist.update(query, updateObject);
        updateObject.put("$set", newEMAIL);
        userlist.update(query, updateObject);
        System.out.println("Saved new profile");

    }
    public void reset(ActionEvent event) throws Exception {
        DBObject query = new BasicDBObject("userid", Authentication.getUsername());
        DBCursor cursor = userlist.find(query);
        DBObject jo = cursor.one();
        //Display to textfield.
        display_name.setText((String) cursor.one().get("name"));
        display_dob.setText((String) cursor.one().get("dob"));
        display_phonenumber.setText((String) cursor.one().get("phonenumber"));
        display_email.setText((String) cursor.one().get("email"));
    }

}
