package classes;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import javax.swing.text.StyleContext;
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
    private JTextArea textArea1;


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


}
