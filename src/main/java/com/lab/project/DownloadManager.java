package com.lab.project;

import java.io.IOException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import javax.swing.JOptionPane;

import com.lab.project.TemplateComponent.Exit;

public class DownloadManager {

    public static String getFullURL(String docsKeys, String sheetName) {
        return "https://docs.google.com/spreadsheets/d/" + docsKeys
                + "/gviz/tq?tqx=out:csv&sheet=" + URLEncoder.encode(sheetName, StandardCharsets.UTF_8);
    }

    public static String getFullURL(String docsKeys, String sheetName, String range) {
        return "https://docs.google.com/spreadsheets/d/" + docsKeys
                + "/gviz/tq?tqx=out:csv&sheet=" + URLEncoder.encode(sheetName, StandardCharsets.UTF_8)
                + "&range=" + range;
    }

    public static String downloadFromURL(String requestURL) {
        try (Scanner scanner = new Scanner(new URL(requestURL).openStream(),
                StandardCharsets.UTF_8.toString())) {
            scanner.useDelimiter("\\A");
            return scanner.hasNext() ? scanner.next() : "";
        }
        catch(IOException e){
            Exit.errorMessage("Download Failure", "Could not download data");
            return null;
        }
    }

    public static String downloadFromURL(String docsKeys, String sheetName) {
        System.out.println(getFullURL(docsKeys, sheetName));
        return downloadFromURL(getFullURL(docsKeys, sheetName));
    }

    public static String downloadFromURL(String docsKeys, String sheetName, String range) {
        return downloadFromURL(getFullURL(docsKeys, sheetName, range));
    }
}
