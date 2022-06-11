package classes;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import javax.swing.text.StyleContext;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;

/*
 * Recruiter profile is what the job seeker can see of the recruiter.
 */
public class RecruiterProfilePage {
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
    private JLabel recruiterPhoneNo;
    private JLabel jobListingsLabel;
    private JPanel jobListingsPanel;
    private JPanel job1ListingPanel;
    private JLabel job1Title;
    private JLabel job1Score;
    private JLabel job1Company;
    private JPanel job2ListingPanel;
    private JLabel job2Title;
    private JLabel job2Score;
    private JLabel job2Company;
    private JPanel job3ListingPanel;
    private JLabel job3Title;
    private JLabel job3Score;
    private JLabel job3Company;
    private JPanel jobDescriptionPanel;
    private JLabel selectedJobLocation;
    private JLabel selectedJobCompensation;
    private JLabel selectJobCategory;
    private JButton applyButton;
    private JLabel selectedJobDescription;
    private JLabel recruiterProfileDescriptionText;
    private JButton viewButton;
    private JButton viewButton1;
    private JButton viewButton2;
    private JTextArea textArea1;


    /**
     * test recruiter profile
     */
    public RecruiterProfilePage() {
        Recruiter testRecruiter = new Recruiter();
        testRecruiter.setOrg("Not Seek");
        testRecruiter.setEmail("recruiter@notseek.com");
        testRecruiter.setRecruiterDescription("If you seek, you will find...");

        Job testJob = new Job();
        testJob.setJobTitle("Software Engineer");
        testJob.setRecruiter(testRecruiter);
        testJob.setCat("compSie");
        testJob.setJobDescription("This is a job. Please apply");
        testJob.setState("QLD");
        testJob.setSalary(125000);

        Job testJob1 = new Job();
        testJob1.setJobTitle("UI Designer");
        testJob1.setRecruiter(testRecruiter);
        testJob1.setCat("compSie");
        testJob1.setJobDescription("This is a job. Please apply");
        testJob1.setState("VIC");
        testJob1.setSalary(99000);

        Job testJob2 = new Job();
        testJob2.setJobTitle("Software Technician");
        testJob2.setRecruiter(testRecruiter);
        testJob2.setCat("AI");
        testJob2.setJobDescription("This is a job. Please apply");
        testJob2.setState("QLD");
        testJob2.setSalary(75000);


        recruiterOrg.setText(testRecruiter.getOrg());
        recruiterProfileDescriptionText.setText(testRecruiter.getRecruiterDescription());
        recruiterEmail.setText(testRecruiter.getEmail());
        recruiterWebsite.setText(testRecruiter.getOrg());
        job1Title.setText(testJob.jobTitle);
        job1Company.setText(testJob.getRecruiter().getOrg());
        job2Title.setText(testJob1.getJobTitle());
        job2Company.setText(testJob1.getRecruiter().getOrg());
        job3Title.setText(testJob2.jobTitle);
        job3Company.setText(testJob2.getRecruiter().getOrg());

        viewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedJobDescription.setText(testJob.getJobDescription());
                selectedJobCompensation.setText(String.valueOf(testJob.getSalary()));
                selectedJobLocation.setText(testJob.getState());
                selectJobCategory.setText(testJob.cat);
            }
        });

        viewButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedJobDescription.setText(testJob1.getJobDescription());
                selectedJobCompensation.setText(String.valueOf(testJob1.getSalary()));
                selectedJobLocation.setText(testJob1.getState());
                selectJobCategory.setText(testJob1.cat);
            }
        });

        viewButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedJobDescription.setText(testJob2.getJobDescription());
                selectedJobCompensation.setText(String.valueOf(testJob2.getSalary()));
                selectedJobLocation.setText(testJob2.getState());
                selectJobCategory.setText(testJob2.cat);
            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }


    public JPanel getRecruiterProfilePage() {
        return recruiterProfile;
    }

}


