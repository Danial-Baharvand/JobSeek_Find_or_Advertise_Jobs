package classes;

import javax.swing.*;

/**
 * Used for all GUI classes
 */
public interface Page {
    void update();
    JPanel getPanel();
    String pageName();
}
