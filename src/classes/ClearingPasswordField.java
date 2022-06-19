package classes;

import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.*;

public class ClearingPasswordField extends JPasswordField implements FocusListener {
    private boolean clicked = false;
    private String defaultText;

    public ClearingPasswordField() {
        // set it to the right color
        setForeground(Color.gray);
        setEchoChar((char) 0); // show what is typed
        addFocusListener(this);
    }

    @Override
    public void focusGained(FocusEvent e) {
        if (!clicked){
            setForeground(Color.black);
            defaultText = String.valueOf(getPassword());
            setEchoChar('*'); //don't show what is typed
            setText("");
            clicked = true;
        }
    }

    @Override
    public void focusLost(FocusEvent e) {
        if (String.valueOf(getPassword()).equals("")){
            setForeground(Color.gray);
            setEchoChar((char) 0);
            setText(defaultText);
            clicked = false;
        }
    }
}