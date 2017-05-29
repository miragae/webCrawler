package pl.webcrawler;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

/**
 *
 * @author Michał Lal
 */
public class WebCrawler {
    
    private final String rootUrl;
    private final int initialDepth;
    private final Set<String> visitedUrls;

    public WebCrawler(String rootUrl, int maxDepth) {
        this.rootUrl = rootUrl;
        this.initialDepth = maxDepth;
        this.visitedUrls = new HashSet<>();
    }
    
    /**
     * Crawl starting from set url to set depth and print informations about visited pages in JSON
     */
    public void analyze(){
        crawl(rootUrl, initialDepth);
    }
    
    /**
     * Recursively crawl through reachable pages from given url to given depth printing JSON with informations about each visited page
     * @param url start url
     * @param depth start depth
     */
    private void crawl(String url, int depth){
        if(visitedUrls.contains(url)){
            return;
        }
        
        try {
            Connection connection = Jsoup.connect(url);
            Document htmlDocument = connection.get();
            Elements linksOnPage = htmlDocument.select("a[href]");
            
            int contentSize = htmlDocument.toString().length();
            String md5Hash = getMD5Hash(htmlDocument.toString());
            List<String> reachablePages = linksOnPage
                    .stream()
                    .map(element -> element.absUrl("href"))
                    .filter(urlString -> urlString.startsWith("http"))
                    .collect(Collectors.toList());
            
            VisitedPage page = new VisitedPage(url, contentSize, md5Hash, reachablePages);
            visitedUrls.add(url);
            System.out.println(page.getJSON());
            
            if(depth > 0){
                reachablePages
                        .stream()
                        .forEach(reachableUrl -> crawl(reachableUrl, depth-1));
            }
            
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }
    
    /**
     * Generate MD5 Hash for text
     * @param text text to generate hash from
     * @return hashed text
     */
    private String getMD5Hash(String text){
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(StandardCharsets.UTF_8.encode(text));
            return String.format("%032x", new BigInteger(1, md.digest()));
        } catch (NoSuchAlgorithmException ex){
        }
        return null;
    }
    
}
