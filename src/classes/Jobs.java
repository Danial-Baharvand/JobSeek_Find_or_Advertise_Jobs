package classes;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Jobs extends BiMultiMap<Job> {
    @Override
    public void readFromFile(String path){
        map.clear();
        invertedMap.clear();
        String line;
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            while ((line = reader.readLine()) != null && !line.equals("")) {
                String[] userData = line.split("=");
                Job job = new Job(userData[1]);
                map.put(userData[0], job);
                invertedMap.put(job, userData[0]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
