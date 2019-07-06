/*
Write a piece of code that will query a URL that returns JSON and can parse the JSON string to pull out pieces of information. The information that should be parsed and returned is the pageid and the list of “See Also” links. Those links should be formatted to be actual links that can be used by a person to find the appropriate article. 
Use the Wikipedia API for the query.
*/
package com.melanta;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Iterator;

class ParseWeb {

    static void GetAllInformationFromWebsiteAndConvertToLinkAndWriteToFile(String urlString) throws Exception {
        URL WikiLink = new URL(urlString);
        BufferedReader InStream = new BufferedReader(new InputStreamReader(WikiLink.openStream()));
        String UrlTextRetrieved;
        StringBuilder CompleteRetrieved = new StringBuilder();
        while ((UrlTextRetrieved = InStream.readLine()) != null)
            CompleteRetrieved.append(UrlTextRetrieved);
        InStream.close();
        String[] Links = GetLinks(CompleteRetrieved.toString());
        String FileDestination = ".\\LinkFile.txt";
        FileWriter WikiLinksTile = new FileWriter(FileDestination);
        for (String s : Links)
            WikiLinksTile.write(s + "\n");
        WikiLinksTile.close();
    }

    private static String[] GetLinks(String WebsiteData) {
        JSONObject CompleteJson = new JSONObject(WebsiteData);
        JSONObject ToGetPageId = CompleteJson.getJSONObject("query").getJSONObject("pages");
        Iterator<?> GetPid = ToGetPageId.keys();
        String WholeText = ToGetPageId.getJSONObject(GetPid.next().toString()).getJSONArray("revisions").getJSONObject(0).getString("*");
        String SeeAlsoText = WholeText.substring(WholeText.indexOf("==See also==") + 12, WholeText.indexOf("==References=="));
        String[] SeeAlsoLinks = SeeAlsoText.split("\n");
        for (String s : SeeAlsoLinks) {
            s = s.replace("[[", "");
            s = s.replace("]]", "");
            s = s.replace("*", "");
            s = s.replace(" ", "_");
            s = "https://< Root Web Link >\\" + s;
            System.out.println(s);
        }
        return SeeAlsoLinks;
    }
}

public class Main {
    public static void main(String[] args) {
        String urlString = "https://en.wikipedia.org/w/api.php?format=json&action=query&titles=SMALL&prop=revisions&rvprop=content";
        try {
            ParseWeb.GetAllInformationFromWebsiteAndConvertToLinkAndWriteToFile(urlString);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}


