package com.lab.project;

import java.util.Arrays;

public class Sheet {
    String[][] data;

    public int getRowCount() {
        return data.length;
    }

    public int getColCount() {
        return data[0].length;
    }

    public String getCell(int row, int col) {
        return data[row][col];
    }

    public String[] getRow(int row) {
        return data[row];
    }

    public String[] getRow(int row, int startCol) {
        return Arrays.copyOfRange(getRow(row), startCol, getColCount());
    }

    public String[] getRow(int row, int startCol, int endCol) {
        return Arrays.copyOfRange(getRow(row), startCol, endCol);
    }

    public String[] getCol(int col) {
        return getCol(col, 0);
    }

    public String[] getCol(int col, int startRow) {
        return getCol(col, startRow, getRowCount() - 1);
    }

    public String[] getCol(int col, int startRow, int endRow) {
        String[] out = new String[getRowCount()];
        for (int i = startRow; i <= endRow; i++) {
            out[i] = data[i][col];
        }
        return out;
    }

    public String[] getColWith(String searchValue) {
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                if (data[i][j].trim().equals(searchValue))
                    return getCol(j);
            }
        }
        Commons.exitWithError("Couldn't find ID", searchValue + " not found");
        return null;
    }

    public String[] getRowWith(String searchValue) {
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                if (data[i][j].trim().equals(searchValue))
                    return getRow(i);
            }
        }
        Commons.exitWithError("Couldn't find ID", searchValue + " not found");
        return null;
    }

    public Sheet(String csv) {
        String[] temp = csv.split("\n");
        data = new String[temp.length][];
        for (int i = 0; i < temp.length; i++) {
            temp[i] = temp[i].substring(1, temp[i].length() - 1);
            data[i] = temp[i].split("\",\"", -1);
        }
    }

    public String toString() {
        return Commons.stringFrom2DArray(data);
    }

    public void print() {
        System.out.println(this.toString());
    }

}
