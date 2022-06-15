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
    private JButton recruiterDeactiveButton;
    private JButton recruiterReActivateButton1;
    private JLabel adminLabel;
    private JPanel inactiveJobSeekersList;
    private JPanel inactiveRecruitersList;
    private JButton catEditButton;
    private JButton catAddButton;
    private JTextArea textArea1;
    private ClearingTextField catNameTB;
    private JLabel userList;

    public AdminHomePage() {
        populateCatsPanel();
        //get list of users
        adminLabel.setText(Runtime.accountManager().getCurrentUser().getEmail());
        Collection<JobSeeker> jobSeekers = Runtime.accountManager().getJobSeekers().values();
        Collection<Recruiter> recruiters = Runtime.accountManager().getRecruiters().values();


        ArrayList<String> activeJobSeekerUsers = new ArrayList<String>();
        for (JobSeeker jobSeeker : jobSeekers) {
            if (jobSeeker.isActive()) {
                activeJobSeekerUsers.add(jobSeeker.getEmail());
            }
        }
        ArrayList<String> inactiveJobSeekersUsers = new ArrayList<String>();
        for (JobSeeker jobSeeker : jobSeekers) {
            if (!jobSeeker.isActive()) {
                inactiveJobSeekersUsers.add(jobSeeker.getEmail());
            }
        }

        ArrayList<String> activeRecruiterUsers = new ArrayList<String>();
        for (Recruiter recruiter : recruiters) {
            if (recruiter.isActive()) {
                activeRecruiterUsers.add(recruiter.getEmail());
            }
        }

        ArrayList<String> inactiveRecruiterUsers = new ArrayList<String>();
        for (Recruiter recruiter : recruiters) {
            if (!recruiter.isActive()) {
                inactiveRecruiterUsers.add(recruiter.getEmail());
            }
        }

        GuiHelper.createOptionBox(activeJobSeekerList, activeJobSeekerUsers);
        GuiHelper.createOptionBox(activeRecruiterList, activeRecruiterUsers);
        GuiHelper.createOptionBox(inactiveJobSeekersList, inactiveJobSeekersUsers);
        GuiHelper.createOptionBox(inactiveRecruitersList, inactiveRecruiterUsers);



        /*jobSeekerDeactivateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                        Set<JobSeeker> js = currentUser.getSkills();
                        for (String s : GuiHelper.getSelectedOptions(skillsList)) {
                            strings.remove(s);
                            Runtime.accountManager().getSkills().remove(currentUser.getEmail(), s);
                        }
                        GuiHelper.createOptionBox(skillsList, new ArrayList<>(currentUser.getSkills()));
                        Runtime.accountManager().getSkills().writeToFile(Config.DT_SKILLS);

                    }
                });
            }
        });*/
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
                    Runtime.accountManager().getCategories().removeAll(deleteStr);
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
        panel2.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(10, 8, new Insets(20, 20, 20, 20), -1, -1));
        panel2.setBackground(new Color(-13224648));
        panel1.add(panel2, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label1 = new JLabel();
        label1.setBackground(new Color(-592138));
        Font label1Font = this.$$$getFont$$$("Calibri Light", -1, 16, label1.getFont());
        if (label1Font != null) label1.setFont(label1Font);
        label1.setForeground(new Color(-592138));
        label1.setText("Welcome Home: ");
        panel2.add(label1, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 8, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        adminLabel = new JLabel();
        adminLabel.setBackground(new Color(-592138));
        Font adminLabelFont = this.$$$getFont$$$("Calibri Light", -1, 22, adminLabel.getFont());
        if (adminLabelFont != null) adminLabel.setFont(adminLabelFont);
        adminLabel.setForeground(new Color(-592138));
        adminLabel.setText("AdminLabel");
        panel2.add(adminLabel, new com.intellij.uiDesigner.core.GridConstraints(2, 0, 1, 7, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label2 = new JLabel();
        Font label2Font = this.$$$getFont$$$("Calibri Light", Font.BOLD, 16, label2.getFont());
        if (label2Font != null) label2.setFont(label2Font);
        label2.setForeground(new Color(-592138));
        label2.setText("Job Seekers (Active)");
        panel2.add(label2, new com.intellij.uiDesigner.core.GridConstraints(3, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label3 = new JLabel();
        Font label3Font = this.$$$getFont$$$("Calibri Light", Font.BOLD, 16, label3.getFont());
        if (label3Font != null) label3.setFont(label3Font);
        label3.setForeground(new Color(-592138));
        label3.setText("Categories");
        panel2.add(label3, new com.intellij.uiDesigner.core.GridConstraints(3, 6, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JScrollPane scrollPane1 = new JScrollPane();
        scrollPane1.setBackground(new Color(-1973791));
        panel2.add(scrollPane1, new com.intellij.uiDesigner.core.GridConstraints(5, 6, 5, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(24, 654), null, 0, false));
        categoriesList = new JPanel();
        categoriesList.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        scrollPane1.setViewportView(categoriesList);
        final JScrollPane scrollPane2 = new JScrollPane();
        scrollPane2.setBackground(new Color(-1973791));
        panel2.add(scrollPane2, new com.intellij.uiDesigner.core.GridConstraints(4, 0, 4, 3, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(24, 654), null, 0, false));
        activeJobSeekerList = new JPanel();
        activeJobSeekerList.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        scrollPane2.setViewportView(activeJobSeekerList);
        final JScrollPane scrollPane3 = new JScrollPane();
        panel2.add(scrollPane3, new com.intellij.uiDesigner.core.GridConstraints(9, 0, 1, 3, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        activeRecruiterList = new JPanel();
        activeRecruiterList.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        scrollPane3.setViewportView(activeRecruiterList);
        final JScrollPane scrollPane4 = new JScrollPane();
        panel2.add(scrollPane4, new com.intellij.uiDesigner.core.GridConstraints(4, 3, 4, 3, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        inactiveJobSeekersList = new JPanel();
        inactiveJobSeekersList.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        scrollPane4.setViewportView(inactiveJobSeekersList);
        final JLabel label4 = new JLabel();
        Font label4Font = this.$$$getFont$$$("Calibri Light", Font.BOLD, 16, label4.getFont());
        if (label4Font != null) label4.setFont(label4Font);
        label4.setForeground(new Color(-592138));
        label4.setText("Job Seekers (Inactive)");
        panel2.add(label4, new com.intellij.uiDesigner.core.GridConstraints(3, 3, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JScrollPane scrollPane5 = new JScrollPane();
        panel2.add(scrollPane5, new com.intellij.uiDesigner.core.GridConstraints(9, 3, 1, 3, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        inactiveRecruitersList = new JPanel();
        inactiveRecruitersList.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        scrollPane5.setViewportView(inactiveRecruitersList);
        jobSeekerDeactivateButton = new JButton();
        jobSeekerDeactivateButton.setText("Deactivate");
        panel2.add(jobSeekerDeactivateButton, new com.intellij.uiDesigner.core.GridConstraints(3, 1, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        jobSeekerReActivateButton = new JButton();
        jobSeekerReActivateButton.setText("Re-Activate");
        panel2.add(jobSeekerReActivateButton, new com.intellij.uiDesigner.core.GridConstraints(3, 4, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        recruiterDeactiveButton = new JButton();
        recruiterDeactiveButton.setText("Deactivate");
        panel2.add(recruiterDeactiveButton, new com.intellij.uiDesigner.core.GridConstraints(8, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label5 = new JLabel();
        Font label5Font = this.$$$getFont$$$("Calibri Light", Font.BOLD, 16, label5.getFont());
        if (label5Font != null) label5.setFont(label5Font);
        label5.setForeground(new Color(-592138));
        label5.setText("Recruiters (Active)");
        panel2.add(label5, new com.intellij.uiDesigner.core.GridConstraints(8, 0, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        recruiterReActivateButton1 = new JButton();
        recruiterReActivateButton1.setText("Re-Activate");
        panel2.add(recruiterReActivateButton1, new com.intellij.uiDesigner.core.GridConstraints(8, 5, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label6 = new JLabel();
        Font label6Font = this.$$$getFont$$$("Calibri Light", Font.BOLD, 16, label6.getFont());
        if (label6Font != null) label6.setFont(label6Font);
        label6.setForeground(new Color(-592138));
        label6.setText("Recruiters (Inactive)");
        panel2.add(label6, new com.intellij.uiDesigner.core.GridConstraints(8, 3, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer1 = new com.intellij.uiDesigner.core.Spacer();
        panel2.add(spacer1, new com.intellij.uiDesigner.core.GridConstraints(7, 7, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        textArea1 = new JTextArea();
        textArea1.setBackground(new Color(-11348236));
        panel2.add(textArea1, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 8, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_NORTH, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, new Dimension(150, 1), new Dimension(-1, 1), 0, false));
        catNameTB = new ClearingTextField();
        catNameTB.setText("New Category Name");
        panel2.add(catNameTB, new com.intellij.uiDesigner.core.GridConstraints(4, 6, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        catAddButton = new JButton();
        catAddButton.setText("Add");
        panel2.add(catAddButton, new com.intellij.uiDesigner.core.GridConstraints(4, 7, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        catRemoveButton = new JButton();
        catRemoveButton.setText("Remove");
        panel2.add(catRemoveButton, new com.intellij.uiDesigner.core.GridConstraints(5, 7, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        catEditButton = new JButton();
        catEditButton.setText("Edit");
        panel2.add(catEditButton, new com.intellij.uiDesigner.core.GridConstraints(6, 7, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
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



