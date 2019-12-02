package messenger;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.event.Event;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
/**
 * <h1>Client View Controller class</h1>
 * This Client View Controller class controls all the elements of the main UI.
 * @author  Anh Pham
 * @version 1.0
 * @since   2019-12-01
 */
public class Controller implements Initializable{
    ObservableList list= FXCollections.observableArrayList();
    @FXML
    private ListView<String> listView;
    @FXML
    private ListView<String>  list_onlineusers;
    @FXML
    private TextArea textarea_outputmessage;
    @FXML
    private TextField textfield_usermessage;
    @FXML
    private MenuItem menu_logout_item;

    private static String thisUserid, thisUsername, thisUserDOB, thisUserEmail,thisUserPhonenumber,selected_onlineuser;

    //Set targeted online user from User Online list:
    public static String getUsername() {
        return selected_onlineuser;
    }
    public static void setUsername(String display_thisusername) {
        Controller.selected_onlineuser = display_thisusername;
    }

    //Set current user
    public static String getThisUser() {
        return thisUserid;
    }
    public static String getThisUsername() { return thisUsername; }
    public static String getThisUserDOB() {
        return thisUserDOB;
    }
    public static String getThisUserEmail() {
        return thisUserEmail;
    }
    public static String getThisUserPhonenumber() {
        return thisUserPhonenumber;
    }

    /**
     * This method is used to indicate the user about the Current User who using the application with their basic information .
     * @param thisuserid This get the user id.
     * @param thisusername This get the user name.
     * @param thisuserdob This get the user date of birth.
     * @param thisuseremail This get the user email.
     * @param thisuserphone This get the user phone number.
     
     */
    public static void setCurrentUser(String thisuserid, String thisusername, String thisuserdob, String thisuseremail, String thisuserphone)
    {
        Controller.thisUserid= thisuserid;
        Controller.thisUsername=thisusername;
        Controller.thisUserDOB=thisuserdob;
        Controller.thisUserEmail=thisuseremail;
        Controller.thisUserPhonenumber=thisuserphone;
    }

    /**
     * This method is used to initialize this class with loadData() method.
     * @param url Unused.
     * @param rb Unused.
     
     */
    public void initialize(URL url, ResourceBundle rb)
    {
        try{
            loadData();
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * This method is used to load initial data, UI elements into the main UI .
     */
    private void loadData() throws IOException{

        list.clear();
        String a="Announcement";
        String b="GroupCollab";
        String c="ComputerScience";
        String d="Homework";
        String e="Gaming";
        String g="Sport";
        String h="Funnymeme";
        list.addAll(a,b,c,d,e,g,h);
        listView.getItems().addAll(list);
        //SELECT ONLINE USER
        String onlineuser=Authentication.getUsername();
        list_onlineusers.getItems().add(onlineuser);
        //#DELETELATER
        list_onlineusers.getItems().add("admin");
        list_onlineusers.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends String> ov, String old_val, String new_val) -> {
            try {
                String selectedItem = list_onlineusers.getSelectionModel().getSelectedItem();
                setUsername(selectedItem);
                //import new stage for user profile
                Stage secondaryStage = new Stage();
                Parent MainStage = FXMLLoader.load(getClass().getResource("DisplayUserProfile.fxml"));
                secondaryStage.setTitle(selectedItem+"'s Profile");
                secondaryStage.setScene(new Scene(MainStage, 622, 420));
                secondaryStage.show();
                //Debug show status:
//                String message = "Shows user profile";
//                textarea_outputmessage.appendText(message + "\n");
            }
            catch(IOException io2) {
                io2.printStackTrace();
            }
        });
        //SELECT CHANNEL
        listView.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends String> ov, String old_val, String new_val) -> {
            try {
                String selectedItem = listView.getSelectionModel().getSelectedItem();
                int index = listView.getSelectionModel().getSelectedIndex();
                textarea_outputmessage.clear();
                URL url = getClass().getResource("Channel1.txt");
                if (selectedItem =="Announcement")
                    url = getClass().getResource("Channel1.txt");
                else if (selectedItem == "GroupCollab")
                    url = getClass().getResource("Channel2.txt");
                else if (selectedItem == "ComputerScience")
                    url = getClass().getResource("Channel3.txt");
                else if (selectedItem == "Homework")
                    url = getClass().getResource("Channel4.txt");
                else if (selectedItem == "Gaming")
                    url = getClass().getResource("Channel5.txt");
                else if (selectedItem == "Sport")
                    url = getClass().getResource("Channel6.txt");
                else if (selectedItem == "Funnymeme")
                    url = getClass().getResource("Channel7.txt");

                Scanner input = new Scanner(new File(url.getPath()));
                while (input.hasNextLine()) {
                    String buffertext = input.nextLine();
                    textarea_outputmessage.appendText(buffertext + "\n");

                }
                //Debug show status:
//                String message = "Changed channel successfully";
//                textarea_outputmessage.appendText(message + "\n");
            }
            catch(IOException t) {
                t.printStackTrace();
            }
        });
        //ENTER TO SUBMIT MESSAGE
        EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e)
            {
                try {
                    String selectedItem = listView.getSelectionModel().getSelectedItem();
                    URL url = getClass().getResource("Channel1.txt");
                    if (selectedItem =="Announcement")
                        url = getClass().getResource("Channel1.txt");
                    else if (selectedItem == "GroupCollab")
                        url = getClass().getResource("Channel2.txt");
                    else if (selectedItem == "ComputerScience")
                        url = getClass().getResource("Channel3.txt");
                    else if (selectedItem == "Homework")
                        url = getClass().getResource("Channel4.txt");
                    else if (selectedItem == "Gaming")
                        url = getClass().getResource("Channel5.txt");
                    else if (selectedItem == "Sport")
                        url = getClass().getResource("Channel6.txt");
                    else if (selectedItem == "Funnymeme")
                        url = getClass().getResource("Channel7.txt");

                    Scanner input = new Scanner(new File(url.getPath()));
                    File file = new File(url.getPath());
                    FileWriter fr = new FileWriter(file, true);
                    BufferedWriter br = new BufferedWriter(fr);
                    if (textfield_usermessage.getText()!="")
                        br.write(Authentication.getUsername()+": "+textfield_usermessage.getText()+"\n");
                    br.close();
                    fr.close();
                    textarea_outputmessage.appendText(Authentication.getUsername()+": "+textfield_usermessage.getText()+"\n");

                    textfield_usermessage.clear();
                }
                catch(IOException g) {
                    g.printStackTrace();
                }


            }
        };
        textfield_usermessage.setOnAction(event);
    }
    /**
     * This method is used to handle event when the user click on menu, user profile.
     * @param evt This is the actionevent name indicate clicking action.
     
     * @exception Exception on file not found.
     * @see Exception
     */
    public void OpenEditUserProfile(Event evt) throws Exception {
        try {
            //setup new stage for editing user profile
            Stage secondaryStage = new Stage();
            Parent MainStage = FXMLLoader.load(getClass().getResource("EditUserProfile.fxml"));
            secondaryStage.setTitle(Authentication.getUsername()+"'s Profile editing");
            secondaryStage.setScene(new Scene(MainStage, 622, 420));
            secondaryStage.show();

            //Debug show status:
//                String message = "Edit user profile";
//                textarea_outputmessage.appendText(message + "\n");
        }
        catch(IOException io2) {
            io2.printStackTrace();
        }
    }
    /**
     * This method is used to handle event when the user click on menu,LogOut.
     * @param evt This is the actionevent name indicate clicking action.
     
     * @exception Exception on file not found.
     * @see Exception
     */
    public void LogOut(ActionEvent evt) throws Exception {
        try {

            //setup new stage for authentication
            Platform.exit();
            Stage secondaryStage = new Stage();
            Parent MainStage = FXMLLoader.load(getClass().getResource("Authentication.fxml"));
            secondaryStage.setTitle("Delta Messenger");
            secondaryStage.setScene(new Scene(MainStage, 400, 200));
            secondaryStage.show();
            // close this windows
            //Debug show status:
//                String message = "Edit user profile";
//                textarea_outputmessage.appendText(message + "\n");
        }
        catch(IOException io2) {
            io2.printStackTrace();
        }
    }
    /**
     * This method is used to handle event when the user click on send message.
     * @param evt This is the actionevent name indicate clicking action.
     
     * @exception IOException on file writer/reader.
     * @see IOException
     */
    public void button_sendMessage(Event evt) throws IOException
    {
        String selectedItem = listView.getSelectionModel().getSelectedItem();
        URL url = getClass().getResource("Channel1.txt");
        if (selectedItem =="Announcement")
            url = getClass().getResource("Channel1.txt");
        else if (selectedItem == "GroupCollab")
            url = getClass().getResource("Channel2.txt");
        else if (selectedItem == "ComputerScience")
            url = getClass().getResource("Channel3.txt");
        else if (selectedItem == "Homework")
            url = getClass().getResource("Channel4.txt");
        else if (selectedItem == "Gaming")
            url = getClass().getResource("Channel5.txt");
        else if (selectedItem == "Sport")
            url = getClass().getResource("Channel6.txt");
        else if (selectedItem == "Funnymeme")
            url = getClass().getResource("Channel7.txt");
        Scanner input = new Scanner(new File(url.getPath()));
        File file = new File(url.getPath());
        FileWriter fr = new FileWriter(file, true);
        BufferedWriter br = new BufferedWriter(fr);
        if (textfield_usermessage.getText()!="")
            br.write(Authentication.getUsername()+": "+textfield_usermessage.getText()+"\n");
        br.close();
        fr.close();
        if (textfield_usermessage.getText()!="")
             textarea_outputmessage.appendText(Authentication.getUsername()+": "+textfield_usermessage.getText()+"\n");

        textfield_usermessage.clear();
    }
}
