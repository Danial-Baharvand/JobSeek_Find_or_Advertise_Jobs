package classes;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import javax.swing.text.StyleContext;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
import java.util.stream.Collectors;

public class DescriptionPage implements Page {
    private JButton applyButton;
    private JTextPane description;
    private JLabel location;
    private JLabel cat;
    private JPanel descriptionPanel;
    private JLabel salary;
    private JButton removeButton;
    private JLabel messageLabel;
    private JTextPane messageText;
    private JPanel messagePanel;
    private JButton editBtn;
    private JButton removeBtn;
    private JButton suitablesBtn;
    private JButton viewApplicantsBtn;
    private JPanel jobseekerButtons;
    private JPanel recruiterButtons;
    private JLabel jobTitle;
    private JButton publishButton;

    /**
     * Creates the detailed description page.
     *
     * @param desFrame
     * @param jobList      the arraylist of scoredJob objects for the current search
     * @param pageNumber   the page number the that this job is on
     * @param indexOfClick indicating which job was clicked on the page
     */
    public DescriptionPage(Job job) {
        if (Runtime.accountManager().getCurrentUser() instanceof JobSeeker) {
            checkJobSeekerJobStatus(job);
            addJobSeekerJobListenrs(job);
        } else if (Runtime.accountManager().getCurrentUser() instanceof Recruiter) {
            checkRecruiterJobStatus(job);
            addRecruiterJobListenrs(job);
        }

        jobTitle.setText(job.getJobTitle());
        description.setText(job.getJobDescription());
        location.setText(job.getStates().stream().map(s -> Character.toUpperCase(s.charAt(0)) +
                s.substring(1)).collect(Collectors.joining(", ")));
        // Getting job categories, capitalizing the first letter and adding a comma between them and setting to label
        cat.setText(job.categories().toCapitalString());

        salary.setText("$" + job.getSalary());

    }

    private void addRecruiterJobListenrs(Job job) {
        removeBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Recruiter recruiter = (Recruiter) Runtime.accountManager().getCurrentUser();
                recruiter.removeJob(job);
                job.setPublished(false);
                IO.writeRecruiters();
                IO.writeJobs();
                Runtime.getPagesVisited().clear();
                Runtime.showRecruiterHome();
                JOptionPane.showMessageDialog(null, "Job was deleted!");
            }
        });
        editBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Runtime.showCreateJobPage(job);
            }
        });
        publishButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                job.setPublished(true);
                publishButton.setEnabled(false);
            }
        });
        viewApplicantsBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Set<JobSeeker> applicants = job.applications().stream().map(Mail::getJobSeeker).collect(Collectors.toSet());
                HashMap<JobSeeker, Integer> jobSeekerScores = Tests.createTestJobSeekerScores(applicants);
                Runtime.showJobSeekerResultsPage(jobSeekerScores, job);
            }
        });
        suitablesBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Set<JobSeeker> applicants = Runtime.accountManager().getJobSeekers().values().stream()
                        .filter(User::isActive).collect(Collectors.toSet());
                HashMap<JobSeeker, Integer> jobSeekerScores = Tests.createTestJobSeekerScores(applicants);
                Runtime.showJobSeekerResultsPage(jobSeekerScores, job);
            }
        });
    }


    private void checkRecruiterJobStatus(Job job) {
        jobseekerButtons.setVisible(false);
        recruiterButtons.setVisible(true);
        if (!job.invitations().isEmpty() || !job.applications().isEmpty()) {
            editBtn.setEnabled(false);
            editBtn.setToolTipText("Jobs with ongoing applications can't be edited");
        }
        if (job.published) {
            publishButton.setVisible(false);
        }
    }

    public void checkJobSeekerJobStatus(Job job) {
        JobSeeker jobSeeker = (JobSeeker) Runtime.accountManager().getCurrentUser();
        jobseekerButtons.setVisible(true);
        recruiterButtons.setVisible(false);
        if (jobSeeker.invitations().getJobs().contains(job)) {
            removeButton.setVisible(false);
            applyButton.setVisible(false);
            messagePanel.setVisible(true);
            messageLabel.setVisible(true);
            String message = jobSeeker.invitations().getMessage(job);
            messageText.setText(message);
        } else if (jobSeeker.applications().getJobs().contains(job)) {
            removeButton.setVisible(true);
            applyButton.setVisible(false);
        }
    }

    public void addJobSeekerJobListenrs(Job job) {
        applyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JobSeeker jobSeeker = (JobSeeker) Runtime.accountManager().getCurrentUser();
                Application newApp = new Application(jobSeeker, job);
                jobSeeker.applications().add(newApp);
                job.applications().add(newApp);
                IO.writeJobs();
                IO.writeJobseekers();
                removeButton.setVisible(true);
                applyButton.setVisible(false);
            }
        });
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JobSeeker jobSeeker = (JobSeeker) Runtime.accountManager().getCurrentUser();
                jobSeeker.applications().removeByJob(job);
                job.applications().removeByJobSeeker(jobSeeker);
                IO.writeJobs();
                IO.writeJobseekers();
                removeButton.setVisible(false);
                applyButton.setVisible(true);
            }
        });
    }

    public JPanel getDescriptionPanel() {
        return descriptionPanel;
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        descriptionPanel = new JPanel();
        descriptionPanel.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(14, 3, new Insets(20, 20, 20, 20), -1, -1));
        descriptionPanel.setBackground(new Color(-13224648));
        final com.intellij.uiDesigner.core.Spacer spacer1 = new com.intellij.uiDesigner.core.Spacer();
        descriptionPanel.add(spacer1, new com.intellij.uiDesigner.core.GridConstraints(11, 0, 1, 3, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(-1, 20), null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer2 = new com.intellij.uiDesigner.core.Spacer();
        descriptionPanel.add(spacer2, new com.intellij.uiDesigner.core.GridConstraints(8, 0, 1, 3, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(-1, 20), null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer3 = new com.intellij.uiDesigner.core.Spacer();
        descriptionPanel.add(spacer3, new com.intellij.uiDesigner.core.GridConstraints(5, 0, 1, 3, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        messagePanel = new JPanel();
        messagePanel.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        messagePanel.setVisible(false);
        descriptionPanel.add(messagePanel, new com.intellij.uiDesigner.core.GridConstraints(2, 0, 1, 3, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        messageText = new JTextPane();
        messageText.setEditable(false);
        messagePanel.add(messageText, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, 50), null, 0, false));
        messageLabel = new JLabel();
        Font messageLabelFont = this.$$$getFont$$$("Calibri Light", Font.BOLD, 16, messageLabel.getFont());
        if (messageLabelFont != null) messageLabel.setFont(messageLabelFont);
        messageLabel.setForeground(new Color(-592138));
        messageLabel.setText("Message");
        messageLabel.setVisible(false);
        descriptionPanel.add(messageLabel, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        recruiterButtons = new JPanel();
        recruiterButtons.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 6, new Insets(0, 0, 0, 0), -1, -1));
        recruiterButtons.setBackground(new Color(-13224648));
        descriptionPanel.add(recruiterButtons, new com.intellij.uiDesigner.core.GridConstraints(1, 1, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        editBtn = new JButton();
        editBtn.setText("Edit Job");
        recruiterButtons.add(editBtn, new com.intellij.uiDesigner.core.GridConstraints(0, 3, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        removeBtn = new JButton();
        removeBtn.setText("Remove Job");
        recruiterButtons.add(removeBtn, new com.intellij.uiDesigner.core.GridConstraints(0, 4, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        suitablesBtn = new JButton();
        suitablesBtn.setText("View Suitable JobSeekers");
        recruiterButtons.add(suitablesBtn, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        viewApplicantsBtn = new JButton();
        viewApplicantsBtn.setText("View Applicants");
        recruiterButtons.add(viewApplicantsBtn, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer4 = new com.intellij.uiDesigner.core.Spacer();
        recruiterButtons.add(spacer4, new com.intellij.uiDesigner.core.GridConstraints(0, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        publishButton = new JButton();
        publishButton.setText("Publish");
        recruiterButtons.add(publishButton, new com.intellij.uiDesigner.core.GridConstraints(0, 5, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label1 = new JLabel();
        Font label1Font = this.$$$getFont$$$("Calibri Light", Font.BOLD, 16, label1.getFont());
        if (label1Font != null) label1.setFont(label1Font);
        label1.setForeground(new Color(-592138));
        label1.setText("Description");
        descriptionPanel.add(label1, new com.intellij.uiDesigner.core.GridConstraints(6, 1, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(1000, -1), null, 0, false));
        description = new JTextPane();
        description.setBackground(new Color(-1973791));
        description.setForeground(new Color(-16777216));
        description.setText("Description");
        descriptionPanel.add(description, new com.intellij.uiDesigner.core.GridConstraints(7, 1, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, 100), null, 0, false));
        final JLabel label2 = new JLabel();
        Font label2Font = this.$$$getFont$$$("Calibri Light", Font.BOLD, 16, label2.getFont());
        if (label2Font != null) label2.setFont(label2Font);
        label2.setForeground(new Color(-592138));
        label2.setText("Location");
        descriptionPanel.add(label2, new com.intellij.uiDesigner.core.GridConstraints(9, 1, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label3 = new JLabel();
        Font label3Font = this.$$$getFont$$$("Calibri Light", Font.BOLD, 16, label3.getFont());
        if (label3Font != null) label3.setFont(label3Font);
        label3.setForeground(new Color(-592138));
        label3.setText("Salary");
        descriptionPanel.add(label3, new com.intellij.uiDesigner.core.GridConstraints(12, 1, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        salary = new JLabel();
        salary.setForeground(new Color(-592138));
        salary.setText("Salary");
        descriptionPanel.add(salary, new com.intellij.uiDesigner.core.GridConstraints(13, 1, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, new Dimension(-1, 100), 0, false));
        jobseekerButtons = new JPanel();
        jobseekerButtons.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        jobseekerButtons.setBackground(new Color(-13224648));
        descriptionPanel.add(jobseekerButtons, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        applyButton = new JButton();
        applyButton.setText("Apply");
        jobseekerButtons.add(applyButton, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, new Dimension(150, -1), 0, false));
        removeButton = new JButton();
        removeButton.setText("Remove Application");
        removeButton.setVisible(false);
        jobseekerButtons.add(removeButton, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, new Dimension(150, -1), 0, false));
        final JLabel label4 = new JLabel();
        Font label4Font = this.$$$getFont$$$("Calibri Light", Font.BOLD, 16, label4.getFont());
        if (label4Font != null) label4.setFont(label4Font);
        label4.setForeground(new Color(-592138));
        label4.setText("Categories");
        descriptionPanel.add(label4, new com.intellij.uiDesigner.core.GridConstraints(9, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        cat = new JLabel();
        cat.setForeground(new Color(-592138));
        cat.setText("Category");
        descriptionPanel.add(cat, new com.intellij.uiDesigner.core.GridConstraints(10, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, new Dimension(-1, 100), 0, false));
        location = new JLabel();
        location.setForeground(new Color(-592138));
        location.setText("Location");
        descriptionPanel.add(location, new com.intellij.uiDesigner.core.GridConstraints(10, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, new Dimension(-1, 100), 0, false));
        jobTitle = new JLabel();
        Font jobTitleFont = this.$$$getFont$$$("Calibri Light", -1, 22, jobTitle.getFont());
        if (jobTitleFont != null) jobTitle.setFont(jobTitleFont);
        jobTitle.setForeground(new Color(-592138));
        jobTitle.setText("Label");
        descriptionPanel.add(jobTitle, new com.intellij.uiDesigner.core.GridConstraints(4, 1, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer5 = new com.intellij.uiDesigner.core.Spacer();
        descriptionPanel.add(spacer5, new com.intellij.uiDesigner.core.GridConstraints(3, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    private Font $$$getFont$$$(String fontName, int style, int size, Font currentFont) {
        if (currentFont == null) return null;
        String resultName;
        if (fontName == null) {
            resultName = currentFont.getName();
        } else {
            Font testFont = new Font(fontName, Font.PLAIN, 10);
            if (testFont.canDisplay('a') && testFont.canDisplay('1')) {
                resultName = fontName;
            } else {
                resultName = currentFont.getName();
            }
        }
        Font font = new Font(resultName, style >= 0 ? style : currentFont.getStyle(), size >= 0 ? size : currentFont.getSize());
        boolean isMac = System.getProperty("os.name", "").toLowerCase(Locale.ENGLISH).startsWith("mac");
        Font fontWithFallback = isMac ? new Font(font.getFamily(), font.getStyle(), font.getSize()) : new StyleContext().getFont(font.getFamily(), font.getStyle(), font.getSize());
        return fontWithFallback instanceof FontUIResource ? fontWithFallback : new FontUIResource(fontWithFallback);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return descriptionPanel;
    }

    @Override
    public void update() {
        // Do nothing
    }

    @Override
    public String pageName() {
        return "Description";
    }

    @Override
    public JPanel getPanel() {
        return descriptionPanel;
    }
}
