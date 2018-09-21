import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class DictionaryManagement {
    private Dictionary myDictionary;

    public DictionaryManagement() {
        this.myDictionary = new Dictionary();
    }

    public ArrayList<Word> getDictionaryData() {
        return this.myDictionary.getListWord();
    }

    public void insertFromCommandLine() {
        System.out.println("Enter number of words");
        int n = 10;
        Scanner myScanner = new Scanner(System.in);
        n = myScanner.nextInt();
        myScanner.nextLine();
        for (int i = 0; i < n; i++) {

            System.out.println("Input english word: ");
            String english = "";
            english = myScanner.nextLine();

            System.out.println("Input vietnamese meaning: ");
            String vietnamese = "";
            vietnamese = myScanner.nextLine();

            Word newWord = new Word(english, vietnamese);
            this.myDictionary.addNewWord(newWord);

        }
    }

    public void insertFromFile () {
        String line = null;
        try {
            FileReader fileReader = new FileReader("src/dictionaries.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
                String engrisk = "";
                String vietnamese = "";
                String [] read_word = line.split("\t");
                engrisk = read_word[0];
                vietnamese = read_word[1];
                Word word = new Word(engrisk, vietnamese);
                this.myDictionary.addNewWord(word);
            }
            bufferedReader.close();
        } catch (FileNotFoundException ex) {
            System.out.println("File not found!");
        } catch (IOException ex) {
            System.out.println("Unexpected error reading file");
        }
    }

    public String dictionaryLookup(String english_word) {
        String ans = " !!! word not found";
        ArrayList<Word> myListData = this.getDictionaryData();
        for (Word word: myListData) {
            if (word.getWord_target().equals(english_word)) {
                return word.getWord_explain();
            }
        }
        return ans;
    }
}
