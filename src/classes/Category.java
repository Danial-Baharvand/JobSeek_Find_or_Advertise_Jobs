package classes;

import java.util.HashSet;
import java.util.Set;

import static classes.Config.*;

public class Category extends HashSet<String> {
    String name;

    /**
     * Stores a category with a set of keywords
     * @param name of the category
     */
    public Category(String name){
        super();
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * Generate the proper format to write to database
     * @return String to be written to database
     */
    @Override
    public String toString(){
        StringBuilder s = new StringBuilder();
        s.append(this.name);
        s.append(SEPARATOR_3);
        String separator = "";
        for (String keyword:this) {
            s.append(separator).append(keyword);
            separator = SEPARATOR_4;
        }
        return s.toString();
    }
    // overridden so the set works
    @Override
    public int hashCode() {
        int prime = 31;
        return prime + (name == null ? 0 : name.hashCode());
    }
    @Override public boolean equals(Object other) {
        if (!(other instanceof Category)) {
            return false;
        }
        Category otherKey = (Category) other;
        return this.name == otherKey.name; // compare the name of the category, so we wouldn't have duplicates
    }
}
