package com.faber.bussiness;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author Nguyen Hung Hau
 */
public class RankingUrl {

    public String getRanking(String keyword, String url) throws UnsupportedEncodingException {

        Document document = null;
        String keywordEndcode =  keyword.replaceAll(" ", "+");
                
        try {

            int startIndex = 0;

            while (startIndex < 50) {
                String googleUrl = "https://www.google.com/search?q=" + keywordEndcode + "&start=" + startIndex;
                document = Jsoup.connect(googleUrl)
                        .userAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:25.0) Gecko/20100101 Firefox/25.0")
                        .referrer("http://www.google.com")
                        .get();

                //Get ranking of url
                String urlEncode = URLEncoder.encode(url, "UTF-8");
                Elements elements = document.select("a[onmousedown]");
                for (Element element : elements) {
                    String href = element.attr("href");
                    if (href.equals(url) || href.contains(urlEncode)) {
                        return element.attr("onmousedown").split("'")[7];
                    } 
                }
                startIndex += 10;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return "50+";
    }
}
