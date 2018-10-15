package sample.WordTranslate;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.paint.Color;

public class displayDetails {
    public static ObservableList<Label> get(String originalWord, String word) {
        ObservableList<Label> listDetails = FXCollections.observableArrayList();
        String noteDetail = new String();
//        String noteDetail = new String(originalWord + "\n");
        Integer cur = 0;
        Integer i = -1;
        boolean nowExample = false;
        String curWord = "";
        char chr = 'm';
        char lstchr;
        while (i < word.length()-1) {
            i++;
            lstchr = chr;
            chr = word.charAt(i);
            if (chr == '*' && lstchr == '\n') {
                cur = 1;
                nowExample = false;
                curWord = "";
                continue;
            }
            if (chr == '-' && lstchr == '\n') {
                cur = 2;
                curWord = "";
                nowExample = false;
                continue;
            }
            if (chr == '=' && lstchr == '\n') {
                cur = 3;
                curWord = "";
                continue;
            }
            if (chr == '@' && lstchr == '\n') {
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


        noteDetail += "\n";
        Label noteLabel = new Label(noteDetail);
        noteLabel.setTextFill(Color.PURPLE);
        noteLabel.setStyle("-fx-font-size: 15pt;");
        listDetails.add(0, noteLabel);
        return listDetails;
    }

}
