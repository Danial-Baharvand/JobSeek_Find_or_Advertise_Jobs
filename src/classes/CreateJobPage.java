package classes;

import com.google.common.collect.Sets;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import javax.swing.text.StyleContext;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;
import java.util.stream.Collectors;

// TODO: publish functionality
public class CreateJobPage implements Page {
    private ClearingTextField jobTitleTB;
    private JPanel createJobPanel;
    private ClearingTextArea jobDescTA;
    private JComboBox jobTypeCB;
    private ClearingTextField salaryTB;
    private JButton saveBtn;
    private JButton publishBtn;
    private ClearingTextField keywordsTB;
    private JPanel catOptions;
    private JPanel statesPanel;
    private JScrollPane statesScroller;
    private Job job;

    public CreateJobPage() {
        setUpPage();
        addListeners(false);
    }

    public CreateJobPage(Job job) {
        setUpPage();
        this.job = job;
        jobTitleTB.setText(job.getJobTitle());
        jobTitleTB.setDefaultText("Job Listing Title", true);
        GuiHelper.setSelectedOptions(statesPanel, job.getStates());
        jobTypeCB.setSelectedItem(job.getJobType());
        jobDescTA.setText(job.getJobDescription());
        jobDescTA.setDefaultText("Job Description", true);
        GuiHelper.setSelectedOptions(catOptions, job.categories().names());
        keywordsTB.setText(job.getKeywords());
        keywordsTB.setDefaultText("Type in keywords separated by space (\" \")", true);
        salaryTB.setText(String.valueOf(job.getSalary()));
        salaryTB.setDefaultText("Salary", true);

        addListeners(true);
    }

    private void addListeners(boolean edit) {
        Recruiter recruiter = (Recruiter) Runtime.accountManager().getCurrentUser();
        saveBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (edit) {
                        job.setPublished(false);
                        recruiter.removeJob(job);
                    }
                    createJob(false);
                    IO.writeRecruiters();
                    IO.writeJobs();
                    Runtime.getPagesVisited().clear();
                    Runtime.showRecruiterHome();
                    JOptionPane.showMessageDialog(null, "Job was saved!");
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Please fill in all options correctly");
                }
            }
        });
        publishBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (edit) {
                        job.setPublished(false);
                        recruiter.removeJob(job);
                    }
                    createJob(true);
                    IO.writeRecruiters();
                    IO.writeJobs();
                    Runtime.getPagesVisited().clear();
                    Runtime.showRecruiterHome();
                    JOptionPane.showMessageDialog(null, "Job was published!");
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Please fill in all options correctly");
                }
            }
        });
    }

    private void setUpPage() {
        GuiHelper.createOptionBox(statesPanel, Config.STATES);
        GuiHelper.createOptionBox(catOptions, Runtime.accountManager().getCategories().names());
        jobTypeCB.setRenderer(new PromptComboBoxRenderer("Job Type"));
    }

    private void createJob(boolean publish) throws Exception {
        Job newJob = new Job();
        newJob.setJobTitle(jobTitleTB.forceGetText());
        newJob.setRecruiter((Recruiter) Runtime.accountManager().getCurrentUser());
        newJob.setJobDescription(jobDescTA.forceGetText());
        newJob.setStates(Sets.newHashSet(GuiHelper.getSelectedOptions(statesPanel)));
        newJob.setSalary(Integer.parseInt(salaryTB.forceGetText()));
        newJob.setJobType(jobTypeCB.getSelectedItem().toString());
        newJob.setKeywords(keywordsTB.forceGetText());
        newJob.setPublished(publish);
        if (((Recruiter) Runtime.accountManager().getCurrentUser()).hasJob(newJob.getJobTitle())) {
            throw new Exception("Duplicate Job: You already have a job with the same title!");
        }
        ((Recruiter) Runtime.accountManager().getCurrentUser()).addJob(newJob);
        newJob.setCategories(new CategoryManager());
        newJob.categories().addAll(GuiHelper.getSelectedOptions(catOptions).stream().map(c -> Runtime.accountManager()
                .getCategories().getByName(c)).collect(Collectors.toSet()));
        Runtime.accountManager().getJobs().put(newJob.getID(), newJob);
        IO.addToDB(newJob);
    }

    public CreateJobPage getPage() {
        return this;
    }

    public JPanel getCreateJobPanel() {
        return createJobPanel;
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
        createJobPanel = new JPanel();
        createJobPanel.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(10, 2, new Insets(20, 20, 20, 20), -1, -1));
        createJobPanel.setBackground(new Color(-13224648));
        createJobPanel.setEnabled(true);
        jobTitleTB = new ClearingTextField();
        jobTitleTB.setBackground(new Color(-1973791));
        jobTitleTB.setForeground(new Color(-13224648));
        jobTitleTB.setText("Job Listing Title");
        createJobPanel.add(jobTitleTB, new com.intellij.uiDesigner.core.GridConstraints(2, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(222, 30), null, 0, false));
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        panel1.setBackground(new Color(-13224648));
        createJobPanel.add(panel1, new com.intellij.uiDesigner.core.GridConstraints(9, 0, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        saveBtn = new JButton();
        saveBtn.setText("Save");
        panel1.add(saveBtn, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), new Dimension(150, -1), 0, false));
        publishBtn = new JButton();
        publishBtn.setEnabled(true);
        publishBtn.setText("Publish");
        panel1.add(publishBtn, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), new Dimension(150, -1), 0, false));
        final JLabel label1 = new JLabel();
        Font label1Font = this.$$$getFont$$$("Calibri Light", Font.BOLD, 16, label1.getFont());
        if (label1Font != null) label1.setFont(label1Font);
        label1.setForeground(new Color(-592138));
        label1.setText("Description");
        createJobPanel.add(label1, new com.intellij.uiDesigner.core.GridConstraints(3, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label2 = new JLabel();
        Font label2Font = this.$$$getFont$$$("Calibri Light", -1, 22, label2.getFont());
        if (label2Font != null) label2.setFont(label2Font);
        label2.setForeground(new Color(-592138));
        label2.setText("Create Job");
        createJobPanel.add(label2, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label3 = new JLabel();
        Font label3Font = this.$$$getFont$$$("Calibri Light", Font.BOLD, 16, label3.getFont());
        if (label3Font != null) label3.setFont(label3Font);
        label3.setForeground(new Color(-592138));
        label3.setText("Title");
        createJobPanel.add(label3, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label4 = new JLabel();
        Font label4Font = this.$$$getFont$$$("Calibri Light", Font.BOLD, 16, label4.getFont());
        if (label4Font != null) label4.setFont(label4Font);
        label4.setForeground(new Color(-592138));
        label4.setText("Keywords");
        createJobPanel.add(label4, new com.intellij.uiDesigner.core.GridConstraints(7, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        keywordsTB = new ClearingTextField();
        keywordsTB.setBackground(new Color(-1973791));
        keywordsTB.setForeground(new Color(-13224648));
        keywordsTB.setText("Type in keywords separated by space (\" \")");
        keywordsTB.setToolTipText("Coding Java SQL");
        createJobPanel.add(keywordsTB, new com.intellij.uiDesigner.core.GridConstraints(8, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(-1, 25), null, 0, false));
        final JScrollPane scrollPane1 = new JScrollPane();
        createJobPanel.add(scrollPane1, new com.intellij.uiDesigner.core.GridConstraints(4, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        catOptions = new JPanel();
        catOptions.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        scrollPane1.setViewportView(catOptions);
        final JLabel label5 = new JLabel();
        Font label5Font = this.$$$getFont$$$("Calibri Light", Font.BOLD, 16, label5.getFont());
        if (label5Font != null) label5.setFont(label5Font);
        label5.setForeground(new Color(-592138));
        label5.setText("Categories");
        createJobPanel.add(label5, new com.intellij.uiDesigner.core.GridConstraints(3, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        jobTypeCB = new JComboBox();
        jobTypeCB.setBackground(new Color(-1973791));
        jobTypeCB.setForeground(new Color(-13224648));
        final DefaultComboBoxModel defaultComboBoxModel1 = new DefaultComboBoxModel();
        defaultComboBoxModel1.addElement("Full-Time");
        defaultComboBoxModel1.addElement("Part-Time");
        defaultComboBoxModel1.addElement("Casual");
        defaultComboBoxModel1.addElement("Intern");
        jobTypeCB.setModel(defaultComboBoxModel1);
        jobTypeCB.setSelectedIndex(-1);
        createJobPanel.add(jobTypeCB, new com.intellij.uiDesigner.core.GridConstraints(2, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label6 = new JLabel();
        Font label6Font = this.$$$getFont$$$("Calibri Light", Font.BOLD, 16, label6.getFont());
        if (label6Font != null) label6.setFont(label6Font);
        label6.setForeground(new Color(-592138));
        label6.setText("Type");
        createJobPanel.add(label6, new com.intellij.uiDesigner.core.GridConstraints(1, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label7 = new JLabel();
        Font label7Font = this.$$$getFont$$$("Calibri Light", Font.BOLD, 16, label7.getFont());
        if (label7Font != null) label7.setFont(label7Font);
        label7.setForeground(new Color(-592138));
        label7.setText("States");
        createJobPanel.add(label7, new com.intellij.uiDesigner.core.GridConstraints(5, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        statesScroller = new JScrollPane();
        createJobPanel.add(statesScroller, new com.intellij.uiDesigner.core.GridConstraints(6, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        statesPanel = new JPanel();
        statesPanel.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        statesScroller.setViewportView(statesPanel);
        salaryTB = new ClearingTextField();
        salaryTB.setBackground(new Color(-1973791));
        salaryTB.setForeground(new Color(-13224648));
        salaryTB.setText("Salary");
        createJobPanel.add(salaryTB, new com.intellij.uiDesigner.core.GridConstraints(8, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, new Dimension(100, 25), null, null, 0, false));
        final JLabel label8 = new JLabel();
        Font label8Font = this.$$$getFont$$$("Calibri Light", Font.BOLD, 16, label8.getFont());
        if (label8Font != null) label8.setFont(label8Font);
        label8.setForeground(new Color(-592138));
        label8.setText("Salary");
        createJobPanel.add(label8, new com.intellij.uiDesigner.core.GridConstraints(7, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JScrollPane scrollPane2 = new JScrollPane();
        createJobPanel.add(scrollPane2, new com.intellij.uiDesigner.core.GridConstraints(4, 0, 3, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        jobDescTA = new ClearingTextArea();
        jobDescTA.setBackground(new Color(-1973791));
        Font jobDescTAFont = this.$$$getFont$$$(null, -1, -1, jobDescTA.getFont());
        if (jobDescTAFont != null) jobDescTA.setFont(jobDescTAFont);
        jobDescTA.setForeground(new Color(-13224648));
        jobDescTA.setText("Job Description");
        scrollPane2.setViewportView(jobDescTA);
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
        return createJobPanel;
    }

    @Override
    public void update() {
        // do nothing
    }

    @Override
    public String pageName() {
        return "Create a Job";
    }

    @Override
    public JPanel getPanel() {
        return createJobPanel;
    }
}
