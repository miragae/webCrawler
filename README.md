# Web Crawler
Simple command line application using jsoup to crawl through reachable pages from given url to given depth, printing results in JSON.

## Running the project

1. Download "build" and "lib" folders
2. Start command line in "build" and "lib" folders location
3. Start program using syntax
```
java -cp build;lib pl.webcrawler.MainClass <URL> <DEPTH>
```
e.g.
```
java -cp build;lib pl.webcrawler.MainClass http://meteo.pl 1
```
