package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import sample.WordTranslate.DBHandler;
import sample.WordTranslate.Trie.Trie;
import sample.WordTranslate.Trie.TrieNode;

import java.sql.SQLException;
import java.util.ArrayList;

public class Main {

    /*@Override
    public void start(Stage primaryStage) throws Exception{
        try {
            Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
            primaryStage.setTitle("Hello World");
            primaryStage.setScene(new Scene(root, 960, 640));
            primaryStage.show();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }*/


    public static void main(String[] args) throws SQLException {
        Trie myTrie = new Trie();
        DBHandler myDBHandler = new DBHandler();

        myDBHandler.init(myTrie);
        ArrayList<Integer> result= myTrie.searchWord("a");
        for(Integer i:result) {
            System.out.println(myDBHandler.getWord(i).getEnglish());
        }
        //launch(args);
    }
}
