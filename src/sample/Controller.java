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
import sample.WordTranslate.WordTranslate;
import sample.WordTranslate.displayDetails;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;


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

        wordDetails.setItems(displayDetails.get(word, myWordTranslate.getDetails(word)));
    }

    public void textChangeInput(final String word) {

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
    }

    @FXML
    TextField delE;

    public void deleteWord() {

        this.myWordTranslate.deleteWord(delE.textProperty().getValue());
        if (delE.textProperty().getValue().equals(this.chosenWord)) {
            wordDetails.setItems(displayDetails.get(this.chosenWord,this.myWordTranslate.getDetails(this.chosenWord)));
        }
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
