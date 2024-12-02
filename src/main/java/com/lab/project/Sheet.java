package com.lab.project;

import java.util.Arrays;

import com.lab.project.TemplateComponent.Exit;

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
        String[] out = new String[getRowCount()];
        for (int i = 0; i < out.length; i++) {
            out[i] = data[i][col];
        }
        return out;
    }

    public String[] getCol(int col, int startRow) {
        return Arrays.copyOfRange(getCol(col), startRow, getRowCount());
    }

    public String[] getCol(int col, int startRow, int endRow) {
        return Arrays.copyOfRange(getCol(col), startRow, endRow);
    }

    public String[] getColWith(String searchValue) {
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                if (searchValue.equals(data[i][j]))
                    return getCol(j);
            }
        }
        Exit.errorMessage("Couldn't find ID", searchValue + " not found");
        return null;
    }

    public String[] getRowWith(String searchValue) {
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                if (data[i][j].trim().equals(searchValue))
                    return getRow(i);
            }
        }
        Exit.errorMessage("Couldn't find ID", searchValue + " not found");
        return null;
    }

    public String[][] getCells(int startRow, int startCol, int endRow, int endCol) {
        int numberOfRows = endRow - startRow + 1;
        int numberOfCols = endCol - startCol + 1;

        String[][] subarray = new String[numberOfRows][numberOfCols];
        for (int i = startRow; i <= endRow; i++) {
            for (int j = startCol; j <= endCol; j++) {
                subarray[i][j] = data[i][j];
            }
        }

        return subarray;
    }

    public Sheet(String csv) {
        String[] temp = csv.split("\n");
        data = new String[temp.length][];
        for (int i = 0; i < temp.length; i++) {
            temp[i] = temp[i].substring(1, temp[i].length() - 1);
            data[i] = temp[i].split("\",\"");
        }
    }

    public String toString() {
        return Commons.stringFrom2DArray(data);
    }

    public void print() {
        System.out.println(this.toString());
    }

}
