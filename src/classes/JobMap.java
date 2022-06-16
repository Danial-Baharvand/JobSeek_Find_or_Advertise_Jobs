package classes;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class JobMap extends BiMultiMap<Job> {
    @Override
    public void writeToFile(String path){
        StringBuilder s = new StringBuilder();
        IO io = new IO();
        io.clearFileContent(path);
        for (Map.Entry<String,Job> entry:map.entries()) {
            io.newLine(path, entry.getKey() + "=" + entry.getValue().getID());
        }
        io.newLine(path, String.valueOf(s));
    }
    public void readFromFile(String path, HashMap<String, Job> jobs){
        map.clear();
        invertedMap.clear();
        String line;
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            while ((line = reader.readLine()) != null && !line.equals("")) {
                String[] userData = line.split("=");
                Job fileJob = jobs.get(userData[1]);
                if (fileJob == null){
                    fileJob = new Job();
                }
                map.put(userData[0], fileJob);
                invertedMap.put(fileJob, userData[0]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
