package classes;

import java.util.HashSet;
import java.util.Set;

public class Category extends HashSet<String> {
    String name;
    public Category(String name){
        this.name = name;
    }




    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
