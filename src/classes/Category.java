package classes;

import java.util.HashSet;
import java.util.Set;

import static classes.Config.*;

public class Category extends HashSet<String> {
    String name;
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
        return this.name == otherKey.name;
    }
}
