package com.lab.project;

public class Selected {
    private static SpreadSheetManager manager;
    private static Group group;
    private static SheetData data;

    public static Group getGroup() {
        return group;
    }

    public static void sectionChosen(int index) {
        group = manager.getGroup(index);
        try {
            data = new SheetData(manager.getDocsKey(), group);
        } catch (DownloadException e) {
            Main.setErrorMessage("Download Error: " + e.getMessage());
        } catch (ApuException e) {
            Main.setErrorMessage("Apu Error: " + e.getMessage());
        }
    }

    // public static String[] getMatch(String ID){}

    public static String[] getSections() {
        return manager.getSections();
    }

    public static void typeChosen(String type) {
        try {
            manager = new SpreadSheetManager(type);
        } catch (DownloadException e) {
            Main.setErrorMessage("Download Error: " + e.getMessage());
        } catch (ApuException e) {
            Main.setErrorMessage("Apu Error: " + e.getMessage());
        }
    }

}
