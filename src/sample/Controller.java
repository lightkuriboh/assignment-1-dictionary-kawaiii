package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import javafx.scene.input.InputEvent;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.InputMethodTextRun;
import sample.DocumentTranslate.DocumentTranslate;
import sample.WordTranslate.WordTranslate;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.searchBar.textProperty().addListener((obs, oldText, newText) -> {
            this.textChangeInput(newText);
        });
    }

    @FXML
    private TextArea searchDoc;

    @FXML
    private TextArea translatedText;

    private DocumentTranslate myDocumentTranslate = new DocumentTranslate();

    public void submitDocument(ActionEvent event) {
        String document = this.searchDoc.getText();
        String translated = this.myDocumentTranslate.translateIt("en", "vi", document);
        this.translatedText.textProperty().setValue(translated);
    }


    @FXML
    private ListView Hints;

    @FXML
    private TextField searchBar;

    @FXML
    private Label wordDetails;

    private WordTranslate myWordTranslate = new WordTranslate();

    public void submitSearchWord(ActionEvent event) {
        String word = this.searchBar.getText();
        System.out.println(word);
        this.wordDetails.textProperty().setValue(word);
    }

    public void textChangeInput(String word) {

    }

}
