package com.lab.project;

import java.awt.event.ItemEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.lab.project.TemplateComponent.DropDownMenu;
import com.lab.project.TemplateComponent.Exit;
import com.lab.project.TemplateComponent.TextDisplay;

public class Main {

    public final static String MAIN_SHEET_DOCS_KEY = "1YhTCkhKhV7oWxZz7ZgwaJI5fOU3md_m8PJKZCfey-r4";
    public final static int SECTION_NAME_COL = 2;
    public final static int FEILD_NAME_COL = 3;
    public final static int STUDENT_INFO_COL = 4;
    public final static int EXTRA_INFO_COL = 5;
    /*
     * Sequence:
     * choosing sht
     * textfield multiline output
     * Bunch of JTables
     * error log (w scroll wheel)
     */
    public static Sheet fields;
    public static Sheet studentData;
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
        String[] options= {"Theory", "Lab"};
        typeSelect = new DropDownMenu(options);
        sectionSelect = new DropDownMenu();
        mainData = new TextDisplay();
        sectionSelect.setVisible(false);
        mainData.setVisible(false);
        typeSelect.setLocation(10, 10);
        sectionSelect.setLocation(10, 70);
        typeSelect.addItemListener(e -> onTypeSelect(e));
        sectionSelect.addItemListener(e -> onSectionSelect(e));
        frame.add(typeSelect);
        frame.add(sectionSelect);
        frame.setVisible(true);
        // Exit.errorMessage("abc", "def");
    }

    public static void onTypeSelect(ItemEvent e) {
        if (e.getSource().equals(typeSelect)) {
            sectionSelect.setVisible(false);
            mainData.setVisible(false);
            String down = DownloadManager.downloadFromURL(MAIN_SHEET_DOCS_KEY, typeSelect.getSelected());
            System.out.println(down);
            mainSheet = new Sheet(down);

            mainSheet.print();
            System.out.println(mainSheet.getCell(1, 2));
            sectionSelect.removeAllItems();
            sectionSelect.addItems(mainSheet.getCol(SECTION_NAME_COL, 1));
            sectionSelect.setVisible(true);
        }
    }

    public static void onSectionSelect(ItemEvent e) {

        if (e.getSource().equals(sectionSelect)) {
            mainData.setVisible(false);
            // Selected.sectionChosen(sectionSelect.getSelectedIndex());
            System.out.println("here but not supposed to");
            int chosenCol = sectionSelect.getSelectedIndex() + 1;
            String down;
            String docsKey = DownloadManager.getDocsKey(mainSheet.getCell(0, 1));
            down = DownloadManager.downloadFromURL(docsKey, sectionSelect.getSelected(),
                    mainSheet.getCell(chosenCol, FEILD_NAME_COL));
            fields = new Sheet(down);
            down = DownloadManager.downloadFromURL(docsKey, sectionSelect.getSelected(),
                    mainSheet.getCell(chosenCol, STUDENT_INFO_COL));
            studentData = new Sheet(down);

            studentData.print();
            fields.print();
            String id = JOptionPane.showInputDialog("ID: ");
            String[] feildData = fields.getRow(0);
            String[] currentData = studentData.getRowWith(id);
            for (int i = 0; i < currentData.length; i++) {
                System.out.println(feildData[i] + ": " + currentData[i]);
            }
        }
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