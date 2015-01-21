import java.lang.Integer;
import java.lang.String;
import java.lang.System;

public class Goosky {
    public static void main(String[] args) {
        System.out.println(args[0]);
        if(args[0].equals("crawl")) {
            if(args[1] != null && ! args[1].isEmpty()) { //url
                String url = args[1];
                Integer depth = 100;
                if(args[2] != null && ! args[2].isEmpty()) { //count
                    depth = new Integer(args[2]);
                }
                Crawler gooskybot = new Crawler(url, depth);
                gooskybot.crawl();
                return;
            } else {
                System.out.println("Please follow crawl with a seed url to crawl");
                return;
            }
        } else if (args[0] == "search") {
            // Search Logic Goes Here
        } else {
            System.out.println("Please follow script call with either search or crawl as the argument");
            return;
        }
    }
}