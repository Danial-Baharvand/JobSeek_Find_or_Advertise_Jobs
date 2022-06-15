package classes;

import javax.swing.*;

public interface Page {
    void update();
    JPanel getPanel();
    String pageName();
}
