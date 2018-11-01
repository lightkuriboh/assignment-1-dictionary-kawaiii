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
import sample.DocumentTranslate.DocumentTranslator2;
import sample.WordTranslate.WordTranslate;
import sample.WordTranslate.displayDetails;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import sample.WordTranslate.notice;

public class Controller implements Initializable {

    public String chosenWord = new String();

    Speaker textSpeaker;
    @FXML
    private TextArea HowToUse;

    String curText = new String("");
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.searchBar.textProperty().addListener((obs, oldText, newText) -> {
            this.textChangeInput(newText);
            curText=newText;
        });
        this.searchDoc.textProperty().addListener((obs, oldText, newText) -> { this.textChangeDoc(newText); });
        this.myDocumentTranslate  = new DocumentTranslator2();
        this.myWordTranslate  = new WordTranslate();
        this.HowToUse.textProperty().setValue(notice.howToUse);
        this.textSpeaker = new Speaker();

    }

    @FXML
    private TextArea searchDoc;

    private void textChangeDoc(String newText) {
        String document = this.searchDoc.getText();
        String translated = this.myDocumentTranslate.translateIt("en", "vi", document);
        this.translatedText.textProperty().setValue(translated);
    }

    @FXML
    private TextArea translatedText;

    private DocumentTranslator2 myDocumentTranslate;

    public void submitDocument(ActionEvent event) {
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

        wordDetails.setItems(displayDetails.get(word, myWordTranslate.getDetails(word)));
    }

    public void textChangeInput(final String word) {
        if (word.length()==0) return;
        ObservableList<Label> listHints = FXCollections.observableArrayList();

        ArrayList<String> myHints = this.myWordTranslate.getHints(word);
        for (String hint: myHints) {
            Label myLabel = new Label(hint);
            myLabel.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    wordDetails.setItems(displayDetails.get(hint, myWordTranslate.getDetails(hint)));
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
        this.myWordTranslate.insertWord(this.getAddE(), this.getAddV(), this.getAddP());
        System.out.println(String.format("English: %s\nVietnamese: %s\nPronunciation: %s\n", this.getAddE(), this.getAddV(), this.getAddP()));
        textChangeInput(this.curText);
    }

    @FXML
    TextField delE;

    public void deleteWord() {

        this.myWordTranslate.deleteWord(delE.textProperty().getValue());
        if (delE.textProperty().getValue().equals(this.chosenWord)) {
            wordDetails.setItems(displayDetails.get(this.chosenWord,this.myWordTranslate.getDetails(this.chosenWord)));
        }
        textChangeInput(this.curText);
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
        this.myWordTranslate.updateWord(this.getUpdE(), this.getUpdV(), this.getUpdP());
        System.out.println(String.format("English: %s\nVietnamese: %s\nPronunciation: %s\n", this.getUpdE(), this.getUpdV(), this.getUpdP()));
        if (updE.textProperty().getValue().equals(this.chosenWord)) {
            wordDetails.setItems(displayDetails.get(this.chosenWord,this.myWordTranslate.getDetails(this.chosenWord)));
        }
    }

}
