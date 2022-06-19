package classes;

import com.google.common.collect.Ordering;
import com.google.common.collect.TreeMultimap;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Scorer {

    /**
     * Takes 2 strings and returns the number of matching words as a percentage of all words in the first string
     * @param title1 first string to be compared. It is used for work count.
     * @param title2 second string to be compared.
     * @return an integer between 0 and 100 representing the percentage of matching between 2 strings
     */
    public int hasWords(String title1, String title2){
        if (title1.equals("")){return 100;}
        // Find the number of mutual words in 2 strings regardless of case
        double mutual = Stream.of(title1.toLowerCase().split(" ")).
                filter(x -> Arrays.asList(title2.toLowerCase().split(" ")).contains(x)).count();
        double wordCount = Arrays.stream(title1.split(" ")).count();
        return (int) ((mutual / wordCount) * 100);
    }
    public int hasSimilarWords(Set<String> keys1, Set<String> keys2){
        return (int) ((keys1.stream().filter(a->keys2.stream().anyMatch(b->areSimilar(a, b))).count()/(double)keys1.size())*100);
    }

    private boolean areSimilar(String word1, String word2){
        word1 = word1.toLowerCase();
        word2 = word2.toLowerCase();
        if (word2.length()<word1.length()){
            String temp = word1;
            word1 = word2;
            word2 = temp;
        }
        if (word1.length()<4){
            return word1.startsWith(word2)||word1.endsWith(word2);
        }
        for (int i = 0; i < word1.length(); i++) {
            String shorter = word1.substring(0,i)+ '%'+ word1.substring(i+1);
            String longer = word2.substring(0,i)+ '%'+ word2.substring(i+1);
            if (longer.startsWith(shorter)||longer.endsWith(shorter)){return true;}
        }
        return false;
    }
    /**
     * Returns 100 if the target option is in selected options match and returns 0 if they don't.
     * @param selectedOptions first state.
     * @param targetOption second state.
     * @return an int which can either be 0 or 100.
     */
    public int scoreComboBox(Set<String> selectedOptions, String targetOption){
        if (selectedOptions.stream().anyMatch(targetOption::equalsIgnoreCase)) {
            return 100;
        }
        return 0;
    }
    public int scoreComboBox(Set<String> selectedOptions, Set<String> targetOptions){
        if (selectedOptions.stream().anyMatch(targetOptions::contains)) {
            return 100;
        }
        return 0;
    }

    /**
     * Returns 100 if the salary is higher than the minimum salary.
     * Otherwise, returns how close the salary is to minimum salary as a percentage.
     * @param minSalary minimum salary selected by the user.
     * @param salary salary of a job advertised by the recruiter.
     * @return How close the salary is to minimum salary as an int which can be between 0 and 100.
     */
    public int scoreSalary(int minSalary, int salary){
        if (salary >= minSalary){return 100;}
        return salary/minSalary;
    }

    /**
     * Compares a job object against the criteria set by search.
     * Returns the average of the score given to the job for each criterion
     * @param search a search object which has the search criterion
     * @param job the job to be compared to the search parameters
     * @return an int between 0 and 100 representing the score given to the job
     */
    public int searchInJobs(Search search, Job job){
        ArrayList<Integer> allScores = new ArrayList<>();
        Set<String> jobTerms = job.searchTerms();
        JobSeeker jobSeeker = (JobSeeker) Runtime.accountManager().getCurrentUser();
        if (jobSeeker != null && !jobSeeker.getSkills().isEmpty()){
            allScores.add(hasSimilarWords(jobSeeker.getSkills(),jobTerms));
        }
        if (search.getCats().isEmpty())
        {
            Set<String> searchCats = Runtime.accountManager().getCategories().stream().filter(Objects::nonNull)
                .filter(a ->search.getCats().contains(a.getName())).flatMap(Set::stream).collect(Collectors.toSet());
            allScores.add(hasSimilarWords(searchCats, jobTerms));
        }
        //searching in jobs for the main search term
        if (!search.getSearchText().isEmpty()){
            Set<String>searchText = Set.of(search.getSearchText().split(" "));
            allScores.add(hasWords(search.getSearchText(), job.getJobTitle()));
            allScores.add(hasSimilarWords(searchText, Set.of(job.getJobTitle().split(" "))));
            allScores.add(hasSimilarWords(searchText, jobTerms));
        }
        if (!search.getStates().isEmpty()){allScores.add(scoreComboBox(search.getStates(), job.getStates()));}
        if (!search.getJobTypes().isEmpty()){allScores.add(scoreComboBox(search.getJobTypes(), job.getJobType()));}
        if (search.getSalary() > 0){allScores.add(scoreSalary(search.getSalary(), job.getSalary()));}
        return  (int) allScores.stream().mapToDouble(a -> a).average().orElse(0.0);
    }
    public int jobMatchesJobSeeker(Job job, JobSeeker jobSeeker){
        if (!jobSeeker.getSkills().isEmpty()){
            return hasSimilarWords(job.searchTerms(), jobSeeker.getSkills());
        }

        return 0;
    }
    public TreeMultimap<Integer, Job> scoreJobs(Search search, Collection<Job> jobs){
        TreeMultimap<Integer, Job> scoredJobs = TreeMultimap.create(Ordering.natural().reverse(), Comparator.comparing(Job::getID));
        jobs.forEach(job -> scoredJobs.put(searchInJobs(search, job), job));
        return scoredJobs;
    }
    public TreeMultimap<Integer, JobSeeker> scoreJobSeekers(Job job, Collection<JobSeeker> jobSeekers){
        TreeMultimap<Integer, JobSeeker> scoredJobSeekers = TreeMultimap.create(Ordering.natural().reverse(), Comparator.comparing(JobSeeker::getEmail));
        jobSeekers.forEach(jobSeeker -> scoredJobSeekers.put(jobMatchesJobSeeker(job, jobSeeker), jobSeeker));
        return scoredJobSeekers;
    }

}
