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
import sample.DocumentTranslate.DocumentTranslate;
import sample.WordTranslate.WordTranslate;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import sample.Speaker;

public class Controller implements Initializable {

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
        this.textSpeaker.speak(document);
    }


    @FXML
    private ListView<Label> Hints;

    @FXML
    private TextField searchBar;

    @FXML
    private Label wordDetails;

    private WordTranslate myWordTranslate;

    public void submitSearchWord(ActionEvent event) {
        String word = this.searchBar.getText();
        System.out.println(word);
        this.wordDetails.textProperty().setValue(word);
    }

    public void textChangeInput(final String word) {

        ObservableList<Label> listHints = FXCollections.observableArrayList();

        ArrayList<String> myHints = this.myWordTranslate.getHints(word);
        for (String hint: myHints) {
            Label myLabel = new Label(hint);
            myLabel.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    wordDetails.textProperty().setValue(myWordTranslate.getDetails(hint));
                    //textSpeaker.speak(hint);
                }
            });
            myLabel.setMaxWidth(Double.MAX_VALUE);
            myLabel.setAlignment(Pos.CENTER);
            listHints.add(myLabel);
        }
        this.Hints.setItems(listHints);
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
    }


}
