package classes;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class GuiHelper {
    public static ArrayList<String> getSelectedOptions(JPanel optionsPanel) {
        ArrayList<String> selectedOptions = new ArrayList<>();
        Component[] allOptions = optionsPanel.getComponents();
        for (Component option : allOptions) {
            if (((JCheckBox) option).isSelected()) {
                selectedOptions.add(((JCheckBox) option).getText().toLowerCase());
            }
        }
        return selectedOptions;
    }

    public static void createOptionBox(JPanel optionsPanel, Collection<String> options) {
        optionsPanel.setLayout(new BoxLayout(optionsPanel, BoxLayout.Y_AXIS));
        optionsPanel.removeAll();
        for (String option : options) {
            option = option.substring(0,1).toUpperCase() + option.substring(1).toLowerCase();
            optionsPanel.add(new JCheckBox(option));
        }
    }
    public static <V> boolean createJobList(Collection<V> jobList, JPanel jobsPanel) {
        if (jobList.isEmpty()){
            return false;
        }
        for (V job : jobList) {
            JPanel jPanel;
            if (job instanceof Job) {
                jPanel = new JobListing((Job) job).getJobListingPanel();
            } else {
                jPanel = new JobListing((ScoredJob) job).getJobListingPanel();
            }

            jobsPanel.setLayout(new BoxLayout(jobsPanel, BoxLayout.Y_AXIS));
            jobsPanel.add(jPanel);
        }
        return true;
    }
}
