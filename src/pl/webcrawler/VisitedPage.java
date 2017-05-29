package pl.webcrawler;

import java.util.List;

/**
 *
 * @author Michał Lal
 */
public class VisitedPage {
    private String pageUrl;
    private int contentSize;
    private String md5Hash;
    List<String> reachablePages;

    public VisitedPage(String pageUrl, int contentSize, String md5Hash, List<String> reachablePages) {
        this.pageUrl = pageUrl;
        this.contentSize = contentSize;
        this.md5Hash = md5Hash;
        this.reachablePages = reachablePages;
    }

    public String getPageUrl() {
        return pageUrl;
    }

    public void setPageUrl(String pageUrl) {
        this.pageUrl = pageUrl;
    }

    public int getContentSize() {
        return contentSize;
    }

    public void setContentSize(int contentSize) {
        this.contentSize = contentSize;
    }

    public String getMd5Hash() {
        return md5Hash;
    }

    public void setMd5Hash(String md5Hash) {
        this.md5Hash = md5Hash;
    }

    public List<String> getReachablePages() {
        return reachablePages;
    }

    public void setReachablePages(List<String> reachablePages) {
        this.reachablePages = reachablePages;
    }

    public String getJSON() {
        return "{" + 
                    "\n\t\"pageUrl\" : \"" + pageUrl + "\"," +
                    "\n\t\"contentSize\" : \"" + contentSize + "\"," +
                    "\n\t\"md5Hash\" : \"" + md5Hash + "\"," +
                    "\n\t\"reachablePages\" : [\n" + 
                        reachablePages.stream().map(page -> "\t\t\""+page+"\",\n").reduce("", String::concat) + 
                    "\t]\n" +
                "}";
    }
    
}
