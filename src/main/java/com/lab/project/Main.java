package com.lab.project;

import javax.swing.JFrame;

public class Main {
    public final static String MAIN_SHEET_DOCS_KEY = "1YhTCkhKhV7oWxZz7ZgwaJI5fOU3md_m8PJKZCfey-r4";
    public final static String[] TYPE_OPTIONS = { "Theory", "Lab" };
    public final static int WIDTH = 800;
    public final static int HEIGHT = 600;
    public final static int SECTION_NAME_COL = 2;
    public final static int FIELDS_RANGE_COL = 3;
    public final static int STUDENT_INFORMATION_RANGE_COL = 4;

    private static Sheet mainSheet;

    private static JFrame frame;
    private static TLabel typeLabel;
    private static TDropDownMenu typeSelect;
    private static TLabel sectionLabel;
    private static TDropDownMenu sectionSelect;
    private static TTextDisplay textDisplay;

    public static void startGUI() {

        frame = new JFrame("Apu Helper");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setSize(WIDTH, HEIGHT);
        typeLabel = new TLabel("Select type: ");
        typeSelect = new TDropDownMenu(TYPE_OPTIONS);
        sectionLabel = new TLabel("Select section: ");
        sectionSelect = new TDropDownMenu();
        textDisplay = new TTextDisplay();
        sectionLabel.setVisible(false);
        sectionSelect.setVisible(false);
        textDisplay.setVisible(false);
        typeLabel.setLocation(20, 10);
        typeSelect.setLocation(150, 10);
        sectionLabel.setLocation(20, 70);
        sectionSelect.setLocation(150, 70);
        textDisplay.setLocation(50, 140);
        typeSelect.addItemListener(e -> onTypeSelect());
        sectionSelect.addItemListener(e -> onSectionSelect());
        frame.add(typeLabel);
        frame.add(typeSelect);
        frame.add(sectionLabel);
        frame.add(sectionSelect);
        frame.add(textDisplay);
        frame.setVisible(true);
    }

    public static void onTypeSelect() {
        if (typeSelect.isVisible()) {
            sectionLabel.setVisible(false);
            sectionSelect.setVisible(false);
            textDisplay.setVisible(false);
            String mainDownload = DownloadManager.downloadFromURL(MAIN_SHEET_DOCS_KEY, typeSelect.getSelected());
            mainSheet = new Sheet(mainDownload);
            sectionSelect.removeAllItems();
            sectionSelect.addItems(mainSheet.getCol(SECTION_NAME_COL, 1));
            sectionSelect.setSelectedIndex(-1);
            sectionLabel.setVisible(true);
            sectionSelect.setVisible(true);
        }
    }

    public static void onSectionSelect() {
        if (sectionSelect.isVisible()) {
            textDisplay.setVisible(false);
            int chosenCol = sectionSelect.getSelectedIndex() + 1;
            String docsKey = DownloadManager.getDocsKey(mainSheet.getCell(0, 1));
            String fieldsDownload = DownloadManager.downloadFromURL(docsKey, sectionSelect.getSelected(),
                    mainSheet.getCell(chosenCol, FIELDS_RANGE_COL));
            String[] fieldNames = new Sheet(fieldsDownload).getRow(0);
            String studentDownload = DownloadManager.downloadFromURL(docsKey, sectionSelect.getSelected(),
                    mainSheet.getCell(chosenCol, STUDENT_INFORMATION_RANGE_COL));
            String id = Custom.getIDFromUser("Enter your ID");
            String[] currentStudentData = new Sheet(studentDownload).getRowWith(id);
            String out = "";
            for (int i = 0; i < fieldNames.length; i++) {
                out += fieldNames[i] + ": " + currentStudentData[i] + "\n";
            }
            showText(out);
        }
    }

    public static void showText(String text) {
        textDisplay.setText(text);
        textDisplay.setVisible(true);
        textDisplay.setSize(WIDTH, 200); // updates preferredSize
        textDisplay.setSize(textDisplay.getPreferredSize());
    }

    public static void main(String[] args) {
        startGUI();
    }

}