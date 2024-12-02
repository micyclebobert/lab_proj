package com.lab.project;

import java.awt.Font;

import javax.swing.JTextArea;

public class TTextDisplay extends JTextArea {
    /* Presetting JTextArea to display multiline text in a nice way */
    public TTextDisplay() {
        this.setLineWrap(true);
        this.setWrapStyleWord(true);
        this.setOpaque(false);
        this.setEditable(false);
        this.setFont(new Font(Font.SERIF,Font.BOLD,14));
    }

    public TTextDisplay(String textToDisplay) {
        this();
        this.setText(textToDisplay);
    }
}
