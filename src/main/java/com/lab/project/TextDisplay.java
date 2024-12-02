package com.lab.project;

import javax.swing.JTextArea;

public class TextDisplay extends JTextArea {
    /* Presetting JTextArea to display multiline text in a nice way */
    public TextDisplay() {
        this.setLineWrap(true);
        this.setWrapStyleWord(true);
        this.setOpaque(false);
        this.setEditable(false);
    }

    public TextDisplay(String textToDisplay) {
        this();
        this.setText(textToDisplay);
    }
}
