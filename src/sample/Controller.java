package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

import sample.DocumentTranslate.DocumentTranslate;
import sample.WordTranslate.WordTranslate;

public class Controller {
    @FXML
    private TextArea searchDoc;

    @FXML
    private Label translatedText;

    private DocumentTranslate myDocumentTranslate = new DocumentTranslate();
    private WordTranslate myWordTranslate = new WordTranslate();

    public void submitDocument(ActionEvent event) {
        String document = this.searchDoc.getText();
        String translated = this.myDocumentTranslate.translateIt("en", "vi", document);
        this.translatedText.textProperty().setValue(translated);
    }
}
