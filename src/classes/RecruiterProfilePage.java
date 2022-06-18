package classes;


import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import javax.swing.text.StyleContext;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.stream.Collectors;

/*
 * Recruiter profile is what the job seeker can see of the recruiter.
 */
public class RecruiterProfilePage implements Page {
    private JPanel recruiterProfile;
    private JButton backButton;
    private JScrollPane recruiterProfileDescription;
    private JLabel whoWeAreLabel;
    private JLabel recruiterOrg;
    private JScrollPane jobDescription;
    private JLabel contactLabel;
    private JLabel recruiterEmail;
    private JLabel recruiterWebsite;
    private JLabel recruiterSocial;
    private JLabel jobListingsLabel;
    private JPanel jobListingsPanel;
    private JPanel job1ListingPanel;
    private JLabel job1Title;
    private JLabel job1Company;
    private JPanel job2ListingPanel;
    private JLabel job2Title;
    private JLabel job2Company;
    private JPanel job3ListingPanel;
    private JLabel job3Title;
    private JLabel job3Company;
    private JPanel jobDescriptionPanel;
    private JLabel selectedJobLocation;
    private JLabel selectedJobCompensation;
    private JLabel selectedJobCategory;
    private JButton applyButton;
    private JLabel selectedJobDescription;
    private JLabel recruiterProfileDescriptionText;
    private JButton viewButton;
    private JButton viewButton1;
    private JButton viewButton2;
    private JLabel descriptionLabel;
    Collection<Job> jobs;

    public RecruiterProfilePage(Recruiter recruiter) {
        recruiterOrg.setText(recruiter.getOrg());
        recruiterEmail.setText(recruiter.getEmail());
        recruiterWebsite.setText("www." + recruiter.getOrg() + ".com");
        recruiterSocial.setText("@" + recruiter.getOrg());

        //obtain collection of jobs by using recruiter email as key
        jobs = recruiter.getJobs();

        ArrayList<Job> jobList = new ArrayList<>(jobs);

        //each button pressed = will display job description and ability to apply

        if (jobList.get(0) != null) {
            job1Title.setText(jobList.get(0).getJobTitle());
            job1Company.setText(recruiter.getOrg());

            viewButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    selectedJobDescription.setText(jobList.get(0).getJobDescription());
                    selectedJobCompensation.setText(String.valueOf(jobList.get(0).getSalary()));
                    selectedJobLocation.setText(jobList.get(0).getStates().stream().map(s -> Character.toUpperCase(s.charAt(0)) +
                            s.substring(1)).collect(Collectors.joining(", ")));
                    //selectJobCategory.setText(jobList.get(0).getCat());
                    //selectedJobLocation.setText(jobList.get(0).getState());
                    selectedJobCategory.setText(jobList.get(0).getJobType());
                    descriptionLabel.setText(jobList.get(0).getJobTitle());
                }
            });
        }


        if (jobList.get(1) != null) {
            job2Title.setText(jobList.get(1).getJobTitle());
            job2Company.setText(recruiter.getOrg());

            viewButton1.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    selectedJobDescription.setText(jobList.get(1).getJobDescription());
                    selectedJobCompensation.setText(String.valueOf(jobList.get(1).getSalary()));
                    selectedJobLocation.setText(jobList.get(1).getStates().stream().map(s -> Character.toUpperCase(s.charAt(0)) +
                            s.substring(1)).collect(Collectors.joining(", ")));
                    //selectJobCategory.setText(jobList.get(1).getCat());
                    //selectedJobLocation.setText(jobList.get(1).getState());
                    selectedJobCategory.setText(jobList.get(1).getJobType());
                    descriptionLabel.setText(jobList.get(1).getJobTitle());
                }
            });
        }

        if (jobList.get(2) != null) {
            job3Title.setText(jobList.get(2).getJobTitle());
            job3Company.setText(recruiter.getOrg());
            viewButton2.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    selectedJobDescription.setText(jobList.get(2).getJobDescription());
                    selectedJobCompensation.setText(String.valueOf(jobList.get(2).getSalary()));
                    //selectedJobLocation.setText(jobList.get(2).getState());
                    selectedJobCategory.setText(jobList.get(2).getJobType());
                    descriptionLabel.setText(jobList.get(2).getJobTitle());
                    selectedJobLocation.setText(jobList.get(2).getStates().stream().map(s -> Character.toUpperCase(s.charAt(0)) +
                            s.substring(1)).collect(Collectors.joining(", ")));
                    //selectJobCategory.setText(jobList.get(2).getCat());
                }
            });
        }
    }


    public JPanel getRecruiterProfilePage() {
        return recruiterProfile;
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
        recruiterProfile = new JPanel();
        recruiterProfile.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(12, 5, new Insets(20, 20, 20, 20), -1, -1));
        recruiterProfile.setBackground(new Color(-13224648));
        recruiterProfile.setEnabled(false);
        recruiterProfile.setForeground(new Color(-5592406));
        recruiterProfileDescription = new JScrollPane();
        recruiterProfileDescription.setHorizontalScrollBarPolicy(30);
        recruiterProfile.add(recruiterProfileDescription, new com.intellij.uiDesigner.core.GridConstraints(2, 0, 8, 4, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(249, 350), null, 0, false));
        recruiterProfileDescriptionText = new JLabel();
        recruiterProfileDescriptionText.setText("Label");
        recruiterProfileDescriptionText.setVerticalAlignment(1);
        recruiterProfileDescription.setViewportView(recruiterProfileDescriptionText);
        jobDescription = new JScrollPane();
        jobDescription.setBackground(new Color(-330497));
        recruiterProfile.add(jobDescription, new com.intellij.uiDesigner.core.GridConstraints(11, 3, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        jobDescriptionPanel = new JPanel();
        jobDescriptionPanel.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(3, 2, new Insets(0, 0, 0, 0), -1, -1));
        jobDescriptionPanel.setBackground(new Color(-1973791));
        jobDescriptionPanel.setForeground(new Color(-13224648));
        jobDescription.setViewportView(jobDescriptionPanel);
        applyButton = new JButton();
        applyButton.setText("Apply");
        jobDescriptionPanel.add(applyButton, new com.intellij.uiDesigner.core.GridConstraints(2, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_EAST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), -1, -1));
        jobDescriptionPanel.add(panel1, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_NORTHWEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        selectedJobDescription = new JLabel();
        selectedJobDescription.setEnabled(false);
        selectedJobDescription.setText("");
        panel1.add(selectedJobDescription, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        descriptionLabel = new JLabel();
        descriptionLabel.setText("Description");
        panel1.add(descriptionLabel, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        selectedJobLocation = new JLabel();
        selectedJobLocation.setForeground(new Color(-13224648));
        selectedJobLocation.setText("Location");
        jobDescriptionPanel.add(selectedJobLocation, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(106, 16), null, 0, false));
        selectedJobCompensation = new JLabel();
        selectedJobCompensation.setForeground(new Color(-13224648));
        selectedJobCompensation.setText("Compensation");
        jobDescriptionPanel.add(selectedJobCompensation, new com.intellij.uiDesigner.core.GridConstraints(1, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_EAST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(106, 16), null, 0, false));
        selectedJobCategory = new JLabel();
        selectedJobCategory.setForeground(new Color(-13224648));
        selectedJobCategory.setText("Job Type");
        jobDescriptionPanel.add(selectedJobCategory, new com.intellij.uiDesigner.core.GridConstraints(2, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 1, false));
        jobListingsPanel = new JPanel();
        jobListingsPanel.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(5, 1, new Insets(0, 0, 0, 0), -1, -1));
        recruiterProfile.add(jobListingsPanel, new com.intellij.uiDesigner.core.GridConstraints(11, 0, 1, 3, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        job1ListingPanel = new JPanel();
        job1ListingPanel.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(2, 2, new Insets(0, 0, 0, 0), -1, -1));
        jobListingsPanel.add(job1ListingPanel, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        job1Title = new JLabel();
        job1Title.setText("Label");
        job1ListingPanel.add(job1Title, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(135, 16), null, 0, false));
        job1Company = new JLabel();
        job1Company.setText("Label");
        job1ListingPanel.add(job1Company, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(135, 16), null, 0, false));
        viewButton = new JButton();
        viewButton.setText("View");
        job1ListingPanel.add(viewButton, new com.intellij.uiDesigner.core.GridConstraints(1, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_EAST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), new Dimension(150, -1), 0, false));
        job2ListingPanel = new JPanel();
        job2ListingPanel.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(2, 2, new Insets(0, 0, 0, 0), -1, -1));
        jobListingsPanel.add(job2ListingPanel, new com.intellij.uiDesigner.core.GridConstraints(2, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        job2Title = new JLabel();
        job2Title.setText("Label");
        job2ListingPanel.add(job2Title, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(133, 16), null, 0, false));
        job2Company = new JLabel();
        job2Company.setText("Label");
        job2ListingPanel.add(job2Company, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(133, 16), null, 0, false));
        viewButton1 = new JButton();
        viewButton1.setText("View");
        job2ListingPanel.add(viewButton1, new com.intellij.uiDesigner.core.GridConstraints(1, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_EAST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), new Dimension(150, -1), 0, false));
        job3ListingPanel = new JPanel();
        job3ListingPanel.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(2, 2, new Insets(0, 0, 0, 0), -1, -1));
        jobListingsPanel.add(job3ListingPanel, new com.intellij.uiDesigner.core.GridConstraints(4, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        job3Title = new JLabel();
        job3Title.setText("Label");
        job3ListingPanel.add(job3Title, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(133, 16), null, 0, false));
        job3Company = new JLabel();
        job3Company.setText("Label");
        job3ListingPanel.add(job3Company, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(133, 16), null, 0, false));
        viewButton2 = new JButton();
        viewButton2.setText("View");
        job3ListingPanel.add(viewButton2, new com.intellij.uiDesigner.core.GridConstraints(1, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_EAST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer1 = new com.intellij.uiDesigner.core.Spacer();
        jobListingsPanel.add(spacer1, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer2 = new com.intellij.uiDesigner.core.Spacer();
        jobListingsPanel.add(spacer2, new com.intellij.uiDesigner.core.GridConstraints(3, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        whoWeAreLabel = new JLabel();
        Font whoWeAreLabelFont = this.$$$getFont$$$(null, Font.BOLD, 16, whoWeAreLabel.getFont());
        if (whoWeAreLabelFont != null) whoWeAreLabel.setFont(whoWeAreLabelFont);
        whoWeAreLabel.setForeground(new Color(-592138));
        whoWeAreLabel.setText("Who We Are:");
        recruiterProfile.add(whoWeAreLabel, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, 1, 1, null, new Dimension(249, 16), null, 0, false));
        jobListingsLabel = new JLabel();
        Font jobListingsLabelFont = this.$$$getFont$$$(null, Font.BOLD, 16, jobListingsLabel.getFont());
        if (jobListingsLabelFont != null) jobListingsLabel.setFont(jobListingsLabelFont);
        jobListingsLabel.setForeground(new Color(-592138));
        jobListingsLabel.setText("Job Listings");
        recruiterProfile.add(jobListingsLabel, new com.intellij.uiDesigner.core.GridConstraints(10, 0, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_SOUTHWEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        recruiterOrg = new JLabel();
        Font recruiterOrgFont = this.$$$getFont$$$("Calibri Light", -1, 22, recruiterOrg.getFont());
        if (recruiterOrgFont != null) recruiterOrg.setFont(recruiterOrgFont);
        recruiterOrg.setForeground(new Color(-592138));
        recruiterOrg.setText("Label");
        recruiterProfile.add(recruiterOrg, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 3, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(54, 16), null, 0, false));
        contactLabel = new JLabel();
        Font contactLabelFont = this.$$$getFont$$$(null, Font.BOLD, 16, contactLabel.getFont());
        if (contactLabelFont != null) contactLabel.setFont(contactLabelFont);
        contactLabel.setForeground(new Color(-592138));
        contactLabel.setText("Contact");
        recruiterProfile.add(contactLabel, new com.intellij.uiDesigner.core.GridConstraints(1, 4, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_NORTHWEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label1 = new JLabel();
        Font label1Font = this.$$$getFont$$$(null, Font.BOLD, -1, label1.getFont());
        if (label1Font != null) label1.setFont(label1Font);
        label1.setForeground(new Color(-592138));
        label1.setText("Email:");
        recruiterProfile.add(label1, new com.intellij.uiDesigner.core.GridConstraints(2, 4, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        recruiterEmail = new JLabel();
        recruiterEmail.setForeground(new Color(-592138));
        recruiterEmail.setText("Email");
        recruiterProfile.add(recruiterEmail, new com.intellij.uiDesigner.core.GridConstraints(3, 4, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_NORTHWEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        recruiterWebsite = new JLabel();
        recruiterWebsite.setForeground(new Color(-592138));
        recruiterWebsite.setText("Website");
        recruiterProfile.add(recruiterWebsite, new com.intellij.uiDesigner.core.GridConstraints(5, 4, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_NORTHWEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label2 = new JLabel();
        Font label2Font = this.$$$getFont$$$(null, Font.BOLD, -1, label2.getFont());
        if (label2Font != null) label2.setFont(label2Font);
        label2.setForeground(new Color(-592138));
        label2.setText("Website:");
        recruiterProfile.add(label2, new com.intellij.uiDesigner.core.GridConstraints(4, 4, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label3 = new JLabel();
        Font label3Font = this.$$$getFont$$$(null, Font.BOLD, -1, label3.getFont());
        if (label3Font != null) label3.setFont(label3Font);
        label3.setForeground(new Color(-592138));
        label3.setText("Socials:");
        recruiterProfile.add(label3, new com.intellij.uiDesigner.core.GridConstraints(6, 4, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        recruiterSocial = new JLabel();
        recruiterSocial.setForeground(new Color(-592138));
        recruiterSocial.setText("Socials");
        recruiterProfile.add(recruiterSocial, new com.intellij.uiDesigner.core.GridConstraints(7, 4, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_NORTHWEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
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
        return recruiterProfile;
    }

    @Override
    public void update() {
        // Do Nothing
    }

    @Override
    public String pageName() {
        return "RecruiterProfile";
    }

    @Override
    public JPanel getPanel() {
        return recruiterProfile;
    }
}




