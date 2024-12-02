package com.lab.project;

import javax.swing.JOptionPane;

public class Custom {
    public static void exitWithError(String title, String message) {
        JOptionPane.showConfirmDialog(null, message, title, JOptionPane.YES_OPTION);
        System.exit(0);
    }

    public static String getIDFromUser(String initialMessage) {
        String id = JOptionPane.showInputDialog(initialMessage).trim();
        while (!isValidNSUID(id)) {
            id = JOptionPane.showInputDialog("Not a valid NSU ID. Please try again.").trim();
        }
        return id;
    }

    public static boolean isValidNSUID(String id) {
        for (int i = 0; i < id.length(); i++) {
            if (!Character.isDigit(id.charAt(i))) {
                return false;
            }
        }
        return id.length() == 10;
    }
}
