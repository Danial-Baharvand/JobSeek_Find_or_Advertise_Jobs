package classes;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import javax.swing.text.StyleContext;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.Collection;
import java.util.Locale;

public class AdminHomePage implements Page {
    private JPanel panel1;
    private JPanel categoriesList;
    private JPanel activeJobSeekerList;
    private JPanel activeRecruiterList;
    private JButton jobSeekerDeactivateButton;
    private JButton jobSeekerReActivateButton;
    private JButton catRemoveButton;
    private JButton recruiterDeactivateButton;
    private JButton recruiterReActivateButton;
    private JLabel adminLabel;
    private JPanel inactiveJobSeekersList;
    private JPanel inactiveRecruitersList;
    private JButton catEditButton;
    private JButton catAddButton;
    private ClearingTextField catNameTB;
    private JButton totalInterviewRequestsButton;
    private JButton totalApplicationsButton;
    private JButton totalRecruitersButton;
    private JButton totalJobsButton;
    private JButton totalJobsSeekersButton;
    private JLabel userList;
    IO io = new IO();
    Set<String> inactive;

    public AdminHomePage() {
        populateCatsPanel();
        //get list of users
        adminLabel.setText(Runtime.accountManager().getCurrentUser().getEmail());
        Collection<JobSeeker> jobSeekers = Runtime.accountManager().getJobSeekers().values();
        Collection<Recruiter> recruiters = Runtime.accountManager().getRecruiters().values();

        inactive = io.readInactiveUsers();

        ArrayList<String> activeJobSeekerUsers = new ArrayList<String>();
        for (JobSeeker jobSeeker : jobSeekers) {
            if (!inactive.contains(jobSeeker.getEmail()))
                activeJobSeekerUsers.add(jobSeeker.getEmail());
        }

        ArrayList<String> inactiveJobSeekersUsers = new ArrayList<String>();
        for (JobSeeker jobSeeker : jobSeekers) {
            if (inactive.contains(jobSeeker.getEmail())) {
                inactiveJobSeekersUsers.add(jobSeeker.getEmail());
            }
        }

        ArrayList<String> activeRecruiterUsers = new ArrayList<String>();
        for (Recruiter recruiter : recruiters) {
            if (!inactive.contains(recruiter.getEmail()))
                activeRecruiterUsers.add(recruiter.getEmail());
        }


        ArrayList<String> inactiveRecruiterUsers = new ArrayList<String>();
        for (Recruiter recruiter : recruiters) {
            if (inactive.contains(recruiter.getEmail())) {
                inactiveRecruiterUsers.add(recruiter.getEmail());
            }
        }

        GuiHelper.createOptionBox(activeJobSeekerList, activeJobSeekerUsers);
        GuiHelper.createOptionBox(activeRecruiterList, activeRecruiterUsers);
        GuiHelper.createOptionBox(inactiveJobSeekersList, inactiveJobSeekersUsers);
        GuiHelper.createOptionBox(inactiveRecruitersList, inactiveRecruiterUsers);


        jobSeekerDeactivateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (String s : GuiHelper.getSelectedOptions(activeJobSeekerList)) {
                    inactive.add(Runtime.accountManager().getJobSeekers().get(s).getEmail());
                    activeJobSeekerUsers.remove(s);
                    inactiveJobSeekersUsers.add(s);
                    io.newLine(Config.DT_INACTIVE_USERS, s);
                    GuiHelper.createOptionBox(activeJobSeekerList, activeJobSeekerUsers);
                    GuiHelper.createOptionBox(inactiveJobSeekersList, inactiveJobSeekersUsers);
                }
                activeJobSeekerList.updateUI();
                inactiveJobSeekersList.updateUI();
            }
        });

        jobSeekerReActivateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (String s : GuiHelper.getSelectedOptions(inactiveJobSeekersList)) {
                    inactive.remove(Runtime.accountManager().getJobSeekers().get(s).getEmail());
                    inactiveJobSeekersUsers.remove(s);
                    activeJobSeekerUsers.add(s);
                    Runtime.accountManager().getInactiveUsers().remove(s);
                    io.clearFileContent(Config.DT_INACTIVE_USERS);
                    io.writeMapEntry(Runtime.accountManager().getJobSeekers().get(s).getEmail(), Runtime.accountManager().getJobSeekers().get(s).getEmail(), Config.DT_INACTIVE_USERS);
                    GuiHelper.createOptionBox(inactiveJobSeekersList, inactiveJobSeekersUsers);
                    GuiHelper.createOptionBox(activeJobSeekerList, activeJobSeekerUsers);

                }
                activeJobSeekerList.updateUI();
                inactiveJobSeekersList.updateUI();
            }
        });

        recruiterDeactivateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (String s : GuiHelper.getSelectedOptions(activeRecruiterList)) {
                    inactive.add(Runtime.accountManager().getRecruiters().get(s).getEmail());
                    activeRecruiterUsers.remove(s);
                    inactiveRecruiterUsers.add(s);
                    io.newLine(Config.DT_INACTIVE_USERS, s);
                    GuiHelper.createOptionBox(activeRecruiterList, activeRecruiterUsers);
                    GuiHelper.createOptionBox(inactiveRecruitersList, inactiveRecruiterUsers);
                }
                activeJobSeekerList.updateUI();
                inactiveJobSeekersList.updateUI();
            }
        });

        recruiterReActivateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (String s : GuiHelper.getSelectedOptions(inactiveRecruitersList)) {
                    inactive.remove(Runtime.accountManager().getRecruiters().get(s).getEmail());
                    inactiveRecruiterUsers.remove(s);
                    activeRecruiterUsers.add(s);
                    Runtime.accountManager().getInactiveUsers().remove(s);
                    io.clearFileContent(Config.DT_INACTIVE_USERS);
                    io.writeMapEntry(Runtime.accountManager().getRecruiters().get(s).getEmail(), Runtime.accountManager().getRecruiters().get(s).getEmail(), Config.DT_INACTIVE_USERS);
                    GuiHelper.createOptionBox(inactiveRecruitersList, inactiveRecruiterUsers);
                    GuiHelper.createOptionBox(activeRecruiterList, activeRecruiterUsers);
                    Runtime.accountManager().getInactiveUsers().remove(s);
                }
                activeJobSeekerList.updateUI();
                inactiveJobSeekersList.updateUI();
            }
        });

        catAddButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String newCat = catNameTB.forceGetText();
                    Runtime.accountManager().getCategories().put(newCat, newCat);
                    Runtime.accountManager().getCategories().writeToFile(Config.DT_CATEGORIES);
                    populateCatsPanel();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Please enter a category name!");
                }
            }
        });

        catRemoveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String deleteStr = GuiHelper.getSelectedRadio(categoriesList);
                    Runtime.accountManager().getCategories().removeAllValues(deleteStr);
                    Runtime.accountManager().getCategories().writeToFile(Config.DT_CATEGORIES);
                    populateCatsPanel();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Please select a category!");
                }
            }
        });
        catEditButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Runtime.showEditCategoryPage(GuiHelper.getSelectedRadio(categoriesList));
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Please select a category!");
                }
            }
        });


        totalJobsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Total Jobs Listed: " + Runtime.accountManager().getJobs().size());
            }
        });
        totalJobsSeekersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Total Job Seekers: " + Runtime.accountManager().getJobSeekers().size());
            }
        });
        totalRecruitersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Total Recruiters: " + Runtime.accountManager().getRecruiters().size());
            }
        });
        totalApplicationsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Total Job Applications: " + (Runtime.accountManager().getJobApplications().map.size()));
            }
        });

        totalInterviewRequestsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Total Interview Requests: " + (Runtime.accountManager().getJobInvitations().map.size()));
            }
        });
    }


    private void populateCatsPanel() {
        GuiHelper.createRadioBox(categoriesList, Runtime.accountManager().getCategories().keySet());
    }

    public JPanel getAdminHomePage() {
        return panel1;
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
        panel1 = new JPanel();
        panel1.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), -1, -1));
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(19, 10, new Insets(20, 20, 20, 20), -1, -1));
        panel2.setBackground(new Color(-13224648));
        panel1.add(panel2, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label1 = new JLabel();
        label1.setBackground(new Color(-592138));
        Font label1Font = this.$$$getFont$$$("Calibri Light", -1, 16, label1.getFont());
        if (label1Font != null) label1.setFont(label1Font);
        label1.setForeground(new Color(-592138));
        label1.setText("Welcome Home: ");
        panel2.add(label1, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 10, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        adminLabel = new JLabel();
        adminLabel.setBackground(new Color(-592138));
        Font adminLabelFont = this.$$$getFont$$$("Calibri Light", -1, 22, adminLabel.getFont());
        if (adminLabelFont != null) adminLabel.setFont(adminLabelFont);
        adminLabel.setForeground(new Color(-592138));
        adminLabel.setText("AdminLabel");
        panel2.add(adminLabel, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 10, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label2 = new JLabel();
        Font label2Font = this.$$$getFont$$$("Calibri Light", Font.BOLD, 16, label2.getFont());
        if (label2Font != null) label2.setFont(label2Font);
        label2.setForeground(new Color(-592138));
        label2.setText("Job Seekers (Active)");
        panel2.add(label2, new com.intellij.uiDesigner.core.GridConstraints(2, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label3 = new JLabel();
        Font label3Font = this.$$$getFont$$$("Calibri Light", Font.BOLD, 16, label3.getFont());
        if (label3Font != null) label3.setFont(label3Font);
        label3.setForeground(new Color(-592138));
        label3.setText("Categories");
        panel2.add(label3, new com.intellij.uiDesigner.core.GridConstraints(2, 8, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, 1, new Dimension(300, -1), null, null, 0, false));
        final JScrollPane scrollPane1 = new JScrollPane();
        scrollPane1.setBackground(new Color(-1973791));
        panel2.add(scrollPane1, new com.intellij.uiDesigner.core.GridConstraints(4, 8, 15, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(24, 654), null, 0, false));
        categoriesList = new JPanel();
        categoriesList.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        scrollPane1.setViewportView(categoriesList);
        final JScrollPane scrollPane2 = new JScrollPane();
        scrollPane2.setBackground(new Color(-1973791));
        panel2.add(scrollPane2, new com.intellij.uiDesigner.core.GridConstraints(3, 0, 7, 3, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        activeJobSeekerList = new JPanel();
        activeJobSeekerList.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        scrollPane2.setViewportView(activeJobSeekerList);
        final JScrollPane scrollPane3 = new JScrollPane();
        panel2.add(scrollPane3, new com.intellij.uiDesigner.core.GridConstraints(12, 0, 7, 3, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(-1, 500), null, 0, false));
        activeRecruiterList = new JPanel();
        activeRecruiterList.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        scrollPane3.setViewportView(activeRecruiterList);
        final JScrollPane scrollPane4 = new JScrollPane();
        panel2.add(scrollPane4, new com.intellij.uiDesigner.core.GridConstraints(3, 4, 7, 3, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(-1, 400), null, 0, false));
        inactiveJobSeekersList = new JPanel();
        inactiveJobSeekersList.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        scrollPane4.setViewportView(inactiveJobSeekersList);
        final JLabel label4 = new JLabel();
        Font label4Font = this.$$$getFont$$$("Calibri Light", Font.BOLD, 16, label4.getFont());
        if (label4Font != null) label4.setFont(label4Font);
        label4.setForeground(new Color(-592138));
        label4.setText("Job Seekers (Inactive)");
        panel2.add(label4, new com.intellij.uiDesigner.core.GridConstraints(2, 4, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JScrollPane scrollPane5 = new JScrollPane();
        panel2.add(scrollPane5, new com.intellij.uiDesigner.core.GridConstraints(12, 4, 7, 3, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(-1, 400), null, 0, false));
        inactiveRecruitersList = new JPanel();
        inactiveRecruitersList.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        scrollPane5.setViewportView(inactiveRecruitersList);
        jobSeekerDeactivateButton = new JButton();
        jobSeekerDeactivateButton.setText("Deactivate");
        panel2.add(jobSeekerDeactivateButton, new com.intellij.uiDesigner.core.GridConstraints(2, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, new Dimension(150, -1), 0, false));
        jobSeekerReActivateButton = new JButton();
        jobSeekerReActivateButton.setText("Re-Activate");
        panel2.add(jobSeekerReActivateButton, new com.intellij.uiDesigner.core.GridConstraints(2, 6, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, new Dimension(150, -1), 0, false));
        catNameTB = new ClearingTextField();
        catNameTB.setForeground(new Color(-13224648));
        catNameTB.setText("New Category Name");
        panel2.add(catNameTB, new com.intellij.uiDesigner.core.GridConstraints(3, 8, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, new Dimension(150, -1), 0, false));
        catAddButton = new JButton();
        catAddButton.setText("Add");
        panel2.add(catAddButton, new com.intellij.uiDesigner.core.GridConstraints(3, 9, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, new Dimension(150, -1), 0, false));
        catRemoveButton = new JButton();
        catRemoveButton.setText("Remove");
        panel2.add(catRemoveButton, new com.intellij.uiDesigner.core.GridConstraints(4, 9, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, new Dimension(150, -1), 0, false));
        catEditButton = new JButton();
        catEditButton.setText("Edit");
        panel2.add(catEditButton, new com.intellij.uiDesigner.core.GridConstraints(5, 9, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, new Dimension(150, -1), 0, false));
        totalInterviewRequestsButton = new JButton();
        totalInterviewRequestsButton.setText("Total Interview Requests");
        panel2.add(totalInterviewRequestsButton, new com.intellij.uiDesigner.core.GridConstraints(18, 9, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        totalApplicationsButton = new JButton();
        totalApplicationsButton.setText("Total Applications");
        panel2.add(totalApplicationsButton, new com.intellij.uiDesigner.core.GridConstraints(17, 9, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        totalRecruitersButton = new JButton();
        totalRecruitersButton.setText("Total Recruiters");
        panel2.add(totalRecruitersButton, new com.intellij.uiDesigner.core.GridConstraints(16, 9, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        totalJobsButton = new JButton();
        totalJobsButton.setText("Total Jobs");
        panel2.add(totalJobsButton, new com.intellij.uiDesigner.core.GridConstraints(14, 9, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        totalJobsSeekersButton = new JButton();
        totalJobsSeekersButton.setText("Total Job Seekers");
        panel2.add(totalJobsSeekersButton, new com.intellij.uiDesigner.core.GridConstraints(15, 9, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label5 = new JLabel();
        label5.setForeground(new Color(-592138));
        label5.setText("Generate Reports");
        panel2.add(label5, new com.intellij.uiDesigner.core.GridConstraints(13, 9, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label6 = new JLabel();
        Font label6Font = this.$$$getFont$$$("Calibri Light", Font.BOLD, 16, label6.getFont());
        if (label6Font != null) label6.setFont(label6Font);
        label6.setForeground(new Color(-592138));
        label6.setText("Recruiters (Inactive)");
        panel2.add(label6, new com.intellij.uiDesigner.core.GridConstraints(11, 4, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        recruiterReActivateButton = new JButton();
        recruiterReActivateButton.setText("Re-Activate");
        panel2.add(recruiterReActivateButton, new com.intellij.uiDesigner.core.GridConstraints(11, 6, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, new Dimension(150, -1), 0, false));
        final JLabel label7 = new JLabel();
        Font label7Font = this.$$$getFont$$$("Calibri Light", Font.BOLD, 16, label7.getFont());
        if (label7Font != null) label7.setFont(label7Font);
        label7.setForeground(new Color(-592138));
        label7.setText("Recruiters (Active)");
        panel2.add(label7, new com.intellij.uiDesigner.core.GridConstraints(11, 0, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        recruiterDeactivateButton = new JButton();
        recruiterDeactivateButton.setText("Deactivate");
        panel2.add(recruiterDeactivateButton, new com.intellij.uiDesigner.core.GridConstraints(11, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, new Dimension(150, -1), 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer1 = new com.intellij.uiDesigner.core.Spacer();
        panel2.add(spacer1, new com.intellij.uiDesigner.core.GridConstraints(6, 3, 4, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, new Dimension(-1, 100), new Dimension(-1, 200), null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer2 = new com.intellij.uiDesigner.core.Spacer();
        panel2.add(spacer2, new com.intellij.uiDesigner.core.GridConstraints(12, 3, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, new Dimension(-1, 50), new Dimension(-1, 200), null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer3 = new com.intellij.uiDesigner.core.Spacer();
        panel2.add(spacer3, new com.intellij.uiDesigner.core.GridConstraints(10, 3, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, new Dimension(-1, 20), null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer4 = new com.intellij.uiDesigner.core.Spacer();
        panel2.add(spacer4, new com.intellij.uiDesigner.core.GridConstraints(5, 7, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, 1, null, new Dimension(15, -1), null, 0, false));
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
        return panel1;
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }

    @Override
    public void update() {
        // Do nothing
    }

    @Override
    public JPanel getPanel() {
        return panel1;
    }

    @Override
    public String pageName() {
        return "Admin Home";
    }
}



