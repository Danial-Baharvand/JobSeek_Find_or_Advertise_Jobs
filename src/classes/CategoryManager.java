package classes;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class CategoryManager {
    private Set<Category> categories;
    public CategoryManager(){
        categories = new HashSet<>();
    }
    public Set<String> names(){
        return categories.stream().map(c -> c.name).collect(Collectors.toSet());
    }
    public void add(Category category){
        categories.add(category);
    }
    public void remove(Category category){
        categories.remove(category);
    }
    public Category getByName(String targetName){
        return categories.stream().filter(c -> Objects.equals(c.name, targetName)).findFirst().orElse(null);
    }
    public void addAll(Set<Category> newCategories){
        categories.addAll(newCategories);
    }
    public String toCapitalString(){
        return categories.stream().map(c -> Character.toUpperCase(c.name.charAt(0)) + c.name.substring(1)).
                collect(Collectors.joining(", "));
    }
}
