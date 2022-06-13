package classes;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.google.common.collect.Multimaps;

import java.io.*;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

// comment for test branch
// Holds data for all categories provided by recruiters
public class BiMultiMap {
    //HashMap<String, Set<Object>> map = new HashMap<>();
    Multimap<String,Object> map = ArrayListMultimap.create();
    /**
     * Inserts an object into the value set for the given key in a <String, Set<Object>> map.
     * @param key in the map
     * @param value is the object to be inserted
     */
    public void put(String key, Object value) {
        // convert key to correct text format
        key = key.toLowerCase();
        // Add object to the value set for the key
        map.put(key, value);
    }

    public void remove(String key, Object value) {
        map.remove(key, value);
    }

    public List<String> getKeysForValue(String value){
        return Multimaps.invertFrom(map, ArrayListMultimap.create()).get(value);
    }
    public Collection<Object> get(String key){
        return map.get(key);
    }

    public void writeToFile(String path){
        StringBuilder s = new StringBuilder();
        IO io = new IO();
        io.clearFileContent(path);
        for (Map.Entry<String,Object> entry:map.entries()) {
            io.newLine(path, entry.getKey() + "=" + entry.getValue());
        }
        io.newLine(path, String.valueOf(s));
    }
    public void readFromFile(String path){
        map.clear();
        String line;
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            while ((line = reader.readLine()) != null && !line.equals("")) {
                String[] userData = line.split("=");
                map.put(userData[0], userData[1]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Multimap<String, Object> getMap() {
        return map;
    }
}
