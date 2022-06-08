package classes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

public class Scorer {

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
        double wordCount = Arrays.stream(title1.split(" ")).count();
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
     * Returns 100 if the target option is in selected options match and returns 0 if they don't.
     * @param selectedOptions first state.
     * @param targetOption second state.
     * @return an int which can either be 0 or 100.
     */
    public int scoreComboBox2(ArrayList<String> selectedOptions, String targetOption){
        if (selectedOptions.stream().anyMatch(targetOption::equalsIgnoreCase)) {
            return 100;

        }
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

    /**
     * Compares a job object against the criteria set by search.
     * Returns the average of the score given to the job for each criterion
     * @param search a search object which has the search criterion
     * @param job the job to be compared to the search parameters
     * @return an int between 0 and 100 repersenting the score given to the job
     */
    public int scoreAgaintSearch(Search search, Job job){
        final int noOfCriteria = 5;
        int total = scoreStrings(search.getSearchText(), job.getJobTitle()) +
                scoreComboBox2(search.getStates(), job.getState()) +
                scoreComboBox2(search.getCats(), job.getCat()) +
                scoreComboBox(search.getJobType(), job.getJobType()) +
                scoreSalary(search.getSalary(), job.getSalary());
        return total / noOfCriteria;
    }

}
