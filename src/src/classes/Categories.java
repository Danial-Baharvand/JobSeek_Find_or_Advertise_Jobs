package classes;

import java.io.*;
import java.util.*;
// Holds data for all categories provided by recruiters
public class Categories {
    HashMap<String, Set<String>> map = new HashMap<>();

    public void addCat(String cat, String recID) {
        // Add category if it doesn't exist
        map.computeIfAbsent(cat, k -> new HashSet<>());
        // Add recruiter to the category
        map.get(cat).add(recID);
    }

    public void remCat(String cat, String recID) {
        if (map.get(cat) != null) {
            map.get(cat).remove(recID);
        }
    }

    public void writeToFile() {
        try {
            FileOutputStream f = new FileOutputStream(("out/production/group-i/dataTables/categories.txt"));
            ObjectOutputStream o = new ObjectOutputStream(f);

            // Write objects to file
            o.writeObject(map);
            // close streams
            o.close();
            f.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public void readFromFile(){
        try {
            FileInputStream fi = new FileInputStream(("myObjects.txt"));
            ObjectInputStream oi = new ObjectInputStream(fi);

            // Read objects
            map = (HashMap<String, Set<String>>) oi.readObject();

            oi.close();
            fi.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
