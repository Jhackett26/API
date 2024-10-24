import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;


// video to load jar
//https://www.youtube.com/watch?v=QAJ09o3Xl_0

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

// Program for print data in JSON format.
public class ReadJson {
    character[] characters = new character[20];
    public static void main(String args[]) throws ParseException {
        // In java JSONObject is used to create JSON object
        // which is a subclass of java.util.HashMap.
       ReadJson h = new ReadJson();
       h.pull();

    }

    public  void pull() throws ParseException {
        String output = "default value";
        String totalJson="";
        try {
            URL url = new URL("https://last-airbender-api.fly.dev/api/v1/characters");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200) {

                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));


            System.out.println("Output from Server .... \n");
            while ((output = br.readLine()) != null) {
                System.out.println(output);
                totalJson+=output;
            }

            conn.disconnect();

        } catch (MalformedURLException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();
        }

        JSONParser parser = new JSONParser();
        JSONArray jsonArray = (JSONArray) parser.parse(totalJson);

        System.out.println(jsonArray);

        try {
            for(int i = 0; i<jsonArray.size();i++) {
                JSONObject jsonObj = (JSONObject) jsonArray.get(i);
                String name = (String) jsonObj.get("name");
                String affiliation = (String) jsonObj.get("affiliation");
                ArrayList enemies = (ArrayList) jsonObj.get("enemies");
                ArrayList allies = (ArrayList) jsonObj.get("allies");
                characters[i]  = new character(name, affiliation, enemies, allies);
                System.out.println(characters[i].name);
                System.out.println(characters[i].affiliation);
                System.out.println(characters[i].enemies);
                System.out.println(characters[i].allies);
            }
        }

        catch (Exception e) {
            e.printStackTrace();
        }




    }
}


