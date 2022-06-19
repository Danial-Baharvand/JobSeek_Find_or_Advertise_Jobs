package classes;

import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.*;
// TODO: don't allow special characters
public class ClearingTextArea extends JTextArea implements FocusListener {
    private boolean clicked = false;
    private String defaultText;

    public ClearingTextArea() {
        setForeground(Color.gray);
        addFocusListener(this);
    }
    public String forceGetText() throws Exception {
        if (!clicked || getText().equals("")){ // don't allow empty strings
            throw new Exception("Please fill in all fields correctly");
        }else {
            return getText();
        }
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
    public void setDefaultText(String defaultText, boolean clicked) {
        this.defaultText = defaultText;
        this.clicked = clicked;
    }
}