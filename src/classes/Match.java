package classes;

public class Match {

    public Match() {

    }

    //Matches Text Search against Job Description and Job Keywords
    //Also Matches Text Search against first and second order Synonym Derivatives of the words in Job Description and Job Keywords
    //Job Keywords have a higher weighting than Job Description
    //Weighting of Original Words > First-Order Synonym Derivatives > Second-Order Synonym Derivatives
    public int scoreMatch(String searchText, String keywords, String jobDescription) {
        int score = 0;
        return score;
    }
}
