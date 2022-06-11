package classes;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import javax.swing.text.StyleContext;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Locale;

public class SearchPage {
    private final boolean searchClicked = false;
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

    public boolean isSearchClicked() {
        return searchClicked;
    }
}
