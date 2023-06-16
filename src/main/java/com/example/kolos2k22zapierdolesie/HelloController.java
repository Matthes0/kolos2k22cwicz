package com.example.kolos2k22zapierdolesie;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

public class HelloController {
    public ListView wordList;
    public TextField filterField;
    public Label wordCountLabel;
    private List<String> allWords = new ArrayList<>();
    @FXML
    private Label welcomeText;
    protected void updateWordCountLabel(){
        int prevValue = Integer.parseInt(wordCountLabel.getText());
        Platform.runLater(() -> wordCountLabel.setText(Integer.toString(prevValue+1)));
    }
    protected void addToWordList(String word) {
//        Collator collator = Collator.getInstance(new Locale("pl", "PL"));
//        collator.setStrength(Collator.SECONDARY);
//        Comparator<String> comparator = collator::compare;
//        String words[] = word.split(" ");
        Comparator<String> comparator = (o1, o2) -> {
            String splitted1[] = o1.split(" ");
            String splitted2[] = o2.split(" ");
            Collator collator = Collator.getInstance(new Locale("pl", "PL"));
            collator.setStrength(Collator.SECONDARY);

            return collator.compare(splitted1[1], splitted2[1]);
        };
        if (!filterField.getText().isEmpty()) {
            allWords.add(word);
            List tmpWordList = new ArrayList<>();
            for (String wordl : allWords) {
                String[] splitWordl = wordl.split(" ");
                if (splitWordl[1].startsWith(filterField.getText())) {
                    tmpWordList.add(wordl);
                }
            }
            Platform.runLater(()->wordList.setItems(FXCollections.observableArrayList(tmpWordList)));
            Platform.runLater(() -> wordList.getItems().sort(comparator));
        }else{
            //Platform.runLater(() -> wordList.getItems().add(word));
            //Platform.runLater(() -> wordList.getItems().sort(comparator));
            allWords.add(word);
            Platform.runLater(()->wordList.setItems(FXCollections.observableArrayList(allWords)));
            Platform.runLater(() -> wordList.getItems().sort(comparator));

        }
    }
    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}