import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.jsoup.Jsoup;

import java.io.FileWriter;
import java.io.IOException;
import java.lang.Integer;
import java.lang.String;
import java.lang.System;
import java.util.ArrayList;


public class Crawler {
    private ArrayList<String> links;
    private Integer indexed;
    private Integer depth;

    public void crawl() {
            if(indexed < depth) {
                String link = links.get(indexed);
                try {

                    System.out.println("Crawling: "+ link);
                    System.out.println("-----");
                    Document doc = Jsoup.connect(link).get();
                    String title = doc.title();
                    System.out.println("Index#: "+ (indexed+1));
                    System.out.println("Title: "+ title);

                    try {
                        FileWriter fw = new FileWriter("database.csv", true);
                        fw.write("\"" + title + "\", " + link + "\n");
                        fw.close();
                        indexed+=1;
                    } catch (IOException ioe) {
                        System.err.println("IOException: " + ioe.getMessage());
                    }
                    System.out.println("-----------------");

                    Elements anchors = doc.select("a[href]");
                    for (Element anchor : anchors) {
                        this.links.add(anchor.absUrl("href"));
                    }
                    crawl();
                } catch (IOException ex) {
                    System.out.println(ex.toString());
                }
            }
    }

    Crawler (String seed, Integer depth) {
        this.links = new ArrayList<String>();
        this.indexed = 0;

        this.links.add(seed);
        if (depth > 0) {
            this.depth = depth;
        } else {
            this.depth = 100;
        }
    }
}