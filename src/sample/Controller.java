package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.*;

import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import sample.DocumentTranslate.DocumentTranslate;
import sample.WordTranslate.DBHandler;
import sample.WordTranslate.WordTranslate;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import sample.Speaker;

public class Controller implements Initializable {

    public String chosenWord = new String();

    Speaker textSpeaker;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.searchBar.textProperty().addListener((obs, oldText, newText) -> {
            this.textChangeInput(newText);
        });
        this.myDocumentTranslate  = new DocumentTranslate();
        this.myWordTranslate  = new WordTranslate();

        this.textSpeaker = new Speaker();

    }

    @FXML
    private TextArea searchDoc;

    @FXML
    private TextArea translatedText;

    private DocumentTranslate myDocumentTranslate;

    public void submitDocument(ActionEvent event) {
        String document = this.searchDoc.getText();
        String translated = this.myDocumentTranslate.translateIt("en", "vi", document);
        this.translatedText.textProperty().setValue(translated);
//        this.textSpeaker.speak(document);
    }


    @FXML
    private ListView<Label> Hints;

    @FXML
    private TextField searchBar;

    @FXML
    private ListView wordDetails;

    @FXML
    private WordTranslate myWordTranslate;

    public void submitSearchWord(ActionEvent event) {

        String word = this.searchBar.getText();

        this.chosenWord = word;

        this.displayDetails(word);
    }

    public void textChangeInput(final String word) {

        ObservableList<Label> listHints = FXCollections.observableArrayList();

        ArrayList<String> myHints = this.myWordTranslate.getHints(word);
        for (String hint: myHints) {
            Label myLabel = new Label(hint);
            myLabel.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    displayDetails(hint);
                    chosenWord = hint;
//                    searchBar.textProperty().setValue(hint);
                }
            });
            myLabel.setMaxWidth(Double.MAX_VALUE);
            myLabel.setAlignment(Pos.CENTER);
            listHints.add(myLabel);
        }
        this.Hints.setItems(listHints);
    }

    public void displayDetails(String originalWord) {
        ObservableList<Label> listDetails = FXCollections.observableArrayList();
        String word = myWordTranslate.getDetails(originalWord);
        String noteDetail = new String();
//        String noteDetail = new String(originalWord + "\n");
        Integer cur = 0;
        Integer i = -1;
        boolean nowExample = false;
        String curWord = "";
        while (i < word.length()-1) {
            i++;
            char chr = word.charAt(i);
            if (chr == '*') {
                cur = 1;
                nowExample = false;
                curWord = "";
                continue;
            }
            if (chr == '-') {
                cur = 2;
                curWord = "";
                nowExample = false;
                continue;
            }
            if (chr == '=') {
                cur = 3;
                curWord = "";
                continue;
            }
            if (chr == '@') {
                cur = 4;
                curWord = "Dạng khác: ";
                continue;
            }
            if (cur == 0) {
                noteDetail = noteDetail + chr;
                continue;
            }
            if (chr != '\n') {
                curWord = curWord + chr;
                continue;
            }
            if (cur == 3) {
                if (!nowExample) {
                    nowExample = true;
                    Label example = new Label("Example: ");
                    example.setMaxWidth(Double.MAX_VALUE);
                    example.setStyle("-fx-font-size: 15pt;");
                    listDetails.add(example);
                }
                curWord ="\t" + curWord;
            }
            Label myLabel = new Label(curWord);
            if (cur == 1) {
                myLabel.setStyle("-fx-font-size: 15pt; -fx-font-weight: bold;");
                myLabel.setTextFill(Color.RED);
            }
            if (cur == 2) {
                myLabel.setStyle("-fx-font-size: 12pt; ");
                myLabel.setTextFill(Color.BLUE);
            }
            if (cur == 3) {
                myLabel.setStyle("-fx-font-size: 12pt; -fx-font-style: italic; ");
                myLabel.setTextFill(Color.GREEN);
            }
            if (cur == 4) {
                myLabel.setStyle("-fx-underline: true; -fx-font-size: 12pt; -fx-font-weight: bold; ");
            }
            listDetails.add(myLabel);
        }

        this.wordDetails.setItems(listDetails);

        noteDetail += "\n";
        Label noteLabel = new Label(noteDetail);
        noteLabel.setTextFill(Color.PURPLE);
        noteLabel.setStyle("-fx-font-size: 15pt;");
        listDetails.add(0, noteLabel);

    }

    /**
     * Speaker will read the chosen word
     */
    public void readWord() {
        this.textSpeaker.speak(this.chosenWord);
    }

    public void speakEnglish() {
        this.textSpeaker.speak(this.searchDoc.textProperty().getValue());
    }


    /**
     * add more word
     */
    @FXML
    TextField addE;
    @FXML
    TextField addV;
    @FXML
    TextField addP;

    /**
     *
     * @return content of addE
     */
    public String getAddE() {
        return addE.textProperty().getValue();
    }

    /**
     *
     * @return content of addV
     */
    public String getAddV() {
        return addV.textProperty().getValue();
    }

    /**
     *
     * @return content of addP
     */
    public String getAddP() {
        return addP.textProperty().getValue();
    }

    public void addNewWord() {
        //this.myWordTranslate.insertWord(this.getAddE(), this.getAddV(), this.getAddP());
        System.out.println(String.format("English: %s\nVietnamese: %s\nPronunciation: %s\n", this.getAddE(), this.getAddV(), this.getAddP()));
    }

    @FXML
    TextField delE;

    public void deleteWord() {
        //this.myWordTranslate.deleteWord(delE.textProperty().getValue());
        System.out.println(String.format("English: %s\n", delE.textProperty().getValue()));
    }

    @FXML
    TextField updE;
    @FXML
    TextArea updV;
    @FXML
    TextField updP;

    /**
     *
     * @return content of addE
     */
    private String getUpdE() {
        return updE.textProperty().getValue();
    }

    /**
     *
     * @return content of addV
     */
    public String getUpdV() {
        return updV.textProperty().getValue();
    }

    /**
     *
     * @return content of addP
     */
    public String getUpdP() {
        return updP.textProperty().getValue();
    }

    public void updateWord() {
        //this.myWordTranslate.updateWord(this.getUpdE(), this.getUpdV(), this.getUpdP());
        System.out.println(String.format("English: %s\nVietnamese: %s\nPronunciation: %s\n", this.getUpdE(), this.getUpdV(), this.getUpdP()));
    }

}
