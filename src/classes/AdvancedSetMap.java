package classes;

import java.io.*;
import java.util.*;

// comment for test branch
// Holds data for all categories provided by recruiters
public class AdvancedSetMap {
    HashMap<String, Set<Object>> map = new HashMap<>();

    /**
     * Inserts an object into the value set for the given key in a <String, Set<Object>> map.
     * @param key in the map
     * @param setValue is the object to be inserted
     */
    public void add(String key, Object setValue) {
        // convert key to correct text format
        key = key.toLowerCase();
        // Add key if it doesn't exist
        map.computeIfAbsent(key, k -> new HashSet<>());
        // Add object to the value set for the key
        map.get(key).add(setValue);
    }

    public void remove(String key, Object setValue) {
        if (map.get(key) != null) {
            map.get(key).remove(setValue);
            if (map.get(key).isEmpty()){
                map.remove(key);
            }
        }
    }

    public void writeToFile(String path){
        IO io = new IO();
        io.clearFileContent(path);
        for (String key:map.keySet()) {
            io.newLine(path, key + "=" + map.get(key).toString());
        }
    }
    public void readFromFile(String path){
        map.clear();
        String line;
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            while ((line = reader.readLine()) != null && !line.equals("")) {
                String[] userData = line.split("=");
                HashSet<Object> cat = new HashSet<>(Arrays.asList(userData[1]
                        .substring(1, userData[1].length() - 1).split(", ")));
                map.put(userData[0], cat);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public HashMap<String, Set<Object>> getMap() {
        return map;
    }
}
