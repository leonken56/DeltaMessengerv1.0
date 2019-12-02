package sample;
import com.mongodb.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
/**
 * <h1>Edit User Profile class</h1>
 * Edit User Profile class let user update their user profile information to the database.
 * @author  Anh Pham
 * @version 1.0
 * @since   2019-12-01
 */
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
    private Label status;
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
    /**
     * This method is used to initialize this class with loadData() method.
     * @param url Unused.
     * @param rb Unused.
     */
    public void initialize(URL url, ResourceBundle rb) {
        try {
            loadData();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * This method is used to load initial user's data, UI elements into the main UI .
     */
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
    /**
     * This method is used to handle event when the user click on save button.
     * Save user's new information by updating database.
     * @param event This is the actionevent name indicate clicking action.
     * @exception Exception on file not found.
     * @see Exception
     */
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
        status.setText("Profile Saved.");

    }
    /**
     * This method is used to handle event when the user click on reset button.
     * Reset the user input to previous state.
     * @param event This is the actionevent name indicate clicking action.
     * @exception Exception on file not found.
     * @see Exception
     */
    public void reset(ActionEvent event) throws Exception {
        DBObject query = new BasicDBObject("userid", Authentication.getUsername());
        DBCursor cursor = userlist.find(query);
        DBObject jo = cursor.one();
        //Display to textfield.
        display_name.setText((String) cursor.one().get("name"));
        display_dob.setText((String) cursor.one().get("dob"));
        display_phonenumber.setText((String) cursor.one().get("phonenumber"));
        display_email.setText((String) cursor.one().get("email"));
        status.setText("Profile Reset.");
    }

}
