package sample.WordTranslate;
import sample.WordTranslate.Trie.Trie;
import java.util.ArrayList;

public class HintManager {
    private Trie myTrie = new Trie();

    private ArrayList<String> myListWord = new ArrayList<>();

    public void addMoreWord(String word) {
        this.myListWord.add(word);
    }

    public void initData() {
        for (int i = 0; i < this.myListWord.size(); i++) {
            String word = this.myListWord.get(i);
            this.myTrie.insertWord(i, word);
        }
    }

}
