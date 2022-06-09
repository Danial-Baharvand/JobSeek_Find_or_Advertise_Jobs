package classes;

import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.*;

public class ClearingTextArea extends JTextArea implements FocusListener {
    private boolean clicked = false;
    private String defaultText;

    public ClearingTextArea() {
        setForeground(Color.gray);
        addFocusListener(this);
    }

    @Override
    public void focusGained(FocusEvent e) {
        if (!clicked){
            setForeground(Color.black);
            defaultText = getText();
            setText("");
            clicked = true;
        }
    }

    @Override
    public void focusLost(FocusEvent e) {
        if (getText().equals("")){
            setForeground(Color.gray);
            setText(defaultText);
            clicked = false;
        }
    }
}