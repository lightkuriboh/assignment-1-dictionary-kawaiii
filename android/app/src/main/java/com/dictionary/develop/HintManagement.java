package com.dictionary.develop;

import java.util.ArrayList;
import com.dictionary.develop.Trie.*;

public class HintManagement {
    private ArrayList<String> myListWord = new ArrayList<>();

    private Trie myTrie;

    public void addMoreWord(String word) {
        this.myListWord.add(word);
    }

    public void initTrie () {
        this.myTrie = new Trie();
    }

    public String getHint(String word) {
        return "";
    }

}
