package sample.models;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class DictionaryCommandLine {
    DictionaryManagement dictionaryManagement = new DictionaryManagement();
    Dictionary dictionary;
    Scanner input = new Scanner(System.in);
    public DictionaryCommandLine(){
        dictionary = dictionaryManagement.getDictionary();
    }

    public void setDictionaryManagement(DictionaryManagement dictionaryManagement) {
        this.dictionaryManagement = dictionaryManagement;
    }

    public void showAllWords(){
        int length = dictionary.getWord_list().size();
        System.out.println("NO\t"+ "|English\t\t"+ "|Vietnamese\n");
        for (int i = 0;i < length;i++){
            Word word = dictionary.getWord_list().get(i);
            System.out.println(i + "\t  |"+ word.getWord_target());
        }
    }
    public void dictionaryBasic() throws IOException {
        dictionaryManagement.insertFromFile();
        showAllWords();
        System.out.println("Input a word: ");
        String inputStr = input.nextLine();
        dictionaryManagement.searchWord(inputStr);
    }
    public void dictionaryAdvanced() throws IOException {
        dictionaryManagement.insertFromFile();
        showAllWords();
        System.out.println("Input a word:");
        String inputStr = input.nextLine();
        ArrayList<Word> words =  dictionaryManagement.advancedSearchWord(inputStr);
        System.out.println(words.size());
        for (Word a: words){
            System.out.println(a.getWord_target());
        }
    }
    public void dictionaryUDFunction(){
        System.out.println("You want to update <Enter 1>   or   delete <Enter 2>");
        int option = input.nextInt();
        input.nextLine();
        switch (option){
            case  1:
                dictionaryManagement.alterWord();
            break;
            case 2:
                dictionaryManagement.deleteWord();
            break;
        }
        showAllWords();
    }
    public static void main(String[] args) throws IOException {
        DictionaryCommandLine dictionaryCommandLine = new DictionaryCommandLine();
        dictionaryCommandLine.dictionaryAdvanced();
    }
}
