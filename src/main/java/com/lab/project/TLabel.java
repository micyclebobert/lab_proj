package com.lab.project;

import java.awt.Font;

import javax.swing.JLabel;

public class TLabel extends JLabel {
    public TLabel(String text) {
        super(text);
        this.setFont(new Font(Font.DIALOG, Font.BOLD, 15));
        this.setSize(this.getPreferredSize());
        this.setSize(this.getWidth() + 10, this.getHeight());// a lil bit more than required
        System.out.println(this.getFont());
    }
}
