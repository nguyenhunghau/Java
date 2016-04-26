package com.faber.bussiness;

import java.io.UnsupportedEncodingException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.net.URLEncoder;

/**
 *
 * @author Nguyen Hung Hau
 */
public class RankingUrl {

    public String getRanking(String keyword, String url) throws UnsupportedEncodingException {

        Document document = null;
        String keywordEndcode = URLEncoder.encode(keyword, "UTF-8").replaceAll(" ", "+");

        try {

            String googleUrl = "https://www.google.com/search?q=" + keywordEndcode + "&num=65";
            document = Jsoup.connect(googleUrl)
                    .userAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:25.0) Gecko/20100101 Firefox/25.0")
                    .referrer("http://www.google.com")
                    .get();

            //Get ranking of url
            String domain = getUrlDomainName(url);
            Elements elements = document.select("cite[class*=_Rm]");
            int size = (elements.size() < 50) ? elements.size() : 50;
            for (int i = 0; i < size; i++) {
                Element element = elements.get(i);
                String link = getUrlDomainName(element.text());
                if (link.contains(domain)) {
                    return String.valueOf(++i);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return "50+";
    }

    private String getUrlDomainName(String url) {

        String domainName = new String(url);

        int index = domainName.indexOf("://");

        if (index != -1) {
            // keep everything after the "://"
            domainName = domainName.substring(index + 3);
        }

        index = domainName.indexOf('/');

        if (index != -1) {
            // keep everything before the '/'
            domainName = domainName.substring(0, index);
        }

        // check for and remove a preceding 'www'
        // followed by any sequence of characters (non-greedy)
        // followed by a '.'
        // from the beginning of the string
        domainName = domainName.replaceFirst("^www.*?\\.", "");

        return domainName;
    }

}
