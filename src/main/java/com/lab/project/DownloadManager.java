package com.lab.project;

import java.io.IOException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class DownloadManager {

    public static String getFullURL(String docsKeys, String sheetName) {
        return "https://docs.google.com/spreadsheets/d/" + docsKeys
                + "/gviz/tq?tqx=out:csv&sheet=" + URLEncoder.encode(sheetName, StandardCharsets.UTF_8);
    }

    public static String getFullURL(String docsKeys, String sheetName, String range) {
        return getFullURL(docsKeys, sheetName) + "&range=" + range;
    }

    public static String downloadFromURL(String requestURL) {
        System.out.println(requestURL);
        try {
            Scanner scanner = new Scanner(new URL(requestURL).openStream(), StandardCharsets.UTF_8.toString());
            scanner.useDelimiter("\\A");
            String out = "";
            if (scanner.hasNext())
                out = scanner.next();
            else
                Commons.exitWithError("Download Failure (1)", "Data is empty");
            scanner.close();
            return out;
        } catch (IOException e) {
            e.printStackTrace();
            Commons.exitWithError("Download Failure (2)", "Could not download data");
            return null;
        }
    }

    public static String downloadFromURL(String docsKeys, String sheetName) {
        return downloadFromURL(getFullURL(docsKeys, sheetName));
    }

    public static String downloadFromURL(String docsKeys, String sheetName, String range) {
        return downloadFromURL(getFullURL(docsKeys, sheetName, range));
    }

    public static String getDocsKey(String fullLink) {
        try {
            return fullLink.split("spreadsheets/d/")[1].split("/")[0];
        } catch (Exception e) {
            e.printStackTrace();
            Commons.exitWithError("Couldn't split", "fullLink");
            return null;
        }
    }
}
