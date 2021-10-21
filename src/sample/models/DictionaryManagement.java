package sample.models;

import sample.Connection.InternetConnection;
import sample.Connection.JSONDecoder;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
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
    public void displayWord(){
        for (Word word : dictionary.getWord_list()) {
            System.out.println(word.getWord_target());
        }
    }
    public void setDictionary(Dictionary dictionary) {
        this.dictionary = dictionary;
    }
    private boolean checkInput(String inputStr){
        if (inputStr == null){
            System.out.println("please input double words");
            return false;
        }
        else return true;
    }
    public void  insertFromCommandLine(){
        System.out.println("Input the number of words: ");
        int number = input.nextInt();
        input.nextLine();
        for(int i = 0;i <number;i++) {
            Word word = new Word();
            String inputStr = null;
            while (checkInput(inputStr)==false) {
                inputStr = input.nextLine();
            }
            word.setWord_target(inputStr);
            dictionary.addWord(word);
        }
    }
    public Word searchWord(String inputStr){
        Word word  = new Word();
        for (Word a: dictionary.getWord_list()){
            if (a.getWord_target().equals(inputStr)) {
                System.out.println(a.getWord_target());
                word = a;
            }
        }
        return word;
    }
    public ArrayList<Word> advancedSearchWord(String inputStr) throws  NullPointerException{
        Pattern pattern
                = Pattern.compile(inputStr);
        ArrayList<Word> keep= new ArrayList<>();
        int i =0;
        for (Word a: dictionary.getWord_list()){
            if (a.getWord_target() ==null){
                System.out.println("There is null word");
            }
            Matcher matcher= pattern.matcher(a.getWord_target());
            if (i<10) {
                if (a.getWord_target().length() >= inputStr.length()) {
                    String dub = a.getWord_target().substring(0, inputStr.length());
                    if (matcher.find() == true && inputStr.equals(dub)) {
//                        System.out.println(a.getWord_target());
                        keep.add(a);
                        i++;
                    }
                }
            }
            else if (i==20) {
                System.out.println("Out of range!");
                break;
            }
        }
        return keep;
    }
    public void deleteWord(){
        System.out.println("You want to delete ?");
//       // int index=searchWord();
//        if(index <dictionary.getWord_list().size())
//            dictionary.getWord_list().remove(index);
    }
    public void alterWord(){
        input.nextLine();
        System.out.println("Enter the target word that want to change to ");
        String inputStr = input.nextLine();
        //dictionary.getWord_list().get(index).setWord_target(inputStr);
    }
    public void insertFromFile() throws IOException {
        String path = "C:\\Users\\mdmt3\\OneDrive\\Documents\\RMIT\\Sem2 2021\\Objected Programming Subject\\DictionaryProjectInterface\\src\\sample\\dictionarySrc\\E_V.txt";
        File file = new File(path);
        Scanner sc = new Scanner(file, StandardCharsets.UTF_8);
        double startTime = System.currentTimeMillis();
        while (sc.hasNext()){
            Word word = new Word();
            String st = sc.nextLine();
            ExtractFileSrc es = new ExtractFileSrc();
            word = es.getWordSrc(st);
            dictionary.addWord(word);
        }
        double endTime = System.currentTimeMillis();
        System.out.println("Duration: "+(endTime-startTime));
        sc.close();
    }
    public  void insertFromFileJSON() throws  IOException{
        FileReader fileReader = new FileReader("emps.json");
        BufferedReader bf = new BufferedReader(fileReader);
        JSONDecoder jsonDecoder = new JSONDecoder();
        String wordStr = bf.readLine();
       // System.out.println(wordStr);
        dictionary.setWork_list(jsonDecoder.Decoder(wordStr));
    }
}
