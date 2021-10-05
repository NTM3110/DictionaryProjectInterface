package sample.Connection;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

public class JSONDecoder {
    public  String Decoder(String data){
        String deData = "";
        try {
            Object obj = JSONValue.parse(data);

            JSONArray ja = (JSONArray) obj;
            JSONObject jo= (JSONObject) ja.get(0);
            deData=deData+="Word: - " +jo.get("word");

            JSONObject jo1 = (JSONObject) jo.get("meaning");
            if(jo1.get("noun")!=null) {
                JSONArray noun = (JSONArray) jo1.get("noun");
                deData += "\n\n - Noun";
                for (int i = 0; i < noun.size(); i++) {
                    JSONObject def = (JSONObject) noun.get(i);
                    deData += "\n Definition:-" + def.get("definition");
                    JSONArray synonyms = (JSONArray) def.get("synonyms");
                    if (synonyms.size() > 0) {
                        deData += "\nSynonym:";
                        for (int j = 0; j < synonyms.size(); j++) {
                            deData += "\n-\t" + synonyms.get(j);
                        }
                    }
                    JSONArray antonyms = (JSONArray) def.get("antonyms");
                    if (antonyms.size() > 0) {
                        deData += "\nAntonym:";
                        for (int k = 0; k < antonyms.size(); k++) {
                            deData += "\n-\t" + antonyms.get(k);
                        }
                    }
                }
            }
            if (jo1.get("adjective")!=null) {
                JSONArray adj = (JSONArray) jo1.get("adjective");
                deData += "\n\n -Adjective";
                for (int i = 0; i < adj.size(); i++) {
                    JSONObject def = (JSONObject) adj.get(i);
                    deData += "\n Definition:-" + def.get("definition");
                    JSONArray synonyms = (JSONArray) def.get("synonyms");
                    if (synonyms.size() > 0) {
                        deData += "\nSynonym:";
                        for (int j = 0; j < synonyms.size(); j++) {
                            deData += "\n-\t" + synonyms.get(j);
                        }
                    }
                    JSONArray antonyms = (JSONArray) def.get("antonyms");
                    if (synonyms.size() > 0) {
                        deData += "\nAntonym:";
                        for (int j = 0; j < antonyms.size(); j++) {
                            deData += "\n-\t" + antonyms.get(j);
                        }
                    }
                }
            }
            if (jo1.get("adverb")!=null) {
                JSONArray adv = (JSONArray) jo1.get("adverb");
                deData += "\n\n -Adverb";
                for (int i = 0; i < adv.size(); i++) {
                    JSONObject def = (JSONObject) adv.get(i);
                    deData += "\n Definition:-" + def.get("definition");
                    JSONArray synonyms = (JSONArray) def.get("synonyms");
                    if (synonyms.size() > 0) {
                        deData += "\nSynonym:";
                        for (int j = 0; j < synonyms.size(); j++) {
                            deData += "\n-\t" + synonyms.get(j);
                        }
                    }
                    JSONArray antonyms = (JSONArray) def.get("antonyms");
                    if (synonyms.size() > 0) {
                        deData += "\nAntonym:";
                        for (int j = 0; j < antonyms.size(); j++) {
                            deData += "\n-\t" + antonyms.get(j);
                        }
                    }
                }
            }
        }catch (Exception e){
            deData="Error";
            System.out.println(e.getMessage());
        }
        return deData;
    }
}
