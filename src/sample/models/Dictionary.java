package sample.models;

import java.util.ArrayList;

public class Dictionary {
    //Attributes
    private ArrayList<Word> word_list = new ArrayList<Word>();


    //Function
    public ArrayList<Word> getWord_list() {
        return word_list;
    }

    public void setWork_list(ArrayList<Word> work_list) {
        this.word_list = work_list;
    }
    public void addWord(Word word){
        word_list.add(word);
    }
}
