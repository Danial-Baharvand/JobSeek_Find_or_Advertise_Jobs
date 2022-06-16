package classes;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class JobSeekerResultsPage implements Page {
    private JPanel jobSeekerResultsPanel;
    private JLabel noJobSeekersLabel;
    private JScrollPane jobSeekerScroller;
    private JPanel jobSeekerPanel;

    JobSeekerResultsPage(HashMap<JobSeeker, Integer> jobSeekerScores, Job job) {
        if (!GuiHelper.createJobSeekerList(jobSeekerScores, job, jobSeekerPanel)) {
            jobSeekerScroller.setVisible(false);
            noJobSeekersLabel.setVisible(true);
        }
    }

    @Override
    public void update() {

    }

    @Override
    public JPanel getPanel() {
        return jobSeekerResultsPanel;
    }

    @Override
    public String pageName() {
        return "JobSeekers";
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
        jobSeekerResultsPanel = new JPanel();
        jobSeekerResultsPanel.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(2, 1, new Insets(20, 20, 20, 20), -1, -1));
        jobSeekerResultsPanel.setBackground(new Color(-13224648));
        jobSeekerScroller = new JScrollPane();
        jobSeekerResultsPanel.add(jobSeekerScroller, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        jobSeekerPanel = new JPanel();
        jobSeekerPanel.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        jobSeekerPanel.setForeground(new Color(-12828863));
        jobSeekerScroller.setViewportView(jobSeekerPanel);
        noJobSeekersLabel = new JLabel();
        noJobSeekersLabel.setText("Label");
        noJobSeekersLabel.setVisible(false);
        jobSeekerResultsPanel.add(noJobSeekersLabel, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return jobSeekerResultsPanel;
    }

}
