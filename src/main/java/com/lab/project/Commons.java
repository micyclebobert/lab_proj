package com.lab.project;

import java.io.IOException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import com.lab.project.Exception.ApuException;

public class Commons {

    public static boolean isValidCellRange(String range) {
        String[] s = range.split(":");
        if (s.length != 2 || s[0].equals(s[1]) || !isValidCellNumber(s[0]) || !isValidCellNumber(s[1]))
            return false;
        return true;
    }

    public static boolean isValidCellNumber(String cellNumber) {
        return cellNumber.matches("^([A-Z]{1,2}|[A-W][A-Z]{2}|X[A-E][A-Z]|XF[A-D])([1-9]\\d{0,6})$");
    }

    public static String getDocsKey(String fullLink) throws ApuException {
        try {
            return fullLink.split("spreadsheets/d/")[1].split("/")[0];
        } catch (Exception e) {
            throw new ApuException("link error");
        }
    }

    public static String[] getExtraInfoArray(String extraInfoString) {
        String[] extraInfoArray = extraInfoString.split(",");
        for (int i = 0; i < extraInfoArray.length; i++) {
            extraInfoArray[i] = extraInfoArray[i].trim();
        }
        return extraInfoArray;
    }

    public static String[] splitCommas(String data) {
        return removeEmpty(trimAll(data.split(",")));
    }

    public static String[] trimAll(String[] array) {
        for (int i = 0; i < array.length; i++) {
            array[i] = array[i].trim();
        }
        return array;
    }

    public static String[] removeEmpty(String[] array) {
        int j = 0;
        for (int i = 0; i < array.length; i++) {
            array[j] = array[i];
            if (hasContent(array[i]))
                j++;

        }
        String[] out = new String[j];
        for (int i = 0; i < out.length; i++) {
            out[i] = array[i];
        }
        return out;
    }

    public static boolean hasContent(String s) {
        return s != null && s.trim().length() != 0;
    }

    public static String[] splitLines(String data) {
        return data.split("\n");
    }

    public static String[][] splitCells(String data) {
        String[] lineArray = splitLines(data);
        String[][] studentData = new String[lineArray.length][];
        for (int i = 0; i < studentData.length; i++) {
            studentData[i] = lineArray[i].split("\",\"");
            studentData[i][0] = studentData[i][0].substring(1);
            int last = studentData[i].length - 1;
            studentData[i][last] = studentData[i][last].substring(0, studentData[i][last].length() - 1);
        }
        return studentData;
    }


    public static <T> String stringFromArray(T[] array) {
        return stringFromArray(array, ", ");
    }

    public static <T> String stringFromArray(T[] array, String joiner) {
        String s = "";
        for (int i = 0; i < array.length; i++) {
            s += array[i].toString();
            if (i != array.length - 1)
                s += joiner;
        }
        return s;
    }

    public static <T> String stringFrom2DArray(T[][] array) {
        return stringFrom2DArray(array, ", ", "\n");
    }

    public static <T> String stringFrom2DArray(T[][] array, String joiner, String joiner2D) {
        String s = "";
        for (int i = 0; i < array.length; i++) {
            s = stringFromArray(array[i], joiner);
            if (i != array.length - 1)
                s += joiner2D;
        }
        return s;
    }
}
