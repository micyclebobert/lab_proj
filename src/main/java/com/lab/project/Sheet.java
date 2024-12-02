package com.lab.project;

import com.lab.project.TemplateComponent.Exit;

public class Sheet {
    String[][] data;

    public String getCell(int row, int col) {
        return data[row][col];
    }
    public String[] getRow(String[][] array, int startRow) {
        if (array == null || startRow < 0 || startRow >= array.length) {
            throw new IllegalArgumentException("Invalid row index or array is null.");
        }
        return array[startRow];
    }
    public String[] getSubRow(String[][] array, int row, int startCol) {
        if (array == null || row < 0 || row >= array.length || startCol < 0 || startCol >= array[row].length) {
            throw new IllegalArgumentException("Invalid row/column index or array is null.");
        }
    
        int length = array[row].length - startCol;
        String[] subRow = new String[length];
    
        for (int i = 0; i < length; i++) {
            subRow[i] = array[row][startCol + i];
        }
    
        return subRow;
    }
    public String[] getCol(String[][] array, int col) {
        if (array == null || col < 0 || col >= array[0].length) {
            throw new IllegalArgumentException("Invalid column index or array is null.");
        }
    
        String[] column = new String[array.length];
        for (int i = 0; i < array.length; i++) {
            column[i] = array[i][col];
        }
    
        return column;
    }
    
    public String[] getSubCol(String[][] array, int col, int startRow) {
        if (array == null || col < 0 || col >= array[0].length || startRow < 0 || startRow >= array.length) {
            throw new IllegalArgumentException("Invalid row/column index or array is null.");
        }
    
        int length = array.length - startRow;
        String[] subCol = new String[length];
    
        for (int i = 0; i < length; i++) {
            subCol[i] = array[startRow + i][col];
        }
    
        return subCol;
    }
        
    

    public String[][] getCells(int startRow, int startCol, int endRow, int endCol) {
        // Validation
        if (data == null || startRow < 0 || startCol < 0 || endRow >= data.length || endCol >= data[0].length
                || startRow > endRow || startCol > endCol) {
            Exit.errorMessage("Programmer has skill issue", "Invalid range or array is null.");
        }

        // Size of the subarray
        int rowsLen = endRow - startRow + 1;
        int colsLen = endCol - startCol + 1;

        String[][] subarray = new String[rowsLen][colsLen];
        for (int i = 0; i < rowsLen; i++) {
            for (int j = 0; j < colsLen; j++) {
                subarray[i][j] = data[startRow + i][startCol + j];
            }
        }

        return subarray;
    }



    public Sheet(String tsv) {
        String[] temp = tsv.split("\n");
        data = new String[temp.length][];
        for (int i = 0; i < temp.length; i++) {
            data[i] = temp[i].split("\t");
        }
    }

}
