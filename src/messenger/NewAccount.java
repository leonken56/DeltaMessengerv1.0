package messenger;

import com.mongodb.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * <h1>New Account class</h1>
 * This class is responsible for handling new account creation by inserting new user to userdatabase.
 * @author  Anh Pham
 * @version 1.0
 * @since   2019-12-01
 */
public class NewAccount {
    @FXML
    private Label status;
    @FXML
    private TextField textfield_userid;
    @FXML
    private TextField passwordfield_userpass;
    @FXML
    private TextField passwordfield_userpassconfirm;

    /**
     * This method is used to handle event when the user click on register button.
     * Create new user account by inserting new user's userid and password to userdatabase.
     * @param event This is the actionevent name indicate clicking action.
     * @exception Exception on file not found.
     * @see Exception
     */
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
