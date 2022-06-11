package classes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SearchResultsPage {
    private final int NO_OF_RESULTS = 3;
    private JScrollBar scrollBar1;
    private JLabel job1Title;
    private JLabel job1Score;
    private JLabel job1Company;
    private JLabel job2Title;
    private JLabel job2Score;
    private JLabel job2Company;
    private JLabel job3Title;
    private JLabel job3Company;
    private JLabel job3Score;
    private JPanel searchResultsPanel;
    private JButton prevButton;
    private JButton nextButton;
    private JPanel job1Panel;
    private JPanel job2Panel;
    private JPanel job3Panel;
    private JButton backButton;
    private JButton searchButton;
    private JTextArea textArea1;
    private JButton loginButton;
    private int pageNumber = 0;
    ArrayList<ScoredJob> jobList;

    public SearchResultsPage(Search search) {
        jobList = search.getScoredJobs();
        createPage(pageNumber);
        addButtonListeners(nextButton, prevButton);
        addPanelListeners();
    }

    public void createPage(int pageNumber) {
        JLabel[] jobTitles = {job1Title, job2Title, job3Title};
        JLabel[] jobCompanies = {job1Company, job2Company, job3Company};
        JLabel[] jobScores = {job1Score, job2Score, job3Score};
        for (JLabel label : jobTitles) {
            label.getParent().setVisible(false);
        }
        if (jobList.isEmpty()) {
            jobTitles[0].setText("No jobs could be found!");
            jobTitles[0].getParent().setVisible(true);
        } else {
            for (int i = 0; i < NO_OF_RESULTS; i++) {
                if (pageNumber * NO_OF_RESULTS + i < jobList.size()) {
                    jobTitles[i].setText(jobList.get(pageNumber * NO_OF_RESULTS + i).getJob().getJobTitle());
                    jobCompanies[i].setText(jobList.get(pageNumber * NO_OF_RESULTS + i).getJob().getRecruiter().getOrg());
                    jobScores[i].setText(String.valueOf(jobList.get(pageNumber * NO_OF_RESULTS + i).getScore()));
                    jobScores[i].getParent().setVisible(true);
                }
            }
        }
    }

    public void addButtonListeners(JButton nextButton, JButton prevButton) {
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (pageNumber + 1 < Math.ceil(jobList.size() / (double) NO_OF_RESULTS)) {
                    pageNumber += 1;
                    createPage(pageNumber);
                }
            }
        });
        this.prevButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (pageNumber > 0) {
                    pageNumber -= 1;
                }
                createPage(pageNumber);
            }
        });
    }

    public void addPanelListeners() {
        if (jobList.isEmpty()) {
            return;
        }
        Map<JPanel, Integer> panels = Map.of(job1Panel, 0, job2Panel, 1, job3Panel, 2);

        for (JPanel panel : panels.keySet()) {
            panel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    if (Runtime.accountManager().getCurrentUser() == null) {
                        Runtime.showLoginPage(new JFrame(), "Search Results Page");
                    } else {
                        JPanel clickedPanel = (JPanel) e.getSource();
                        int indexOfClick = panels.get(clickedPanel);
                        JFrame desFrame = new JFrame("Description Page");
                        desFrame.setContentPane(new DescriptionPage(jobList, pageNumber, indexOfClick).getDescriptionPanel());
                        desFrame.pack();
                        desFrame.setVisible(true);
                    }
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    super.mouseEntered(e);
                    panel.setBackground(Color.CYAN);
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    super.mouseExited(e);
                    panel.setBackground(Color.lightGray);
                }
            });
        }
    }

    public JPanel getSearchResultsPanel() {
        return searchResultsPanel;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }
}
