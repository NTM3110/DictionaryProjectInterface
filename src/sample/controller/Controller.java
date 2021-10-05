package sample.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import sample.Connection.InternetConnection;
import sample.models.Dictionary;
import sample.models.DictionaryManagement;
import sample.models.Word;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import  org.controlsfx.control.textfield.TextFields;

public class Controller implements Initializable {
    @FXML
    private HBox header;

    @FXML
    private Button home_button;
    @FXML
    private VBox list_word;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private TextField textField;

    @FXML
    private  Button searchButton;

    @FXML
    private TextArea textArea;

    private DictionaryManagement dictionaryManagement =  new DictionaryManagement();
    private Dictionary dictionary = new Dictionary();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dictionary = dictionaryManagement.getDictionary();
        try {
            dictionaryManagement.insertFromFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (Word a: dictionary.getWord_list()) {
            Button word = new Button(a.getWord_target());
            word.setMinWidth(270);
            word.setMinHeight(45);
            word.setStyle("-fx-background-color: #E1E1E1");
            word.setPadding(new Insets(0,0,10,0));
            list_word.getChildren().add(word);
            scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
            scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
            scrollPane.setContent(list_word);
        }
}

    public void doSearch(){
        String inputStr = textField.getText();
        InternetConnection ic = new InternetConnection();
        String data = ic.getOnlineData(inputStr);
        if (data.equalsIgnoreCase("error"))
        {
            textArea.setText("Something is WRONG");
        }
        else textArea.setText(data);
    }
    public void displayWord(){
        list_word.getChildren().clear();
        if(!textField.getText().isEmpty()) {
            String inputStr = textField.getText();
            ArrayList<Word> displayWord = dictionaryManagement.advancedSearchWord(inputStr);
            for (Word a : displayWord) {
                Label word = new Label(a.getWord_target());
                word.setMinWidth(270);
                word.setMinHeight(45);
                word.setStyle("-fx-background-color: #E1E1E1");
                word.setStyle("-fx-text-alignment: left");
                word.setPadding(new Insets(0, 0, 10, 0));
                list_word.getChildren().add(word);
                scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
                scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
            }
            scrollPane.setContent(list_word);
        }
    }

}
