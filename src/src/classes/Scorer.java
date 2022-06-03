package classes;

import java.util.Arrays;
import java.util.stream.Stream;

public class Scorer {
    public static void main(String[] args) {
        // Code to test functionality
        Scorer scorer = new Scorer();
        //System.out.println(scorer.scoreJobTitle("world hello", "Hi World"));
        //System.out.println(scorer.scoreComboBox("Any", "hi"));

    }

    /**
     * Takes 2 strings and returns the number of matching words as a percentatge of all words in the first string
     * @param title1 first string to be compared. It is used for work count.
     * @param title2 second string to be compared.
     * @return an integer between 0 and 100 representing the percentage of matching between 2 strings
     */
    public int scoreStrings(String title1, String title2){
        if (title1.equals("")){return 100;}
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
     * @param option1 first state.
     * @param option2 second state.
     * @return an int which can either be 0 or 100.
     */
    public int scoreComboBox(String option1, String option2){
        if(option1.toLowerCase().equals("any")){return 100;}
        if (option1.toLowerCase().equals(option2.toLowerCase())) {return 100;}
        return 0;
    }

    /**
     * Returns 100 if the salary is higher than the minimum salary. Returns 0 otherwise.
     * @param minSalary minimum salary selected by the user.
     * @param salary salary of a job advertised by the recruiter.
     * @return an int which can either be 0 or 100.
     */
    public int scoreSalary(int minSalary, int salary){
        if (salary >= minSalary){return 100;}
        return 0;
    }
    public int scoreAgaintSearch(Search search, Job job){
        final int noOfCriteria = 6;
        int total = scoreStrings(search.getSearchText(), job.getJobTitle()) +
                scoreStrings(search.getKeywords(), job.getKeywords()) +
                scoreComboBox(search.getState(), job.getState()) +
                scoreComboBox(search.getCat(), job.getCat()) +
                scoreComboBox(search.getJobType(), job.getJobType()) +
                scoreSalary(search.getSalary(), job.getSalary());
        return total / noOfCriteria;
    }

}
