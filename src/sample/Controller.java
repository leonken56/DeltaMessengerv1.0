package sample;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

import javafx.beans.value.ObservableValue;
import javafx.event.Event;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class Controller implements Initializable{
    ObservableList list= FXCollections.observableArrayList();
    @FXML
    private ListView<String> listView;
    @FXML
    private TextArea textarea_outputmessage;
    @FXML
    private TextField textfield_usermessage;


    public void initialize(URL url, ResourceBundle rb)
    {
        try{
            loadData();
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }

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

        //listView = new ListView<>();
        listView.getItems().addAll(list);

        //SELECT CHANNEL
        listView.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends String> ov, String old_val, String new_val) -> {
            try {
                String selectedItem = listView.getSelectionModel().getSelectedItem();
                int index = listView.getSelectionModel().getSelectedIndex();
                //textarea_outputmessage.appendText("You selected " + selectedItem + " Channel at index : " + index +"\n");
                textarea_outputmessage.clear();
                String filepath = "C:\\Users\\NovA\\IdeaProjects\\HelloFX\\src\\sample\\channel1.txt";
                if (selectedItem == "Announcement")
                    filepath = "C:\\Users\\NovA\\IdeaProjects\\HelloFX\\src\\sample\\channel1.txt";
                else if (selectedItem == "GroupCollab")
                    filepath = "C:\\Users\\NovA\\IdeaProjects\\HelloFX\\src\\sample\\channel2.txt";
                else if (selectedItem == "ComputerScience")
                    filepath = "C:\\Users\\NovA\\IdeaProjects\\HelloFX\\src\\sample\\channel3.txt";
                else if (selectedItem == "Homework")
                    filepath = "C:\\Users\\NovA\\IdeaProjects\\HelloFX\\src\\sample\\channel4.txt";
                else if (selectedItem == "Gaming")
                    filepath = "C:\\Users\\NovA\\IdeaProjects\\HelloFX\\src\\sample\\channel5.txt";
                else if (selectedItem == "Sport")
                    filepath = "C:\\Users\\NovA\\IdeaProjects\\HelloFX\\src\\sample\\channel6.txt";
                else if (selectedItem == "Funnymeme")
                    filepath = "C:\\Users\\NovA\\IdeaProjects\\HelloFX\\src\\sample\\channel7.txt";

                Scanner input = new Scanner(new File(filepath));
                while (input.hasNextLine()) {
                    String buffertext = input.nextLine();
                    textarea_outputmessage.appendText(buffertext + "\n");
                }


                String message = "Changed channel successfully";

                textarea_outputmessage.appendText(message + "\n");
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
                    String filepath="C:\\Users\\NovA\\IdeaProjects\\HelloFX\\src\\sample\\channel1.txt";
                    if (selectedItem =="Announcement")
                        filepath="C:\\Users\\NovA\\IdeaProjects\\HelloFX\\src\\sample\\channel1.txt";
                    else if (selectedItem == "GroupCollab")
                        filepath="C:\\Users\\NovA\\IdeaProjects\\HelloFX\\src\\sample\\channel2.txt";
                    else if (selectedItem == "ComputerScience")
                        filepath="C:\\Users\\NovA\\IdeaProjects\\HelloFX\\src\\sample\\channel3.txt";
                    else if (selectedItem == "Homework")
                        filepath="C:\\Users\\NovA\\IdeaProjects\\HelloFX\\src\\sample\\channel4.txt";
                    else if (selectedItem == "Gaming")
                        filepath="C:\\Users\\NovA\\IdeaProjects\\HelloFX\\src\\sample\\channel5.txt";
                    else if (selectedItem == "Sport")
                        filepath="C:\\Users\\NovA\\IdeaProjects\\HelloFX\\src\\sample\\channel6.txt";
                    else if (selectedItem == "Funnymeme")
                        filepath="C:\\Users\\NovA\\IdeaProjects\\HelloFX\\src\\sample\\channel7.txt";
                    File file = new File(filepath);
                    FileWriter fr = new FileWriter(file, true);
                    BufferedWriter br = new BufferedWriter(fr);
                    if (textfield_usermessage.getText()!="")
                        br.write("<Username>: "+textfield_usermessage.getText()+"\n");
                    br.close();
                    fr.close();
                    textarea_outputmessage.appendText("<Username>: "+textfield_usermessage.getText()+"\n");

                    textfield_usermessage.clear();
                }
                catch(IOException g) {
                    g.printStackTrace();
                }


            }
        };
        textfield_usermessage.setOnAction(event);
    }

    public void button_changeChannel(Event evt) throws FileNotFoundException {
//        String a="Announcement";
//        String b="GroupCollab";
//        String c="ComputerScience";
//        String d="Homework";
//        String e="Gaming";
//        String g="Sport";
//        String h="Funnymeme";
            textarea_outputmessage.clear();
            String selectedItem = listView.getSelectionModel().getSelectedItem();
            String filepath="C:\\Users\\NovA\\IdeaProjects\\HelloFX\\src\\sample\\channel1.txt";
            if (selectedItem =="Announcement")
               filepath="C:\\Users\\NovA\\IdeaProjects\\HelloFX\\src\\sample\\channel1.txt";
            else if (selectedItem == "GroupCollab")
                filepath="C:\\Users\\NovA\\IdeaProjects\\HelloFX\\src\\sample\\channel2.txt";
            else if (selectedItem == "ComputerScience")
                filepath="C:\\Users\\NovA\\IdeaProjects\\HelloFX\\src\\sample\\channel3.txt";
            else if (selectedItem == "Homework")
                filepath="C:\\Users\\NovA\\IdeaProjects\\HelloFX\\src\\sample\\channel4.txt";
            else if (selectedItem == "Gaming")
                filepath="C:\\Users\\NovA\\IdeaProjects\\HelloFX\\src\\sample\\channel5.txt";
            else if (selectedItem == "Sport")
                filepath="C:\\Users\\NovA\\IdeaProjects\\HelloFX\\src\\sample\\channel6.txt";
            else if (selectedItem == "Funnymeme")
                filepath="C:\\Users\\NovA\\IdeaProjects\\HelloFX\\src\\sample\\channel7.txt";

        Scanner input = new Scanner(new File(filepath));
            while (input.hasNextLine())
            {
                String buffertext=input.nextLine();
                textarea_outputmessage.appendText(buffertext+"\n");
            }



        String message = "Changed channel successfully";

        textarea_outputmessage.appendText(message+"\n");
    }

    public void button_sendMessage(Event evt) throws IOException
    {
        String selectedItem = listView.getSelectionModel().getSelectedItem();
        String filepath="C:\\Users\\NovA\\IdeaProjects\\HelloFX\\src\\sample\\channel1.txt";
        if (selectedItem =="Announcement")
            filepath="C:\\Users\\NovA\\IdeaProjects\\HelloFX\\src\\sample\\channel1.txt";
        else if (selectedItem == "GroupCollab")
            filepath="C:\\Users\\NovA\\IdeaProjects\\HelloFX\\src\\sample\\channel2.txt";
        else if (selectedItem == "ComputerScience")
            filepath="C:\\Users\\NovA\\IdeaProjects\\HelloFX\\src\\sample\\channel3.txt";
        else if (selectedItem == "Homework")
            filepath="C:\\Users\\NovA\\IdeaProjects\\HelloFX\\src\\sample\\channel4.txt";
        else if (selectedItem == "Gaming")
            filepath="C:\\Users\\NovA\\IdeaProjects\\HelloFX\\src\\sample\\channel5.txt";
        else if (selectedItem == "Sport")
            filepath="C:\\Users\\NovA\\IdeaProjects\\HelloFX\\src\\sample\\channel6.txt";
        else if (selectedItem == "Funnymeme")
            filepath="C:\\Users\\NovA\\IdeaProjects\\HelloFX\\src\\sample\\channel7.txt";
        File file = new File(filepath);
        FileWriter fr = new FileWriter(file, true);
        BufferedWriter br = new BufferedWriter(fr);
        if (textfield_usermessage.getText()!="")
            br.write("<Username>: "+textfield_usermessage.getText()+"\n");
        br.close();
        fr.close();
        if (textfield_usermessage.getText()!="")
             textarea_outputmessage.appendText("<Username>: "+textfield_usermessage.getText()+"\n");

        textfield_usermessage.clear();
    }
}
