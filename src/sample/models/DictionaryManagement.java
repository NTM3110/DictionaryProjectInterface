package sample.models;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DictionaryManagement {
    Scanner input = new Scanner(System.in);
    private Dictionary dictionary = new Dictionary();

    public Dictionary getDictionary() {
        return dictionary;
    }

    public void setDictionary(Dictionary dictionary) {
        this.dictionary = dictionary;
    }
    private boolean checkInput(String inputStr, String outStr){
        if (inputStr == null || outStr == null ){
            System.out.println("please input double words");
            return false;
        }
        else return true;
    }
    public void dictionaryLookUp(){
        System.out.println("Please enter the word that you want to look up:");
        String inputStr = input.next();
        input.nextLine();
        int keep = 0;
        for(Word a: dictionary.getWord_list()){
            if(inputStr.equals(a.getWord_target())){
              System.out.println("Translated word is:"+ a.getWord_explain());
              break;
            }
        }
    }
    public void  insertFromCommandLine(){
        System.out.println("Input the number of words: ");
        int number = input.nextInt();
        input.nextLine();
        for(int i = 0;i <number;i++) {
            Word word = new Word();
            String inputStr = null;
            String outputStr =  null;
            while (checkInput(inputStr,outputStr)==false) {
                inputStr = input.nextLine();
                outputStr = input.nextLine();
            }
            word.setWord_target(inputStr);
            word.setWord_explain(outputStr);
            dictionary.addWord(word);
        }
    }
    public int searchWord(){
        System.out.println("Enter the word that you want:");
        String inputStr = input.nextLine();
        int i=0;
        int keep=0;
        boolean checkOccur = false;
        for (Word a: dictionary.getWord_list()){
            if (a.getWord_target().equals(inputStr)){
                System.out .println("The pair of word is:"+ a.getWord_target()+"-"+a.getWord_explain());
                keep=i;
                checkOccur = true;
                break;
            }
            i++;
        }
        if (checkOccur ==true ) return keep;
        else  return dictionary.getWord_list().size();
    }
    public ArrayList<Word> advancedSearchWord(String inputStr){
        Pattern pattern
                = Pattern.compile(inputStr);
        int i=0;
        ArrayList<Word> keep= new ArrayList<Word>();
        for (Word a: dictionary.getWord_list()){
            Matcher matcher = pattern.matcher(a.getWord_target());
            if (matcher.find()==true && inputStr.charAt(0) == a.getWord_target().charAt(0)){
                System.out .println("The pair of word is:"+ a.getWord_target()+"-"+a.getWord_explain());
                keep.add(a);
            }
            i++;
        }
        return keep;
    }
    public void deleteWord(){
        System.out.println("You want to delete ?");
        int index=searchWord();
        if(index <dictionary.getWord_list().size())
            dictionary.getWord_list().remove(index);
    }
    public void alterWord(){
        int index= searchWord();
        System.out.println(" Are you want to change the target word <Enter 1>   or   the  explain word <Enter 2> ");
        int option = input.nextInt();
        input.nextLine();
        switch (option){
            case  1:
                System.out.println("Enter the target word that want to change to ");
                String inputStr = input.nextLine();
                dictionary.getWord_list().get(index).setWord_target(inputStr);
            break;
            case 2:
                System.out.println("Enter the explain word that want to change to ");
                String outputStr = input.nextLine();
                dictionary.getWord_list().get(index).setWord_explain(outputStr);
            break;
        }
    }
    public void insertFromFile() throws IOException {
        String path = "C:\\Users\\mdmt3\\OneDrive\\Documents\\RMIT\\Sem2 2021\\Objected Programming Subject\\DictionaryProject\\src\\text\\DemoSrcFile.txt";
        File file = new File(path);
        Scanner sc = new Scanner(file, StandardCharsets.UTF_8);
        while (sc.hasNext()){
            String st = sc.nextLine();
            //Split by -
            String[] s = st.split(",");
            System.out.println(s[0]+"-"+s[1]);
            String inputStr= s[0];
            String outputStr = s[1];
            Word word = new Word();
            word.setWord_target(inputStr);
            word.setWord_explain(outputStr);
            dictionary.addWord(word);
        }
    }
}
