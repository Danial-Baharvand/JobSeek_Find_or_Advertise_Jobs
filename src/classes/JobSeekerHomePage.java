package classes;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import javax.swing.text.StyleContext;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.*;

public class JobSeekerHomePage implements Page {
    private JPanel jobSeekerProfile;
    private JButton appliedJobsButton;
    private JButton jobInvitationsButton;
    private JButton uploadResumeButton;
    private JButton addButton;
    private JButton deleteButton;
    private JTextField skillsInput;
    private JTextArea skillsListing;
    private JLabel welcomeLabel;
    private JLabel nameLabel;
    private JPanel skillsList;
    private JScrollPane skillsListScrollPane;
    private JLabel resumeFileAddress;
    private JButton viewResumeButton;
    private JButton deleteButton1;
    private JButton viewButton;


    public JobSeekerHomePage() {
        JobSeeker currentUser = (JobSeeker) Runtime.accountManager().getCurrentUser();
        GuiHelper.createOptionBox(skillsList, new ArrayList<>(currentUser.getSkills()));
        nameLabel.setText(currentUser.getFullName());

        if (currentUser.getResumeFile() == null)
            resumeFileAddress.setText("No Resume Uploaded");


        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //add skills to jobseekers skills list

                if (!skillsInput.getText().isEmpty()) {
                    String sk = skillsInput.getText();
                    currentUser.addSkill(sk.toLowerCase());
                    GuiHelper.createOptionBox(skillsList, new ArrayList<>(currentUser.getSkills()));
                    skillsInput.setText("");
                    Runtime.accountManager().getSkills().put(currentUser.getEmail(), sk);
                    Runtime.accountManager().getSkills().writeToFile(Config.DT_SKILLS);
                }
            }

        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //delete from skills arraylist based on text entered in textpanel
                Set<String> strings = currentUser.getSkills();
                for (String s : GuiHelper.getSelectedOptions(skillsList)) {
                    strings.remove(s);
                    Runtime.accountManager().getSkills().remove(currentUser.getEmail(), s);
                }
                GuiHelper.createOptionBox(skillsList, new ArrayList<>(currentUser.getSkills()));
                Runtime.accountManager().getSkills().writeToFile(Config.DT_SKILLS);

            }
        });


        uploadResumeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser file_upload = new JFileChooser();
                int res = file_upload.showSaveDialog(null);

                if (res == JFileChooser.APPROVE_OPTION) {
                    File file_absolute_path = new File(file_upload.getSelectedFile().getAbsolutePath());
                    File file_name = new File(file_upload.getSelectedFile().getName());

                    //Code to duplicate uploaded file to project resume folder
                    //Files.copy(file_absolute_path, "src/resumes");
                    //Code to set jobseeker's resume
                    ((JobSeeker) Runtime.accountManager().getCurrentUser()).setResumeFile(file_absolute_path.toString());
                    resumeFileAddress.setText(file_name.toString());
                }


            }
        });

        /*viewResumeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JFileChooser file_chooser = new JFileChooser();
                StringBuilder sb = new StringBuilder();

                if (file_chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                    File file = file_chooser.getSelectedFile();
                    Scanner input = null;
                    try {
                        input = new Scanner(file);
                    } catch (FileNotFoundException fileNotFoundException) {
                        fileNotFoundException.printStackTrace();
                    }

                    while (input.hasNext()) {
                        sb.append(input.nextLine());
                        sb.append("\n");

                    }
                    input.close();
                } else {
                    sb.append("No file was selected");
                }
            }
        });*/

        deleteButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ((JobSeeker) Runtime.accountManager().getCurrentUser()).setResumeFile(null);
                if (currentUser.getResumeFile() == null)
                    resumeFileAddress.setText("No Resume Uploaded");
            }
        });

        viewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (((JobSeeker) Runtime.accountManager().getCurrentUser()).getResumeFile() != null) {
                    JFileChooser file_open = new JFileChooser();
                    int res = file_open.showOpenDialog(null);
                    if (res == JFileChooser.APPROVE_OPTION) {
                        File selectedFile = file_open.getSelectedFile();
                        System.out.println("Selected file: " + selectedFile.getAbsolutePath());
                    }
                }
            }
        });
        appliedJobsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Search appliedSearch = new Search(Runtime.accountManager().getJobApplications().
                        get(Runtime.accountManager().getCurrentUser().getEmail()));
                appliedSearch.setScores();
                ArrayList<ScoredJob> jobList = appliedSearch.getScoredJobs();
                Runtime.showSearchResultsPage(jobList);
            }
        });
        jobInvitationsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Search invitedSearch = new Search(Runtime.accountManager().getJobInvitations().
                        get(Runtime.accountManager().getCurrentUser().getEmail()));
                invitedSearch.setScores();
                ArrayList<ScoredJob> jobList = invitedSearch.getScoredJobs();
                Runtime.showSearchResultsPage(jobList);
            }
        });
    }


    public ArrayList<String> getSelectedOptions(JPanel optionsPanel) {
        ArrayList<String> selectedOptions = new ArrayList<>();
        Component[] allOptions = optionsPanel.getComponents();
        for (Component option : allOptions) {
            if (((JCheckBox) option).isSelected()) {
                selectedOptions.add(((JCheckBox) option).getText());
            }
        }
        return selectedOptions;
    }

    public void deleteSelectedOptions(JPanel optionsPanel) {
        ArrayList<String> selectedOptions = new ArrayList<>();
        Component[] allOptions = optionsPanel.getComponents();
        for (Component option : allOptions) {
            if (((JCheckBox) option).isSelected()) {
                selectedOptions.remove(((JCheckBox) option).getText());
            }
        }
    }

    /*public String getRelativePath(Path path1, Path path2){
        Path relativePath = absolutePath2.relativize()
    }*/


    public JPanel getJobSeekerProfile() {
        return jobSeekerProfile;
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
        jobSeekerProfile = new JPanel();
        jobSeekerProfile.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(9, 3, new Insets(20, 20, 20, 20), -1, -1));
        jobSeekerProfile.setBackground(new Color(-13224648));
        addButton = new JButton();
        addButton.setText("Add");
        jobSeekerProfile.add(addButton, new com.intellij.uiDesigner.core.GridConstraints(3, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(100, -1), new Dimension(150, -1), 0, false));
        welcomeLabel = new JLabel();
        Font welcomeLabelFont = this.$$$getFont$$$("Calibri Light", -1, 16, welcomeLabel.getFont());
        if (welcomeLabelFont != null) welcomeLabel.setFont(welcomeLabelFont);
        welcomeLabel.setForeground(new Color(-592138));
        welcomeLabel.setText("Welcome Back:");
        welcomeLabel.setVerticalAlignment(0);
        jobSeekerProfile.add(welcomeLabel, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        nameLabel = new JLabel();
        Font nameLabelFont = this.$$$getFont$$$("Calibri Light", -1, 22, nameLabel.getFont());
        if (nameLabelFont != null) nameLabel.setFont(nameLabelFont);
        nameLabel.setForeground(new Color(-592138));
        nameLabel.setText("Label");
        jobSeekerProfile.add(nameLabel, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 3, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        skillsListScrollPane = new JScrollPane();
        jobSeekerProfile.add(skillsListScrollPane, new com.intellij.uiDesigner.core.GridConstraints(4, 0, 3, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_NORTH, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(1000, 700), new Dimension(-1, 700), 0, false));
        skillsList = new JPanel();
        skillsList.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        skillsList.setBackground(new Color(-1973791));
        skillsList.setForeground(new Color(-13224648));
        skillsListScrollPane.setViewportView(skillsList);
        skillsInput = new ClearingTextField();
        skillsInput.setBackground(new Color(-1973791));
        skillsInput.setForeground(new Color(-13224648));
        skillsInput.setText("Type in your skills");
        jobSeekerProfile.add(skillsInput, new com.intellij.uiDesigner.core.GridConstraints(3, 0, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_NORTH, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(-1, 25), null, 0, false));
        deleteButton = new JButton();
        deleteButton.setText("Delete");
        jobSeekerProfile.add(deleteButton, new com.intellij.uiDesigner.core.GridConstraints(4, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(100, -1), new Dimension(150, -1), 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer1 = new com.intellij.uiDesigner.core.Spacer();
        jobSeekerProfile.add(spacer1, new com.intellij.uiDesigner.core.GridConstraints(5, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(-1, 200), null, 0, false));
        appliedJobsButton = new JButton();
        appliedJobsButton.setText("Applied Jobs");
        jobSeekerProfile.add(appliedJobsButton, new com.intellij.uiDesigner.core.GridConstraints(7, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_NORTHWEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, new Dimension(120, -1), new Dimension(150, -1), new Dimension(150, -1), 0, false));
        jobInvitationsButton = new JButton();
        jobInvitationsButton.setText("Job Invitations");
        jobSeekerProfile.add(jobInvitationsButton, new com.intellij.uiDesigner.core.GridConstraints(8, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_NORTHWEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, new Dimension(120, -1), new Dimension(150, -1), new Dimension(150, -1), 0, false));
        final JLabel label1 = new JLabel();
        Font label1Font = this.$$$getFont$$$("Calibri Light", Font.BOLD, 16, label1.getFont());
        if (label1Font != null) label1.setFont(label1Font);
        label1.setForeground(new Color(-592138));
        label1.setText("Skills");
        jobSeekerProfile.add(label1, new com.intellij.uiDesigner.core.GridConstraints(2, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_NORTHWEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, new Dimension(-1, 20), new Dimension(-1, 20), 0, false));
        resumeFileAddress = new JLabel();
        Font resumeFileAddressFont = this.$$$getFont$$$("Calibri Light", -1, 16, resumeFileAddress.getFont());
        if (resumeFileAddressFont != null) resumeFileAddress.setFont(resumeFileAddressFont);
        resumeFileAddress.setForeground(new Color(-592138));
        resumeFileAddress.setText("No Resume Uploaded");
        jobSeekerProfile.add(resumeFileAddress, new com.intellij.uiDesigner.core.GridConstraints(7, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_NORTHEAST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        uploadResumeButton = new JButton();
        uploadResumeButton.setText("Upload/Edit Resume");
        jobSeekerProfile.add(uploadResumeButton, new com.intellij.uiDesigner.core.GridConstraints(8, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_NORTHEAST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, new Dimension(150, -1), 0, false));
        viewButton = new JButton();
        viewButton.setText("View");
        jobSeekerProfile.add(viewButton, new com.intellij.uiDesigner.core.GridConstraints(7, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_NORTHWEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(100, -1), new Dimension(150, -1), 0, false));
        deleteButton1 = new JButton();
        deleteButton1.setText("Delete");
        deleteButton1.setVerticalAlignment(0);
        jobSeekerProfile.add(deleteButton1, new com.intellij.uiDesigner.core.GridConstraints(8, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_NORTHWEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(100, -1), new Dimension(150, -1), 0, false));
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
        return jobSeekerProfile;
    }

    @Override
    public void update() {
        // Do nothing
    }

    @Override
    public String pageName() {
        return "Job Seeker Home";
    }

    @Override
    public JPanel getPanel() {
        return jobSeekerProfile;
    }
}
