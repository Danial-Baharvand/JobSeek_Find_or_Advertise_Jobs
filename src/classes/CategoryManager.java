package classes;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import static classes.Config.SEPARATOR_2;

/**
 * holds a set of categories
 */
public class CategoryManager extends HashSet<Category> {
    /**
     * @return set of category names
     */
    public Set<String> names(){
        return this.stream().map(c -> c.name).collect(Collectors.toSet());
    }

    /**
     * get a category by its name
     * @param targetName category name
     * @return category
     */
    public Category getByName(String targetName){
        return this.stream().filter(Objects::nonNull).filter(c -> c.name.equalsIgnoreCase(targetName) ).findFirst()
                .orElse(null);
    }

    /**
     * Main print method, changes the first letter to uppercase, so it's presentable.
     * @return string representation of the categories with capitalised beginnings.
     */
    public String toCapitalString(){

        return this.stream().filter(Objects::nonNull).map(c -> Character.toUpperCase(c.name.charAt(0)) +
                        c.name.substring(1)).collect(Collectors.joining(", "));
    }

    /**
     * Get representation by category names
     * @return category names string
     */
    @Override
    public String toString(){
        StringBuilder s = new StringBuilder();
        String separator = "";
        for (Category category:this) {
            s.append(separator).append(category.getName());
            separator = SEPARATOR_2;
        }
        return s.toString();
    }

    /**
     * Convert category to writable format
     * @return writable string
     */
    public String toWriteFormat(){
        StringBuilder s = new StringBuilder();
        for (Category category:this) {
            s.append(category).append("\n");
        }
        return s.toString();
    }


}
