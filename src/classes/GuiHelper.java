package classes;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GuiHelper {
    public static ArrayList<String> getSelectedOptions(JPanel optionsPanel) {
        ArrayList<String> selectedOptions = new ArrayList<>();
        Component[] allOptions = optionsPanel.getComponents();
        for (Component option : allOptions) {
            if (((JCheckBox) option).isSelected()) {
                selectedOptions.add(((JCheckBox) option).getText());
            }
        }
        return selectedOptions;
    }

    public static void createOptionBox(JPanel optionsPanel, ArrayList<String> options) {
        optionsPanel.setLayout(new BoxLayout(optionsPanel, BoxLayout.Y_AXIS));
        for (String option : options) {
            optionsPanel.add(new JCheckBox(option));
        }
    }
}
