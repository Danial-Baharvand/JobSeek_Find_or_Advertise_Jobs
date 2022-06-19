package classes;

import java.awt.*;
import javax.swing.*;
import javax.swing.plaf.basic.*;

/**
 * Extended because we wanted to show the prompt on the drop down menu before it's selected
 */
class PromptComboBoxRenderer extends BasicComboBoxRenderer
{
    private final String prompt;

    /*
     *  Set the text to display when no item has been selected.
     */
    public PromptComboBoxRenderer(String prompt)
    {
        this.prompt = prompt;
    }

    /*
     *  Custom rendering to display the prompt text when no item is selected
     */
    public Component getListCellRendererComponent(
            JList list, Object value, int index, boolean isSelected, boolean cellHasFocus)
    {
        super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

        if (value == null)
            setText( prompt );
        return this;
    }
}