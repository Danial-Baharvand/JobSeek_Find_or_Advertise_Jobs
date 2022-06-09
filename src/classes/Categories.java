package classes;

import java.io.*;
import java.util.*;

// comment for test branch
// Holds data for all categories provided by recruiters
public class Categories {
    HashMap<String, Set<String>> map = new HashMap<>();

    public void addCat(String cat, String userEmail) {
        // convert category to correct text format
        cat = cat.substring(0,1).toUpperCase() + cat.substring(1).toLowerCase();
        // Add category if it doesn't exist
        map.computeIfAbsent(cat, k -> new HashSet<>());
        // Add recruiter to the category
        map.get(cat).add(userEmail);
    }

    public void remCat(String cat, String userEmail) {
        if (map.get(cat) != null) {
            map.get(cat).remove(userEmail);
            if (map.get(cat).isEmpty()){
                map.remove(cat);
            }
        }
    }

    public void writeToFile(){
        IO io = new IO();
        io.clearFileContent(Config.DT_CATEGORIES);
        for (String category:map.keySet()) {
            io.newLine(Config.DT_CATEGORIES, category + "=" + map.get(category).toString());
        }
    }
    public void readFromFile(){
        map.clear();
        String line;
        try (BufferedReader reader = new BufferedReader(new FileReader(Config.DT_CATEGORIES))) {
            while ((line = reader.readLine()) != null && !line.equals("")) {
                String[] userData = line.split("=");
                HashSet<String> cat = new HashSet<>(Arrays.asList(userData[1]
                        .substring(1, userData[1].length() - 1).split(", ")));
                map.put(userData[0], cat);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public ArrayList<String> getUserCategories(User user) {
        HashMap<String, Set<String>> categories = Runtime.categories.getMap();
        ArrayList<String> userCats = new ArrayList<>();
        for (String category : categories.keySet()) {
            if (categories.get(category).contains(user.getEmail())) {
                userCats.add(category);
            }
        }
        return userCats;
    }

    public HashMap<String, Set<String>> getMap() {
        return map;
    }
}
