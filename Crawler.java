import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.jsoup.Jsoup;

import java.io.FileWriter;
import java.io.IOException;
import java.lang.Boolean;
import java.lang.Integer;
import java.lang.String;
import java.lang.System;
import java.util.ArrayList;
import java.util.List;

public class Crawler {
    private ArrayList<String> queue;
    private Integer current;
    private Integer results;
    private Integer matched;
    private String query;

    public void crawl() {
        if (matched < results) {
            String link = queue.get(current);
            try {
                System.out.println("Crawling: " + link);

                Document doc = Jsoup.connect(link).get();

                String title = doc.title();
                Element description = doc.select("meta[name=description]").first();
                Element keywords = doc.select("meta[name=keywords]").first();
                System.out.println(title);

                if (title == null && description == null && keywords == null) {
                    current += 1;
                    crawl();
                } else {
                    String site = buildSite(link, title, description, keywords);
                    if (title != null && ! title.isEmpty() && title.toLowerCase().indexOf(query) > -1) {
                        store(site);
                        matched+=1;
                    } else if (keywords != null && keywords.attr("content") != null && ! keywords.attr("content").isEmpty() && keywords.attr("content").toLowerCase().indexOf(query) > -1 ) {
                        store(site);
                        matched+=1;
                    } else if (description != null && description.attr("content") != null && ! description.attr("content").isEmpty() && description.attr("content").toLowerCase().indexOf(query) > -1 ) {
                        store(site);
                        matched+=1;
                    }
                    current+=1;

                    for (Element anchor : doc.select("a[href]")) {
                        if(! this.queue.contains(anchor.absUrl("href"))) {
                            this.queue.add(anchor.absUrl("href"));
                        }
                    }

                    crawl();
                }

            } catch (IOException ex) {
                System.out.println(ex.toString());
            }
        }
    }

    public void store (String site) {
        try {
            FileWriter fw = new FileWriter("results.txt", true);

            fw.write(site);

            fw.close();

        } catch (IOException ioe) {
            System.err.println("IOException: " + ioe.getMessage());
        }
    }

    public String buildSite(String link, String title, Element description, Element keywords) {
        String site = title + "\n" + "-----\n" + "url: " + link + "\n";

        if (description != null && description.attr("content") != null && ! description.attr("content").isEmpty()) {
            site+="description: " + description.attr("content") + "\n";
        }

        if (keywords != null && keywords.attr("content") != null && ! keywords.attr("content").isEmpty()) {
            site += "keywords: " + keywords.attr("content") + "\n";
        }

        site+="\n";

        return site;
    }

    Crawler (String seed, Integer results, String query) {
        this.queue = new ArrayList<String>();
        this.current = 0;
        this.matched = 0;
        this.queue.add(seed);
        this.query = query;
        if (results > 0) {
            this.results = results;
        } else {
            this.results = 10;
        }

    }
}