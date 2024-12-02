package com.lab.project.TemplateComponent;

import javax.swing.JOptionPane;

public class Exit {
    public static void errorMessage(String title, String message) {
        JOptionPane.showConfirmDialog(null, message, title, JOptionPane.YES_OPTION);
        System.exit(0);
    }
}
