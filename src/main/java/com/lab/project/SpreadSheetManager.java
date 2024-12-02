package com.lab.project;

public class SpreadSheetManager {
    public final String type;
    public final static String SPREAD_SHEET_MANAGER_DOCS_KEY = "1YhTCkhKhV7oWxZz7ZgwaJI5fOU3md_m8PJKZCfey-r4";

    private String docsKey;

    public String getDocsKey() {
        return docsKey;
    }

    private Group[] group;

    public SpreadSheetManager(String type) throws DownloadException, ApuException {
        this.type = type;
        this.update();
    }

    public Group getGroup(int index) {
        return group[index];
    }

    public String[] getSections() {
        String[] out = new String[group.length];
        for (int i = 0; i < group.length; i++) {
            out[i] = group[i].getSection();
        }
        return out;
    }

    public void update() throws DownloadException, ApuException {
        String s = "";
        // try {
        // s = Commons.readStringFromURL(SPREAD_SHEET_MANAGER_DOCS_KEY, type);
        // } catch (IOException e) {
        // throw new DownloadException("Main Sheet download failed");
        // }
        s = "\"Link:\",\"https://docs.google.com/spreadsheets/d/139M28-ssoApBQxjlaATXXlTNZiB01m3Ct-9nv3yZYWQ/edit?gid=1767563786#gid=1767563786\",\"Sheet Name\",\"Fields Range\",\"Student Information Range\",\"Extra Information Range\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\"\r\n"
                + //
                "\"\",\"\",\"CSE 115 Sec 7\",\"A3:L3\",\"A4:L42\",\"A\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\"\r\n"
                + //
                "\"\",\"\",\"CSE 215 Sec 7\",\"A3:L3\",\"A4:L41\",\"A\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\"\r\n"
                + //
                "\"\",\"\",\"CSE 215 Sec 8\",\"A3:L3\",\"A4:L41\",\"A\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\"\r\n"
                + //
                "\"\",\"\",\"CSE 440 Sec 1\",\"A3:L3\",\"A4:L44\",\"A\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\"\r\n"
                + //
                "\"\",\"\",\"CSE 445 Sec 6\",\"A3:L3\",\"A4:L46\",\"A,B\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\"";
        System.out.println(s);
        String[][] downloaded = Commons.splitCells(s);
        group = new Group[downloaded.length - 1];
        docsKey = Commons.getDocsKey(downloaded[0][1]);
        for (int i = 0; i < group.length; i++) {
            group[i] = new Group(
                    downloaded[i + 1][2],
                    downloaded[i + 1][3],
                    downloaded[i + 1][4],
                    downloaded[i + 1][5]);
        }
    }

    public String toString() {
        return Commons.stringFromArray(group, "\n");
    }

    public void print() {
        System.out.println(this.toString());
    }
}
