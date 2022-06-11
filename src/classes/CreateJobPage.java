package classes;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import javax.swing.text.StyleContext;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;import java.awt.event.FocusAdapter;
import java.util.Locale;

public class CreateJobPage {
    private ClearingTextField jobTitleTB;
    private JPanel CreateJobPanel;
    private JComboBox catCB;
    private JComboBox<String> stateCB;
    private ClearingTextArea jobDescTA;
    private JComboBox jobTypeCB;
    private JButton catButton;
    private ClearingTextField salaryTB;
    private JButton saveBtn;
    private JButton publishBtn;
    private ClearingTextField keywordsTB;
    private JTextArea textArea1;

    public CreateJobPage() {
        Runtime.getJobs().readFromFile(Config.DT_JOBS);
        Runtime.categories.readFromFile(Config.DT_CATEGORIES);
        stateCB.setRenderer(new PromptComboBoxRenderer("Location"));
        catCB.setRenderer(new PromptComboBoxRenderer("Category"));
        jobTypeCB.setRenderer(new PromptComboBoxRenderer("Job Type"));
        updateCategories();
        catButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Runtime.showEditCategoryPage(new JFrame(), getPage());
            }
        });
        catCB.addFocusListener(new FocusAdapter() {
        });
        saveBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createJob(false);
            }
        });
        publishBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createJob(true);
            }
        });
    }

    private void createJob(boolean publish) {

        try {
            Job newJob = new Job();
            newJob.setJobTitle(jobTitleTB.forceGetText());
            newJob.setRecruiter((Recruiter) Runtime.accountManager().getCurrentUser());
            newJob.setCat(catCB.getSelectedItem().toString());
            newJob.setJobDescription(jobDescTA.forceGetText());
            newJob.setState(stateCB.getSelectedItem().toString());
            newJob.setSalary(Integer.parseInt(salaryTB.forceGetText()));
            newJob.setJobType(jobTypeCB.getSelectedItem().toString());
            newJob.setKeywords(keywordsTB.forceGetText());
            newJob.setPublished(publish);
            System.out.println(newJob);
            Runtime.getJobs().add(newJob.getRecruiter().getEmail(), newJob);
            Runtime.getJobs().writeToFile(Config.DT_JOBS);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Please fill in all fields");
        }

    }

    public CreateJobPage getPage() {
        return this;
    }

    public void updateCategories() {
        catCB.setModel(new DefaultComboBoxModel<>(Runtime.categories.getUserCategories(Runtime.accountManager().
                getCurrentUser()).toArray(new String[0])));
        catCB.setSelectedIndex(-1);
        catCB.setRenderer(new PromptComboBoxRenderer("Category"));


    }

    public JPanel getCreateJobPanel() {
        return CreateJobPanel;
    }
}
