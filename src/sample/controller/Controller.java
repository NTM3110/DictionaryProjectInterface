package sample.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import sample.Connection.InternetConnection;
import sample.models.*;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.swing.*;

public class Controller implements Initializable {
    @FXML
    private HBox header;

    @FXML
    private Button home_button;
    @FXML
    private VBox list_word;

    @FXML
    private ScrollPane scrollPane1;

    @FXML
    private TextField textField;
    @FXML
    private Label wordLabel;

    @FXML
    private  Button searchButton;

    @FXML
    private TextArea textArea;
    private DictionaryManagement dictionaryManagement =  new DictionaryManagement();
    private Dictionary dictionary = new Dictionary();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            dictionaryManagement.insertFromFileJSON();
        } catch (IOException e) {
            e.printStackTrace();
        }
        textArea.setEditable(false);
}

    public void doSearch(String inputStr){
       Word word =dictionaryManagement.searchWord(inputStr);
//        System.out.println(word.getWord_target());
       String wordStr = "";
       for (String a: word.getMeaning().keySet()){
           if (!a.equals("noType") && !a.equals("noEx"))    wordStr+=a+"\n\n";
           for (Description b : word.getMeaning().get(a)){
               wordStr+= b.getDefinition()+"\n";
               for (String c: b.getExample()){
                   wordStr+=c+"\n";
               }
           }
       }
       textArea.setText(wordStr);
       wordLabel.setText(word.getWord_target());
       wordLabel.setFont(Font.font(24));
       wordLabel.setTextFill(Color.BLUE);
    }
    void addClickListener(Label label){
        label.setOnMouseClicked(mouseEvent -> {
            String labelStr = label.getText();
            doSearch(labelStr);
        });
    }

    public void displayWord(){
        list_word.getChildren().clear();
        if(!textField.getText().isEmpty()) {
            String inputStr = textField.getText();
            ArrayList<Word> displayWord = dictionaryManagement.advancedSearchWord(inputStr);
            for (Word a : displayWord) {
                Label word = new Label(a.getWord_target());
                addClickListener(word);
                word.setMinWidth(270);
                word.setMinHeight(45);
                //word.setStyle("-fx-background-color: #E1E1E1");
                word.setStyle("-fx-text-alignment: left");
                word.setPadding(new Insets(0, 0, 10, 5));
                word.setBorder(new Border(new BorderStroke(Color.AQUAMARINE, BorderStrokeStyle.SOLID, new CornerRadii(1.0), BorderStroke.THICK)));
                list_word.getChildren().add(word);
                scrollPane1.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
                scrollPane1.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
            }
            scrollPane1.setContent(list_word);
        }
    }

}
