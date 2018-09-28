package com.dictionary.develop.Trie;
import java.util.ArrayList;

public class TrieNode {
    private ArrayList<Integer> indexList;

    public ArrayList<Integer> getIndexList() {
        return indexList;
    }

    public void setIndexList(ArrayList<Integer> indexList) {
        this.indexList = indexList;
    }

    public TrieNode() {
        this.indexList = new ArrayList<>();
    }

    public void addIndex(int _index) {
        Integer index = new Integer(_index);
        this.indexList.add(index);
    }
}
