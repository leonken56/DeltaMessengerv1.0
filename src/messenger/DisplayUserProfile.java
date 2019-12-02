package messenger;

import com.mongodb.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
/**
 * <h1>Display User Profile class</h1>
 * This Display User Profile class let user see selected user profile information from online users list.
 * @author  Anh Pham
 * @version 1.0
 * @since   2019-12-01
 */
public class DisplayUserProfile implements Initializable {
    @FXML
    TextField display_name;
    @FXML
    TextField display_dob;
    @FXML
    TextField display_phonenumber;
    @FXML
    TextField display_email;
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
     * This method is used to initialize connection with cloud mongoDB, load selected user's data and information to the GUI.
     * @exception IOException on input/output error.
     * @see IOException
     */
    private void loadData() throws IOException {
        //System.out.println("Trying to connect to mongo");
        try {
            //Online host URL
            MongoClientURI uri = new MongoClientURI(
                    "mongodb+srv://userdataaccess:user@cluster0-7hpzc.mongodb.net/test:27017?retryWrites=true&w=majority");
            //Online host
            MongoClient mongoClient = new MongoClient(uri);
            // Localhost
            // MongoClient mongoClient = new MongoClient("localhost", 27017);
            DB db = mongoClient.getDB("userdatabase");
            DBCollection userlist = db.getCollection("userlist");
            //System.out.println("Connected to Database");

            DBObject query = new BasicDBObject("userid", Controller.getUsername());
            DBCursor cursor = userlist.find(query);
            DBObject jo = cursor.one();
            //Display to textfield
            display_name.setText((String) cursor.one().get("name"));
            display_dob.setText((String) cursor.one().get("dob"));
            display_phonenumber.setText((String) cursor.one().get("phonenumber"));
            display_email.setText((String) cursor.one().get("email"));
        } catch (Exception e) {
            System.out.println("exception occured");
            System.out.println(e);

        }
    }
}