package sample.WordTranslate.Word;

public class aWord {
    private String english;
    private String vietnamese;
    private String pronunciation;
    private Integer index;

    public aWord() {};

    public aWord(Integer _index, String _english, String _vietnamese, String _pronunciation) {
        setEnglish(_english);
        setVietnamese(_vietnamese);
        setIndex(_index);
        setPronunciation(_pronunciation);
    }

    public void setWord(Integer _index, String _english, String _vietnamese, String _pronunciation) {
        setEnglish(_english);
        setVietnamese(_vietnamese);
        setIndex(_index);
        setPronunciation(_pronunciation);
    }

    public String getEnglish() {
        return english;
    }

    public void setEnglish(String english) {
        this.english = english;
    }

    public String getVietnamese() {
        return vietnamese;
    }

    public void setVietnamese(String vietnamese) {
        this.vietnamese = vietnamese;
    }

    public String getPronunciation() {
        return pronunciation;
    }

    public void setPronunciation(String pronunciation) {
        this.pronunciation = pronunciation;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }
}
