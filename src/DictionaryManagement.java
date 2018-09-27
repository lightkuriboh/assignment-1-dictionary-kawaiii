import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.PrintWriter;

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
                System.out.println(read_word[0]);
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

    public void dictionaryExportToFile() {
        PrintWriter writer = null;
        try {
            writer = new PrintWriter("baongocratxinh.txt", StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        writer.println("No   | English                         | Vietnamese");
        ArrayList<Word> myList = this.myDictionary.getListWord();
        for (int i = 0; i < myList.size(); i++) {
            Word word = myList.get(i);
            writer.print(i);
            writer.println("    | " + word.getWord_target() + "                         | " + word.getWord_explain());
        }
        writer.close();

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

    public void deleteWord(String Del) {
        ArrayList<Word> curList=this.myDictionary.getListWord();
        for(int i=0;i<curList.size(); i++) {
            if (Del.equals(curList.get(i).getWord_target())) {
                curList.remove(i);
                break;
            }
        }
    }

    public ArrayList<Word> findPrefix(String curWord){
        ArrayList<Word> curList=this.myDictionary.getListWord();
        ArrayList<Word> res = new ArrayList<Word>();
        for(int i=0;i<curList.size(); i++) {
            if (curList.get(i).getWord_target().substring(0,curWord.length()).equals(curWord)) {
                res.add(curList.get(i));
            }
        }
        return res;
    }
}
