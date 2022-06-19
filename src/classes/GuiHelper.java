package classes;

import com.google.common.collect.TreeMultimap;

import javax.naming.NotContextException;
import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * functions to help with producing the GUI
 */
public class GuiHelper {
    /**
     * gets the selected options from a jpanel that contains checkboxes
     * @param optionsPanel the panel
     * @return set of selected options
     */
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

    /**
     * Checks the checkboxes in a jpanel
     */
    public static void setSelectedOptions(JPanel optionsPanel, Collection<String> selected) {
        Component[] allOptions = optionsPanel.getComponents();
        for (Component option : allOptions) {
            if (selected.contains(((JCheckBox)option).getText().toLowerCase())) {
                ((JCheckBox) option).setSelected(true);
            }
        }
    }

    /**
     * gets the selected options from a jpanel with radio buttons
     * @param optionsPanel
     * @return
     * @throws Exception
     */
    public static String  getSelectedRadio(JPanel optionsPanel) throws Exception {
        Component[] allOptions = optionsPanel.getComponents();
        for (Component option : allOptions) {
            if (((JRadioButton) option).isSelected()) {
                return ((JRadioButton) option).getText().toLowerCase();
            }
        }
        throw new Exception();
    }

    /**
     * creates puts the checkboxes in a set as checkboxes into a jpanel
     * @param optionsPanel
     * @param options
     */
    public static void createOptionBox(JPanel optionsPanel, Collection<String> options) {
        optionsPanel.setLayout(new BoxLayout(optionsPanel, BoxLayout.Y_AXIS));
        optionsPanel.removeAll();
        for (String option : options) {
            option = option.substring(0,1).toUpperCase() + option.substring(1).toLowerCase();
            optionsPanel.add(new JCheckBox(option));
        }
        optionsPanel.updateUI();
    }

    /**
     * creates puts the radio buttons in a set as checkboxes into a jpanel
     * @param optionsPanel
     * @param options
     */
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

    /**
     * creates a list of job items and puts them into a jpabel
     * @param jobList list of jobs and their scores
     * @param jobsPanel
     * @return
     */
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

    /**
     * creates a list of job items and puts them into a jpabel
     * @param jobList list of jobs
     * @param jobsPanel
     * @return
     */
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

    /**
     * creates jobseeker items and puts them into the jpanel
     * @param jobSeekerList
     * @param job
     * @param jobSeekerPanel
     * @return
     */
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

    /**
     * capitalizes the first character for each letter in a string
     * @param words the string
     * @return
     */
    public static String capitalise(String words) {
        return Stream.of(words.trim().split("\\s"))
                .filter(word -> word.length() > 0)
                .map(word -> word.substring(0, 1).toUpperCase() + word.substring(1))
                .collect(Collectors.joining(" "));
    }
}
