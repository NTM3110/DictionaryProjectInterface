package sample.models;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import sample.Connection.JSONDecoder;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class CreateJSONFile {


    public static void main(String[] args) throws IOException {
        DictionaryManagement dictionaryManagement = new DictionaryManagement();
        dictionaryManagement.insertFromFile();
        JSONArray json = new JSONArray();
        for (Word word : dictionaryManagement.getDictionary().getWord_list()) {
            JSONObject jsonWord = new JSONObject();
            jsonWord.put("word", word.getWord_target());
            JSONArray jsonMeanings = new JSONArray();
            for (String type : word.getMeaning().keySet()) {
                JSONObject meaningByType = new JSONObject();

                if (!type.equals("noType") && !type.equals("noEx")) {
                    meaningByType.put("partOfSpeech", type);
                }
                else if (type.equals("noEx")) meaningByType.put( "partOfSpeech","noEx");
                else if (type.equals("noType")) meaningByType.put( "partOfSpeech","noType");

                JSONArray jsonDefinitions = new JSONArray();
                for (Description des : word.getMeaning().get(type)) {
                    JSONObject jsonDefinition = new JSONObject();
                    jsonDefinition.put("definition", des.getDefinition());
                    JSONArray jsonExamples = new JSONArray();
                    for (String example : des.getExample()) {
                        jsonExamples.add(example);
                    }
                    jsonDefinition.put("examples", jsonExamples);
                    jsonDefinitions.add(jsonDefinition);
                }
                meaningByType.put("definitions", jsonDefinitions);
                jsonMeanings.add(meaningByType);
            }
//            for (Object a: hm.keySet()){
//                System.out.println(a.toString());
//            }
            jsonWord.put("meanings", jsonMeanings);
            json.add(jsonWord);
        }
        try(FileWriter file = new FileWriter("emps.json")){
            System.out.println("Write file");
            file.write(json.toJSONString());
            file.flush();
        }
        catch(IOException e) {e.printStackTrace();}

//        FileReader fileReader = new FileReader("emps.json");
//        BufferedReader bf = new BufferedReader(fileReader);
//        JSONDecoder jsonDecoder = new JSONDecoder();
//        String wordStr = bf.readLine();
//       // System.out.println(wordStr);
//        Word wordInput =  jsonDecoder.Decoder(wordStr);
//        wordInput.displayWord();
    }
}
