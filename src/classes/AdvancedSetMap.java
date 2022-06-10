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
        key = key.substring(0,1).toUpperCase() + key.substring(1).toLowerCase();
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
        for (String category:map.keySet()) {
            io.newLine(path, category + "=" + map.get(category).toString());
        }
    }


    public HashMap<String, Set<Object>> getMap() {
        return map;
    }
}
