package sample.WordTranslate;

import java.util.ArrayList;

public class WordTranslate {

    private DBHandler myDBHandler;
    private HintManager myHintManager;

    public WordTranslate() {
        this.myDBHandler = new DBHandler();


        this.myHintManager  = new HintManager();

        this.myDBHandler.initHint(this.myHintManager);

        this.myDBHandler.release();
    }

    public ArrayList<String> getHints(String word) {
        return this.myHintManager.getHints(word);
    }

}
