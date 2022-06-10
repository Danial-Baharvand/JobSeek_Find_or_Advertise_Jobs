package classes;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Jobs extends AdvancedSetMap {
    public void readFromFile(String path){
        map.clear();
        String line;
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            while ((line = reader.readLine()) != null && !line.equals("")) {
                String[] userData = line.split("=");
                System.out.println(userData[0]);
                System.out.println(userData[1]);
/*                HashSet<Object> cat = new HashSet<>(Arrays.asList(userData[1]
                        .substring(1, userData[1].length() - 1).split(", ")));*/
                HashSet<Object> cat = createJobs(userData[1].substring(1, userData[1].length() - 1));
                map.put(userData[0], cat);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public HashSet<Object> createJobs(String jobDetails){
        HashSet<Object> jobs = new HashSet<>();
        for (String jobDetail:jobDetails.split(", ")) {
            Job newJob = new Job(jobDetail);
            jobs.add(newJob);
        }
        return jobs;
    }
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
