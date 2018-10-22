package sample.WordTranslate;
import sample.WordTranslate.Trie.Trie;
import java.util.ArrayList;

public class HintManager {

    private Trie myTrie;
    private ArrayList<Boolean> myListCheck = new ArrayList<>();
    public HintManager () {
        this.myTrie = new Trie();
    }

    private ArrayList<String> myListWord = new ArrayList<>();

    public void addMoreWord(String word) {
        Integer st=-1;
        for(int i=0;i<myListWord.size();i++) {
            if (myListCheck.get(i)==false && myListWord.get(i).equals(word)) {
                st = i;
                break;
            }
        }
        if (st==-1) {
            this.myListWord.add(word);
            this.myListCheck.add(true);
            myTrie.insertWord(myListWord.size()-1,word);
        } else{
            myListCheck.set(st,true);
            myTrie.insertWord(st,word);
        }
    }

    public void deleteWord(String word) {
        Integer st=-1;
        for(int i=0;i<myListWord.size();i++) {
            if (myListCheck.get(i)==true && myListWord.get(i).equals(word)) {
                st = i;
                break;
            }
        }
        if (st==-1) return;
        myListCheck.set(st,false);
        myTrie.delete(st,word);
    }

    public ArrayList<String> getHints(String word) {

        ArrayList<String> ans = new ArrayList<>();

        ArrayList<Integer> getIndex = this.myTrie.searchWord(word);
        for (int index: getIndex) {
            ans.add(this.myListWord.get(index));
        }

        return ans;
    }

}
