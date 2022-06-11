package classes;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import javax.swing.text.StyleContext;
import java.awt.*;
import java.util.ArrayList;
import java.util.Locale;

public class DescriptionPage {
    private JButton applyButton;
    private JLabel description;
    private JLabel location;
    private JLabel cat;
    private JPanel DescriptionPanel;
    private JLabel salary;
    private JTextArea textArea1;

    /**
     * Creates the detailed description page.
     *
     * @param jobList      the arraylist of scoredJob objects for the current search
     * @param pageNumber   the page number the that this job is on
     * @param indexOfClick indicating which job was clicked on the page
     */
    public DescriptionPage(ArrayList<ScoredJob> jobList, int pageNumber, int indexOfClick) {
        Job job = jobList.get(pageNumber * 3 + indexOfClick).getJob();
        description.setText(job.getJobDescription());
        location.setText(job.getState());
        cat.setText(job.getCat());
        salary.setText(String.valueOf(job.getSalary()));
    }

    public JPanel getDescriptionPanel() {
        return DescriptionPanel;
    }
}
