import java.util.ArrayList;

public class Dictionary {

    public Dictionary() {

    }

    private ArrayList<Word> listWord = new ArrayList<Word>();

    public ArrayList<Word> getListWord() {
        return this.listWord;
    }

    public void addNewWord (Word word) {
        this.listWord.add(word);
    }
}
