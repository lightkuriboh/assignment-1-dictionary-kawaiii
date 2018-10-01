package com.dictionary.develop.Trie;
import java.util.ArrayList;

public class Trie {
    private final int limitNumWordEachNode = 20;
    private TrieNode rootNode;

    public Trie() {
        this.rootNode = new TrieNode();
    }

    public void insertWord(final int index, String word) {
        TrieNode curNode = this.rootNode;
        for (int i = 0; i < word.length(); i++) {
            char curChar = word.charAt(i);
            if ('a' <= curChar && curChar <= 'z') {
                TrieNode nextNode = curNode.getNextNode(curChar);
                if (nextNode == null) {
                    nextNode = curNode.newNode(index, this.limitNumWordEachNode, curChar);
                }
                curNode = nextNode;
            }
        }
    }

    public ArrayList<Integer> searchWord(String word) {
        TrieNode curNode = this.rootNode;
        for (int i = 0; i < word.length(); i++) {
            char curChar = word.charAt(i);
            if ('a' <= curChar && curChar <= 'z') {
                TrieNode nextNode = curNode.getNextNode(curChar);
                if (nextNode != null) {
                    curNode = nextNode;
                } else {
                    break;
                }
            }
        }
        return curNode.getIndexList();
    }
}
