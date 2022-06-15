package classes;

import java.io.BufferedReader;
import java.net.*;
import java.util.ArrayList;
import org.json.simple.*;

// code sourced from http://thesaurus.altervista.org/testjava with amendments


public class Thesaurus {

    final String endpoint = "http://thesaurus.altervista.org/thesaurus/v1";

    public Thesaurus(String word, String language, String output) {
        try {
            URL serverAddress = new URL(endpoint + "?word="+URLEncoder.encode(word, "UTF-8")+"&language="+language+"&key="+"PTM7RS5jqBLsizfWN3xN"+"&output="+output);
            HttpURLConnection connection = (HttpURLConnection)serverAddress.openConnection();
            connection.connect();
            int rc = connection.getResponseCode();
            if (rc == 200) {
                String line = null;
                BufferedReader br = new BufferedReader(new java.io.InputStreamReader(connection.getInputStream()));
                StringBuilder sb = new StringBuilder();
                while ((line = br.readLine()) != null)
                    sb.append(line + '\n');
                JSONObject obj = (JSONObject) JSONValue.parse(sb.toString());
                JSONArray array = (JSONArray)obj.get("response");
                ArrayList<String> results = new ArrayList<String>();
                for (int i = 0; i < array.size(); i++) {
                    JSONObject list = (JSONObject) ((JSONObject)array.get(i)).get("list");
                    results.add(list.get("synonyms").toString().split("\\|")[0]);
                }
                results.forEach( (r) -> {System.out.println(r);});
            } else System.out.println("HTTP error:"+rc);
            connection.disconnect();
        } catch (MalformedURLException | ProtocolException e) {
            e.printStackTrace();
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }
}
