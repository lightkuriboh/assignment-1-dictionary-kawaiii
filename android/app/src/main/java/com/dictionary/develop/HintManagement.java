package com.dictionary.develop;

import java.util.ArrayList;
import com.dictionary.develop.Trie.*;

public class HintManagement {
    private ArrayList<String> myListWord = new ArrayList<>();

    private Trie myTrie;

    public HintManagement() {
        this.myTrie = new Trie();
    }

    public void addMoreWord(String word) {
        this.myListWord.add(word);
    }

    public void initData() {
        for (int i = 0; i < this.myListWord.size(); i++) {
            String word = this.myListWord.get(i);
            this.myTrie.insertWord(i, word);
        }
    }

    public String getHint(String word) {
        ArrayList<Integer> getList = this.myTrie.searchWord(word);
        String ans = "";
        ans += "[";
        for (int i = 0; i < getList.size(); i++) {
            String myWord = this.myListWord.get(getList.get(i));
            String cur = "{key:'";
            cur += myWord;
            cur += "'}";
            if (i != getList.size() - 1) {
                cur += ",";
            }
            ans += cur;
        }
        ans += "]";
        return ans;
    }

}
