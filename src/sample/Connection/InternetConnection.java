package sample.Connection;

import javafx.scene.layout.Pane;
import sample.models.Word;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class InternetConnection {
    public Word getOnlineData(String inputStr){
        System.out.println(inputStr);
        Word word = new Word();
        String data= "";
        String decodedData = "";
        try{
            URL url = new URL("https://api.dictionaryapi.dev/api/v2/entries/en/"+inputStr);
            HttpURLConnection con = ( HttpURLConnection) url.openConnection();

            if(con.getResponseCode()==200){
                InputStream im= con.getInputStream();
                BufferedReader br =new BufferedReader(new InputStreamReader(im));
                String line= br.readLine();
                while    (line!= null){
                    data+=line;
                    line=br.readLine();
                }
                br.close();
                System.out.println(data+"\n");
                JSONDecoder jd = new JSONDecoder();
                //word= jd.Decoder(data);
            }
            else{
                decodedData = "Error";
                System.out.println("Internet Connection error!"+ inputStr);
            }
        }catch (Exception e){
            try{
                decodedData = "Second Error";
                System.out.println(e);
            }catch (Exception e1){
                System.out.println(e1);
            }
        }
        return word;
    }
}
