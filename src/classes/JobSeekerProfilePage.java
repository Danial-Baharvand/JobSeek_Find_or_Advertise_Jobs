package classes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class JobSeekerProfilePage {
    private JPanel JobSeekerProfile;
    private JButton backButton;
    private JButton appliedJobsButton;
    private JButton jobInterviewsButton;
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


    public JobSeekerProfilePage() {
        JobSeeker currentUser = (JobSeeker) Runtime.accountManager().getCurrentUser();
        Runtime.getSkills().readFromFile(Config.DT_SKILLS);
        currentUser.getSkills().clear();
        for (Object skill : Runtime.getSkills().getMap().get(currentUser.getEmail())) {
            String stringSkill = (String) skill;
            currentUser.getSkills().add(stringSkill);
        }
        GuiHelper.createOptionBox(skillsList, new ArrayList<>(currentUser.getSkills()));
        skillsList.updateUI();
        nameLabel.setText(currentUser.getFullName());

        if (js.getResumeFile() == null)
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
                    skillsList.updateUI();
                    Runtime.getSkills().add(currentUser.getEmail(), sk);
                    Runtime.getSkills().writeToFile(Config.DT_SKILLS);
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
                    Runtime.getSkills().remove(currentUser.getEmail(), s);
                }
                //refreshOptionBox(skillsList, currentUser.getSkills());
                GuiHelper.createOptionBox(skillsList, new ArrayList<>(currentUser.getSkills()));
                skillsList.updateUI();
                Runtime.getSkills().writeToFile(Config.DT_SKILLS);

            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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
                if (js.getResumeFile() == null)
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


    public void refreshOptionBox(JPanel optionsPanel, Set<String> options) {
        optionsPanel.removeAll();
        optionsPanel.repaint();
        for (String option : options) {
            optionsPanel.add(new JCheckBox(option));
        }
        optionsPanel.updateUI();

    }

    /*public String getRelativePath(Path path1, Path path2){
        Path relativePath = absolutePath2.relativize()
    }*/


    public JPanel getJobSeekerProfile() {
        return JobSeekerProfile;
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
        JobSeekerProfile = new JPanel();
        JobSeekerProfile.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(10, 6, new Insets(0, 0, 0, 0), -1, -1));
        backButton = new JButton();
        backButton.setText("Back");
        JobSeekerProfile.add(backButton, new com.intellij.uiDesigner.core.GridConstraints(1, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        jobInterviewsButton = new JButton();
        jobInterviewsButton.setText("Job Interviews");
        JobSeekerProfile.add(jobInterviewsButton, new com.intellij.uiDesigner.core.GridConstraints(8, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        addButton = new JButton();
        addButton.setText("Add");
        JobSeekerProfile.add(addButton, new com.intellij.uiDesigner.core.GridConstraints(3, 3, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        appliedJobsButton = new JButton();
        appliedJobsButton.setText("Applied Jobs");
        JobSeekerProfile.add(appliedJobsButton, new com.intellij.uiDesigner.core.GridConstraints(7, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        welcomeLabel = new JLabel();
        welcomeLabel.setText("Welcome Back:");
        JobSeekerProfile.add(welcomeLabel, new com.intellij.uiDesigner.core.GridConstraints(1, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        nameLabel = new JLabel();
        nameLabel.setText("Label");
        JobSeekerProfile.add(nameLabel, new com.intellij.uiDesigner.core.GridConstraints(2, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        skillsListScrollPane = new JScrollPane();
        JobSeekerProfile.add(skillsListScrollPane, new com.intellij.uiDesigner.core.GridConstraints(4, 2, 3, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        JobSeekerProfile.add(skillsListScrollPane, new com.intellij.uiDesigner.core.GridConstraints(4, 2, 2, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, new Dimension(100, 100), null, null, 0, false));
        skillsList = new JPanel();
        skillsList.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        skillsListScrollPane.setViewportView(skillsList);
        skillsInput = new ClearingTextField();
        skillsInput.setText("");
        JobSeekerProfile.add(skillsInput, new com.intellij.uiDesigner.core.GridConstraints(3, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label1 = new JLabel();
        label1.setText("Skills:");
        JobSeekerProfile.add(label1, new com.intellij.uiDesigner.core.GridConstraints(4, 1, 2, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label2 = new JLabel();
        label2.setText("Enter skills to add/delete");
        JobSeekerProfile.add(label2, new com.intellij.uiDesigner.core.GridConstraints(3, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer1 = new com.intellij.uiDesigner.core.Spacer();
        JobSeekerProfile.add(spacer1, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 8, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer2 = new com.intellij.uiDesigner.core.Spacer();
        JobSeekerProfile.add(spacer2, new com.intellij.uiDesigner.core.GridConstraints(9, 1, 1, 4, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer3 = new com.intellij.uiDesigner.core.Spacer();
        JobSeekerProfile.add(spacer3, new com.intellij.uiDesigner.core.GridConstraints(1, 5, 8, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer4 = new com.intellij.uiDesigner.core.Spacer();
        JobSeekerProfile.add(spacer4, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 4, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        resumeFileAddress = new JLabel();
        resumeFileAddress.setText("No Resume Uploaded");
        JobSeekerProfile.add(resumeFileAddress, new com.intellij.uiDesigner.core.GridConstraints(7, 3, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        deleteButton1 = new JButton();
        deleteButton1.setText("Delete");
        JobSeekerProfile.add(deleteButton1, new com.intellij.uiDesigner.core.GridConstraints(8, 4, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        uploadResumeButton = new JButton();
        uploadResumeButton.setText("Upload/Edit Resume");
        JobSeekerProfile.add(uploadResumeButton, new com.intellij.uiDesigner.core.GridConstraints(8, 3, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        deleteButton = new JButton();
        deleteButton.setText("Delete");
        JobSeekerProfile.add(deleteButton, new com.intellij.uiDesigner.core.GridConstraints(4, 3, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer5 = new com.intellij.uiDesigner.core.Spacer();
        JobSeekerProfile.add(spacer5, new com.intellij.uiDesigner.core.GridConstraints(5, 3, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        viewButton = new JButton();
        viewButton.setText("View");
        JobSeekerProfile.add(viewButton, new com.intellij.uiDesigner.core.GridConstraints(7, 4, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return JobSeekerProfile;
    }


}
