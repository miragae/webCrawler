package pl.webcrawler;

/**
 *
 * @author Michał Lal
 */
public class MainClass {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Wrong number of arguments. Enter 2 arguments separated with spaces: \n"
                    + "1. HTTP URL\n"
                    + "2. depth (>=0)");
            return;
        }
        
        String url = args[0];
        
        if(!url.startsWith("http://")){
            System.out.println("Invalid URL. Please specify http:// url");
            return;
        }
        
        Integer depth;
        
        try {
            depth = Integer.parseInt(args[1]);
        } catch (NumberFormatException ex){
            System.out.println("Invalid depth. Please specify a number.");
            return;
        }
        
        if(depth < 0){
            System.out.println("Invalid depth. Depth must be non-negative.");
            return;
        }
        
        WebCrawler webCrawler = new WebCrawler(url, depth);
        webCrawler.analyze();
    }
    
}
