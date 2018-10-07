package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import sample.DocumentTranslate.DocumentTranslate;
import sample.WordTranslate.WordTranslate;

import java.net.URL;
import java.util.ArrayList;
import java.util.Observable;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.searchBar.textProperty().addListener((obs, oldText, newText) -> {
            this.textChangeInput(newText);
        });

        this.myDocumentTranslate  = new DocumentTranslate();
        this.myWordTranslate  = new WordTranslate();
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
    }


    @FXML
    private ListView<String> Hints;

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

        ObservableList<String> listHints = FXCollections.observableArrayList();

        listHints.addAll(this.myWordTranslate.getHints(word));

        this.Hints.setItems(listHints);
    }

}
