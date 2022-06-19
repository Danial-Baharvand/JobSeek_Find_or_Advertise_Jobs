package classes;

import com.google.common.collect.TreeMultimap;

import javax.naming.NotContextException;
import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GuiHelper {
    public static Set<String> getSelectedOptions(JPanel optionsPanel) {
        Set<String> selectedOptions = new HashSet<>();
        Component[] allOptions = optionsPanel.getComponents();
        for (Component option : allOptions) {
            if (((JCheckBox) option).isSelected()) {
                selectedOptions.add(((JCheckBox) option).getText().toLowerCase());
            }
        }
        return selectedOptions;
    }
    public static void setSelectedOptions(JPanel optionsPanel, Collection<String> selected) {
        Component[] allOptions = optionsPanel.getComponents();
        for (Component option : allOptions) {
            if (selected.contains(((JCheckBox)option).getText().toLowerCase())) {
                ((JCheckBox) option).setSelected(true);
            }
        }
    }
    public static String  getSelectedRadio(JPanel optionsPanel) throws Exception {
        Component[] allOptions = optionsPanel.getComponents();
        for (Component option : allOptions) {
            if (((JRadioButton) option).isSelected()) {
                return ((JRadioButton) option).getText().toLowerCase();
            }
        }
        throw new Exception();
    }
    public static void createOptionBox(JPanel optionsPanel, Collection<String> options) {
        optionsPanel.setLayout(new BoxLayout(optionsPanel, BoxLayout.Y_AXIS));
        optionsPanel.removeAll();
        for (String option : options) {
            option = option.substring(0,1).toUpperCase() + option.substring(1).toLowerCase();
            optionsPanel.add(new JCheckBox(option));
        }
        optionsPanel.updateUI();
    }
    public static void createRadioBox(JPanel optionsPanel, Collection<String> options) {
        optionsPanel.setLayout(new BoxLayout(optionsPanel, BoxLayout.Y_AXIS));
        optionsPanel.removeAll();
        ButtonGroup buttonGroup = new ButtonGroup();
        for (String option : options) {
            option = option.substring(0,1).toUpperCase() + option.substring(1).toLowerCase();
            JRadioButton radioButton = new JRadioButton(option);
            buttonGroup.add(radioButton);
            optionsPanel.add(radioButton);
        }
        optionsPanel.updateUI();
    }
    public static boolean createJobList(TreeMultimap<Integer, Job> jobList, JPanel jobsPanel) {
        if (jobList.isEmpty()){
            return false;
        }
        for (Map.Entry<Integer, Job> entry : jobList.entries()) {
            JPanel jPanel = new JobListing(entry.getKey(),entry.getValue()).getJobListingPanel();
            jobsPanel.setLayout(new BoxLayout(jobsPanel, BoxLayout.Y_AXIS));
            jobsPanel.add(jPanel);
        }
        return true;
    }
    public static boolean createJobList(Collection< Job> jobList, JPanel jobsPanel) {
        if (jobList.isEmpty()){
            return false;
        }
        for (Job job : jobList) {
            JPanel jPanel;
            jPanel = new JobListing(job).getJobListingPanel();
            jobsPanel.setLayout(new BoxLayout(jobsPanel, BoxLayout.Y_AXIS));
            jobsPanel.add(jPanel);
        }
        return true;
    }
    public static boolean createJobSeekerList(TreeMultimap<Integer, JobSeeker> jobSeekerList, Job job, JPanel jobSeekerPanel) {
        if (jobSeekerList.isEmpty()){
            return false;
        }
        jobSeekerPanel.setLayout(new BoxLayout(jobSeekerPanel, BoxLayout.Y_AXIS));
        for (Map.Entry<Integer, JobSeeker> entry : jobSeekerList.entries()) {
            JPanel jPanel = new JobSeekerItem(entry.getValue(), job, entry.getKey()).getPanel();
            jobSeekerPanel.add(jPanel);
        }
        return true;
    }
    public static String capitalise(String words) {
        return Stream.of(words.trim().split("\\s"))
                .filter(word -> word.length() > 0)
                .map(word -> word.substring(0, 1).toUpperCase() + word.substring(1))
                .collect(Collectors.joining(" "));
    }
}
