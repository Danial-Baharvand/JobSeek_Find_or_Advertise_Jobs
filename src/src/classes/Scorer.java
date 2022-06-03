package classes;

import java.util.Arrays;
import java.util.stream.Stream;

public class Scorer {
    public static void main(String[] args) {
        Scorer scorer = new Scorer();
        System.out.println(scorer.scoreJobTitle("world hello", "Hi World"));

    }

    /**
     * Takes 2 strings and returns the number of matching words as a percentatge of all words in the first string
     * @param title1 first string to be compared. It is used for work count.
     * @param title2 second string to be compared.
     * @return an integer between 0 and 100 representing the percentage of matching between 2 strings
     */
    public int scoreJobTitle(String title1, String title2){
        // Find the number of mutual words in 2 strings regardless of case
        double mutual = Stream.of(title1.toLowerCase().split(" ")).
                filter(x -> Arrays.asList(title2.toLowerCase().split(" ")).contains(x)).count();
        System.out.println(mutual);
        double wordCount = Arrays.stream(title1.split(" ")).count();
        System.out.println(wordCount);
        return (int) ((mutual / wordCount) * 100);
    }

    /**
     * Returns 100 if the states match and returns 0 if they don't.
     * @param state1 first state.
     * @param state2 second state.
     * @return an int which can either be 0 or 100.
     */
    public int scoreState(String state1, String state2){
        if (state1.toLowerCase().equals(state2.toLowerCase())) {return 100;}
        return 0;
    }


}
