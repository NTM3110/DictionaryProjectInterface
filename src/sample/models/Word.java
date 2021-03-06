package sample.models;


import java.util.ArrayList;
import java.util.HashMap;

public class Word {
    private String word_target;
    private HashMap<String,ArrayList<Description>> meaning = new HashMap<String,ArrayList<Description>>();
    private HashMap<String , String > phonetics = new HashMap<String, String>();

    public void displayWord(){
        System.out.println("The word is: "+ word_target);
        for (String a: meaning.keySet()){
            System.out.println(a);
            for (Description b : meaning.get(a)){
                System.out.println(b.getDefinition());
                for (String c: b.getExample()){
                    System.out.println(c);
                }
            }
        }
    }
    public HashMap<String, ArrayList<Description>> getMeaning() {
        return meaning;
    }

    public void setPhonetics(String text , String audioURL) {
        this.phonetics.put(text, audioURL);
    }

    public HashMap<String, String> getPhonetics() {
        return phonetics;
    }

    public void addMeaning(String type, ArrayList<Description> description) {
        this.meaning.put(type,description);
    }
    public String getWord_target(){
        return this.word_target;
    }

    public void setWord_target(String word_target) {
        this.word_target = word_target;
    }
}
