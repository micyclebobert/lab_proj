package com.lab.project;

import java.io.IOException;

import com.lab.project.Exception.ApuException;
import com.lab.project.Exception.DownloadException;
import com.lab.project.Exception.NoSuchStudentException;

public class SheetData {
    Group selectedGroup;
    private String docsKey;
    private String[] fieldName;
    private String[][] studentData;
    private String[][][] extraInfos;

    public String[] getFieldName() {
        return fieldName;
    }

    public String[] getStudentData(String ID) throws NoSuchStudentException {
        for (int i = 0; i < studentData.length; i++) {
            for (int j = 0; j < studentData[i].length; j++) {
                if (studentData[i][j].equals(ID))
                    return studentData[i];
            }
        }
        throw new NoSuchStudentException("No one with the ID " + ID + " found");
    }

    public String[][][] getExtraInfos() {
        return extraInfos;
    }

    public SheetData(String docsKey, Group selectedGroup) throws DownloadException, ApuException {
        this.docsKey = docsKey;
        this.selectedGroup = selectedGroup;
        getData(selectedGroup.getFieldNameRange());
        System.out.println("Sub-sheet downloaded");
    }

    public String getData(String range) throws DownloadException {
        try {
            return DownloadManager.downloadFromURL(docsKey, selectedGroup.getSection(), range);
        } catch (Exception e) {
            throw new DownloadException("Sub-sheet download failed");
        }
    }

}
