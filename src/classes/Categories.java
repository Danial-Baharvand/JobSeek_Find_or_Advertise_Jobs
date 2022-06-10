package classes;

import java.io.*;
import java.util.*;

// comment for test branch
// Holds data for all categories provided by recruiters
public class Categories extends AdvancedSetMap {

    public ArrayList<String> getUserCategories(User user) {
        HashMap<String, Set<Object>> categories = Runtime.categories.getMap();
        ArrayList<String> userCats = new ArrayList<>();
        for (String category : categories.keySet()) {
            if (categories.get(category).contains(user.getEmail())) {
                userCats.add(category);
            }
        }
        return userCats;
    }

}
