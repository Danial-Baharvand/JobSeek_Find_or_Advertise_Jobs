package classes;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import javax.swing.text.StyleContext;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;

public class EditCategoryPage {
    private JPanel catPagePanel;
    private JButton addBtn;
    private JButton removeBtn;
    private ClearingTextField catTB;
    private JScrollPane catsScroller;
    private JPanel catsPanel;
    private JTextArea textArea1;

    public EditCategoryPage(JFrame frame, CreateJobPage jobPage) {
        Runtime.categories.readFromFile(Config.DT_CATEGORIES);
        GuiHelper.createOptionBox(catsPanel, Runtime.categories.getUserCategories(Runtime.accountManager().getCurrentUser()));
        addBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Runtime.categories.add(catTB.getText(), Runtime.accountManager().getCurrentUser().getEmail());
                Runtime.categories.writeToFile(Config.DT_CATEGORIES);
                GuiHelper.createOptionBox(catsPanel,
                        Runtime.categories.getUserCategories(Runtime.accountManager().getCurrentUser()));
                catTB.setText("");
                frame.setVisible(true);
                frame.repaint();
                jobPage.updateCategories();

            }
        });
        removeBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (String catName : GuiHelper.getSelectedOptions(catsPanel)) {
                    Runtime.categories.remove(catName, Runtime.accountManager().getCurrentUser().getEmail());
                }
                Runtime.categories.writeToFile(Config.DT_CATEGORIES);
                GuiHelper.createOptionBox(catsPanel,
                        Runtime.categories.getUserCategories(Runtime.accountManager().getCurrentUser()));
                jobPage.updateCategories();
                frame.setVisible(true);
                frame.repaint();

            }
        });
    }


    public JPanel getCatPagePanel() {
        return catPagePanel;
    }
}
