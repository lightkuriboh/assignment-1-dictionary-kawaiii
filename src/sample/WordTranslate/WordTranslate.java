package sample.WordTranslate;

import java.awt.*;
import java.util.ArrayList;

public class WordTranslate {

    private DBHandler myDBHandler;
    private HintManager myHintManager;

    private Label HowToUse = new Label();
    public WordTranslate() {
        this.myDBHandler = new DBHandler();


        this.myHintManager  = new HintManager();

        this.myDBHandler.initHint(this.myHintManager);

        //this.myDBHandler.release();
    }

    public ArrayList<String> getHints(String word) {
        return this.myHintManager.getHints(word);
    }

    public String getDetails(String word) {
        try {
            return this.myDBHandler.getStringWord(word);
        } catch (Exception ex ){
            return ex.getMessage();
        }
    }

    public void insertWord(String Eng, String Vie, String Pro) {
        this.myDBHandler.insertWord(Eng, Vie, Pro);
    }

    public void deleteWord(String word) {
        try {
            System.out.println(this.myDBHandler.deleteWord(word));
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void updateWord(String Eng, String Vie, String Pro) {
        try {
            this.myDBHandler.updateWord(Eng, Vie, Pro);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

}
