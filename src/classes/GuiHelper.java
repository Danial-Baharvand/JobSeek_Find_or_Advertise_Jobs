package classes;

import javax.naming.NotContextException;
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
    public static String  getSelectedRadio(JPanel optionsPanel) throws Exception {
        Component[] allOptions = optionsPanel.getComponents();
        for (Component option : allOptions) {
            if (((JRadioButton) option).isSelected()) {
                return ((JRadioButton) option).getText().toLowerCase();
            }
        }
        throw new Exception();
    }
    public static void createOptionBox(JPanel optionsPanel, Collection<String> options, String hidden) {
        optionsPanel.setLayout(new BoxLayout(optionsPanel, BoxLayout.Y_AXIS));
        optionsPanel.removeAll();
        options.remove(hidden);
        for (String option : options) {
            option = option.substring(0,1).toUpperCase() + option.substring(1).toLowerCase();
            optionsPanel.add(new JCheckBox(option));
        }
        optionsPanel.updateUI();
    }
    public static void createOptionBox(JPanel optionsPanel, Collection<String> options) {
        createOptionBox(optionsPanel, options, null);
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
