import java.lang.Integer;
import java.lang.String;
import java.lang.System;

public class Goosky {
    public static void main(String[] args) {
        if(args[0] != null && ! args[0].isEmpty()) { //url
            Integer results = 10;
            if(args[1] != null && ! args[1].isEmpty()) { //count
                results = new Integer(args[1]);
            }
            if(args[2] != null && ! args[2].isEmpty()) {
                Crawler gooskybot = new Crawler(args[0], results, args[2]);
                gooskybot.crawl();
            } else {
                System.out.println("Please add a query");
                return;
            }
        } else {
            System.out.println("Please follow script call with either search or crawl as the argument");
            return;
        }
    }
}