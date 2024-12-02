package com.lab.project;

import com.lab.project.Exception.ApuException;
import com.lab.project.Exception.DownloadException;

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
        try {
            s = DownloadManager.downloadFromURL(SPREAD_SHEET_MANAGER_DOCS_KEY, type);
        } catch (Exception e) {
            throw new DownloadException("Main Sheet download failed");
        }
        String[][] downloaded = Commons.splitCells(s);
        System.out.println(2);
        group = new Group[downloaded.length - 1];
        System.out.println(3);
        docsKey = Commons.getDocsKey(downloaded[0][1]);
        System.out.println(4);
        for (int i = 0; i < group.length; i++) {
            System.out.println("i: "+i);
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
