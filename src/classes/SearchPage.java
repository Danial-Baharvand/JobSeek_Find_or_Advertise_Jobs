package classes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class SearchPage {
    private boolean searchClicked = false;
    private ClearingTextField searchTBox;
    private JButton searchButton;
    private JSlider salarySlider;
    private JPanel searchPanel;
    private JLabel StateLabel;
    private JLabel jobTypeLabel;
    private JLabel lowSalLabel;
    private JLabel midSalLabel;
    private JLabel highSalLabel;
    private JTextArea textArea1;
    private JPanel statePanel;
    private JScrollPane stateScroller;
    private JLabel catLabel;
    private JScrollPane catScroller;
    private JPanel catPanel;
    private JScrollPane jobTypeScroller;
    private JPanel jobTypePanel;

    public JPanel getSearchPanel() {
        return searchPanel;
    }

    public SearchPage(HashSet<Job> jobs) {
        /**test code to check categories' functionality
         should be removed when adding categories by recruiters is implemented
         */
        Categories categories = Tests.createExampleCats();
        GuiHelper.createOptionBox(statePanel, Config.STATES);
        GuiHelper.createOptionBox(catPanel, new ArrayList<>(categories.getMap().keySet()));
        GuiHelper.createOptionBox(jobTypePanel, Config.JOB_TYPES);
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // Creates a new search object and adds the user's selected criteria to it
                Search search = new Search(jobs);
                search.setSearchText(searchTBox.getText());
                search.setStates(GuiHelper.getSelectedOptions(statePanel));
                search.setCats(GuiHelper.getSelectedOptions(catPanel));
                search.setJobTypes(GuiHelper.getSelectedOptions(jobTypePanel));
                search.setSalary(salarySlider.getValue() * 3000);
                search.setScores();
                Runtime.showSearchResultsPage(Runtime.frame, search);
            }
        });
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
        searchPanel = new JPanel();
        searchPanel.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(17, 7, new Insets(20, 20, 20, 20), -1, -1));
        searchPanel.setBackground(new Color(-13224648));
        searchPanel.setForeground(new Color(-1973791));
        searchTBox = new ClearingTextField();
        searchTBox.setEditable(true);
        searchTBox.setEnabled(true);
        searchTBox.setText("I'm looking for...");
        searchPanel.add(searchTBox, new com.intellij.uiDesigner.core.GridConstraints(2, 0, 1, 6, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer1 = new com.intellij.uiDesigner.core.Spacer();
        searchPanel.add(spacer1, new com.intellij.uiDesigner.core.GridConstraints(3, 5, 14, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        searchButton = new JButton();
        searchButton.setText("Search");
        searchPanel.add(searchButton, new com.intellij.uiDesigner.core.GridConstraints(2, 6, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, new Dimension(200, -1), 0, false));
        textArea1 = new JTextArea();
        textArea1.setBackground(new Color(-11348236));
        searchPanel.add(textArea1, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 7, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, 1, 1, null, new Dimension(150, 15), new Dimension(-1, 1), 0, false));
        StateLabel = new JLabel();
        StateLabel.setForeground(new Color(-592138));
        StateLabel.setText("State");
        searchPanel.add(StateLabel, new com.intellij.uiDesigner.core.GridConstraints(3, 6, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        catLabel = new JLabel();
        catLabel.setForeground(new Color(-592138));
        catLabel.setText("Categories");
        searchPanel.add(catLabel, new com.intellij.uiDesigner.core.GridConstraints(3, 0, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        stateScroller = new JScrollPane();
        searchPanel.add(stateScroller, new com.intellij.uiDesigner.core.GridConstraints(5, 6, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, new Dimension(100, 100), new Dimension(150, 100), new Dimension(150, 200), 0, false));
        statePanel = new JPanel();
        statePanel.setLayout(new GridBagLayout());
        stateScroller.setViewportView(statePanel);
        catScroller = new JScrollPane();
        searchPanel.add(catScroller, new com.intellij.uiDesigner.core.GridConstraints(5, 0, 1, 5, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        catPanel = new JPanel();
        catPanel.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        catScroller.setViewportView(catPanel);
        jobTypeScroller = new JScrollPane();
        searchPanel.add(jobTypeScroller, new com.intellij.uiDesigner.core.GridConstraints(12, 6, 4, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, new Dimension(100, 100), new Dimension(150, 100), new Dimension(150, 200), 0, false));
        jobTypePanel = new JPanel();
        jobTypePanel.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1, true, false));
        jobTypeScroller.setViewportView(jobTypePanel);
        jobTypeLabel = new JLabel();
        jobTypeLabel.setForeground(new Color(-592138));
        jobTypeLabel.setText("Job Type");
        searchPanel.add(jobTypeLabel, new com.intellij.uiDesigner.core.GridConstraints(11, 6, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        salarySlider = new JSlider();
        salarySlider.setBackground(new Color(-13224648));
        salarySlider.setForeground(new Color(-1973791));
        salarySlider.setValue(10);
        searchPanel.add(salarySlider, new com.intellij.uiDesigner.core.GridConstraints(15, 0, 1, 5, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label1 = new JLabel();
        label1.setForeground(new Color(-592138));
        label1.setText("Minimum Salary");
        searchPanel.add(label1, new com.intellij.uiDesigner.core.GridConstraints(12, 0, 1, 5, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        highSalLabel = new JLabel();
        highSalLabel.setForeground(new Color(-592138));
        highSalLabel.setText("$270K");
        searchPanel.add(highSalLabel, new com.intellij.uiDesigner.core.GridConstraints(14, 3, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        midSalLabel = new JLabel();
        midSalLabel.setForeground(new Color(-592138));
        midSalLabel.setText("$150K");
        searchPanel.add(midSalLabel, new com.intellij.uiDesigner.core.GridConstraints(14, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        lowSalLabel = new JLabel();
        lowSalLabel.setForeground(new Color(-592138));
        lowSalLabel.setText("$30K");
        searchPanel.add(lowSalLabel, new com.intellij.uiDesigner.core.GridConstraints(14, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        searchPanel.add(panel1, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 7, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return searchPanel;
    }

    public boolean isSearchClicked() {
        return searchClicked;
    }
}
