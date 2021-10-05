package sample.Connection;

import javafx.scene.layout.Pane;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class InternetConnection {
    public String getOnlineData(String inputStr){
        String data= "";
        String decodedData = "";
        try{
            URL url = new URL("https://api.dictionaryapi.dev/api/v1/entries/en/"+inputStr);
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
                JSONDecoder jd = new JSONDecoder();
                decodedData = jd.Decoder(data);
            }
            else{
                decodedData = "Error";
                System.out.println("Internet Connection error!");
            }
        }catch (Exception e){
            try{
                decodedData = "Second Error";
                System.out.println(e);
            }catch (Exception e1){
                System.out.println(e1);
            }
        }

        return decodedData;
    }
}
