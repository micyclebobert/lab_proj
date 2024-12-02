package com.lab.project;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Main {
    /*
     * Sequence:
     * choosing sht
     * textfield multiline output
     * Bunch of JTables
     * error log (w scroll wheel)
     */

    private static String errorMessage = "";

    public static String getErrorMessage() {
        return errorMessage;
    }

    public static void setErrorMessage(String errorMessage) {
        Main.errorMessage = errorMessage;
        System.out.println(errorMessage);
    }

    private static JFrame frame;
    private static DropDownMenu typeSelect;
    private static DropDownMenu sectionSelect;
    private static JTextField _IDInput;
    private static JPanel data;

    public static void _GUI() {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setSize(800, 600);
        typeSelect = new DropDownMenu("Theory", "Lab");
        sectionSelect = new DropDownMenu();
        _IDInput = new JTextField();
        data = new JPanel();
        sectionSelect.setVisible(false);
        _IDInput.setVisible(false);
        data.setVisible(false);
        typeSelect.setLocation(10, 10);
        sectionSelect.setLocation(10, 70);
        typeSelect.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                sectionSelect.setVisible(false);
                _IDInput.setVisible(false);
                data.setVisible(false);
                Selected.typeChosen((String) typeSelect.getSelectedItem());
                sectionSelect.removeAllItems();
                sectionSelect.addItems(Selected.getSections());
                sectionSelect.setVisible(true);
            }
        });
        sectionSelect.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                _IDInput.setVisible(false);
                data.setVisible(false);
                Selected.sectionChosen(sectionSelect.getSelectedIndex());
            }
        });
        frame.add(typeSelect);
        frame.add(sectionSelect);
        frame.setVisible(true);
    }

    public static void check(String s) {
        System.out.println(s + Commons.isValidCellRange(s));
    }

    public static void main(String[] args) {
        // ("139M28-ssoApBQxjlaATXXlTNZiB01m3Ct-9nv3yZYWQ", "CSE 445 Sec 6", "B4:I6")
        // f3();
        _GUI();
    }

    static void f1() {
        String[] e = "".split(",");
        System.out.println(e.length);
        for (int i = 0; i < e.length; i++) {
            System.out.println(e[i]);
        }

        System.out.println(e);
    }

    static void f3() {
        SpreadSheetManager sm = null;
        try {
            sm = new SpreadSheetManager("Theory");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("abc");
        }
        sm.print();
    }

    static void f2() throws Exception {
        String s1 = Commons.readStringFromURL("139M28-ssoApBQxjlaATXXlTNZiB01m3Ct-9nv3yZYWQ", "CSE 445 Sec 6",
                "B4:I6");
        String[][] s2 = Commons.splitCells(s1);
        for (int i = 0; i < s2.length; i++) {
            for (int j = 0; j < s2[i].length; j++) {
                System.out.print(s2[i][j] + " ");
            }
            System.out.println();
        }
    }

}