package apcsfinal;

import deps.jsoup.*;

import java.io.FileWriter;
import java.io.IOException;
import java.lang.String;
import java.lang.System;
import java.util.ArrayList;


public class Crawler {
    private ArrayList<String> links;

    public void crawl(String url) {

        Document doc = Jsoup.connect(url).get();
        String title = doc.title();

        try {
            FileWriter fw = new FileWriter("database.csv",true);
            fw.write("\""+title+"\", "+url+"\n");
            fw.close();
        }
        catch(IOException ioe) {
            System.err.println("IOException: " + ioe.getMessage());
        }


        Elements anchors = doc.select("a[href]");
        for (anchor:anchors) {
            links.add(anchor.absUrl("href"))
        }
    }

    Crawler (String seed) {

    }



    public static void main(String[] args) {

    }
}