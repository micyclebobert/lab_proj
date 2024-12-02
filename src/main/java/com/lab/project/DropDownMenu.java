package com.lab.project;

import java.awt.Dimension;
import javax.swing.JComboBox;

public class DropDownMenu extends JComboBox<String> {
    public DropDownMenu(String... args) {
        super(args);
        this.setSize(new Dimension(100, 20));
        this.setSelectedIndex(-1);
    }

    public void addItems(String[] args) {
        for (String string : args) {
            this.addItem(string);
        }
    }
}
