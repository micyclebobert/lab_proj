package com.lab.project;

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

    public String[] getCol(int col, int startRow) {
        String[] out = new String[getRowCount() - startRow];
        for (int i = startRow; i < out.length; i++) {
            out[i] = data[i][col];
        }
        return out;
    }

    public String[] getRowWith(String searchValue) {
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                if (data[i][j].trim().equals(searchValue))
                    return getRow(i);
            }
        }
        return getRowWith(Custom.getIDFromUser(searchValue
                + " was not found. Please try again or if you believe it to be a mistake, contact Sir or Apu."));
    }

    public Sheet(String csv) {
        String[] temp = csv.split("\n");
        data = new String[temp.length][];
        for (int i = 0; i < temp.length; i++) {
            temp[i] = temp[i].substring(1, temp[i].length() - 1);
            data[i] = temp[i].split("\",\"", -1);
        }
    }
}
