package classes;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GuiHelper {
    public static ArrayList<String> getSelectedOptions(JPanel optionsPanel) {
        ArrayList<String> selectedOptions = new ArrayList<>();
        Component[] allOptions = optionsPanel.getComponents();
        for (Component option : allOptions) {
            if (((JCheckBox) option).isSelected()) {
                selectedOptions.add(((JCheckBox) option).getText().toLowerCase());
            }
        }
        return selectedOptions;
    }

    public static void createOptionBox(JPanel optionsPanel, List<String> options) {
        optionsPanel.setLayout(new BoxLayout(optionsPanel, BoxLayout.Y_AXIS));
        optionsPanel.removeAll();
        for (String option : options) {
            option = option.substring(0,1).toUpperCase() + option.substring(1).toLowerCase();
            optionsPanel.add(new JCheckBox(option));
        }
    }
}
