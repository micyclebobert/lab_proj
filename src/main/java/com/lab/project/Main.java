package com.lab.project;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.lab.project.TemplateComponent.DropDownMenu;
import com.lab.project.TemplateComponent.TextDisplay;

public class Main {
    
    public final static String SPREAD_SHEET_MANAGER_DOCS_KEY = "1YhTCkhKhV7oWxZz7ZgwaJI5fOU3md_m8PJKZCfey-r4";
    public final static int SECTION_NAME_COL = 2;
    /*
     * Sequence:
     * choosing sht
     * textfield multiline output
     * Bunch of JTables
     * error log (w scroll wheel)
     */

    private static String errorMessage = "";

    private static Sheet mainSheet;

    public static String getErrorMessage() {
        return errorMessage;
    }

    public static void setErrorMessage(String errorMessage) {
        System.out.println(errorMessage);
        Main.errorMessage = errorMessage;
    }

    private static JFrame frame;
    private static DropDownMenu typeSelect;
    private static DropDownMenu sectionSelect;
    private static TextDisplay mainData;

    public static void _GUI() {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setSize(800, 600);
        typeSelect = new DropDownMenu("Theory", "Lab");
        sectionSelect = new DropDownMenu();
        mainData = new TextDisplay();
        sectionSelect.setVisible(false);
        mainData.setVisible(false);
        typeSelect.setLocation(10, 10);
        sectionSelect.setLocation(10, 70);
        typeSelect.addItemListener(e -> onTypeSelect());
        sectionSelect.addItemListener(e -> onSectionSelect());
        frame.add(typeSelect);
        frame.add(sectionSelect);
        frame.setVisible(true);
    }

    public static void onTypeSelect() {
        sectionSelect.setVisible(false);
        mainData.setVisible(false);
        mainSheet = new Sheet(DownloadManager.downloadFromURL(SPREAD_SHEET_MANAGER_DOCS_KEY,typeSelect.getSelected()));
        sectionSelect.removeAllItems();
        // sectionSelect.addItems(Selected.getSections());
        sectionSelect.setVisible(true);
    }

    public static void onSectionSelect() {
        mainData.setVisible(false);
        // Selected.sectionChosen(sectionSelect.getSelectedIndex());
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
        String s1 = DownloadManager.downloadFromURL("139M28-ssoApBQxjlaATXXlTNZiB01m3Ct-9nv3yZYWQ", "CSE 445 Sec 6",
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