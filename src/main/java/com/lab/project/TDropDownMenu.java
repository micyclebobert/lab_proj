package com.lab.project;

import javax.swing.JComboBox;

public class TDropDownMenu extends JComboBox<String> {
    public TDropDownMenu() {
        this.setSize(200, 20);
        this.setSelectedIndex(-1);
    }

    public TDropDownMenu(String[] args) {
        super(args);
        this.setSize(200, 20);
        this.setSelectedIndex(-1);
    }

    public void addItems(String[] args) {
        for (int i = 0; i < args.length; i++) {
            this.addItem(args[i]);
        }
    }

    public String getSelected() {
        return (String) this.getSelectedItem();
    }
}
