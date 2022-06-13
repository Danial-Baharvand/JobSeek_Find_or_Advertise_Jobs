package classes;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

import java.io.*;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**
 * Wrapper class for 2 ArraListMultiMaps with needed functionality implemented
 */
public class BiMultiMap <V> {
    //HashMap<String, Set<Object>> map = new HashMap<>();
    Multimap<String,V> map = HashMultimap.create();
    Multimap<V,String> invertedMap = HashMultimap.create();
    /**
     * Inserts an object into the value set for the given key in a <String, Set<Object>> map.
     * @param key in the map
     * @param value is the object to be inserted
     */
    public void put(String key, V value) {
        // convert key to correct text format
        key = key.toLowerCase();
        // Add object to the value set for the key
        map.put(key, value);
        invertedMap.put(value, key);
    }

    public void remove(String key, V value) {
        map.remove(key, value);
        invertedMap.remove(value, key);
    }

    public Collection<String> getKeysForValue(V value){
        return invertedMap.get(value);
    }
    public Collection<V> get(String key){
        return map.get(key);
    }

    public void writeToFile(String path){
        StringBuilder s = new StringBuilder();
        IO io = new IO();
        io.clearFileContent(path);
        for (Map.Entry<String,V> entry:map.entries()) {
            io.newLine(path, entry.getKey() + "=" + entry.getValue());
        }
        io.newLine(path, String.valueOf(s));
    }
    public void readFromFile(String path){
        map.clear();
        invertedMap.clear();
        String line;
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            while ((line = reader.readLine()) != null && !line.equals("")) {
                String[] userData = line.split("=");
                map.put(userData[0], (V)userData[1]);
                invertedMap.put((V)userData[1], userData[0]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public Set<String> keySet(){
        return map.keySet();
    }
}
