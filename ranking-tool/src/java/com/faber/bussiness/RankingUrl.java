package com.faber.bussiness;

import java.io.UnsupportedEncodingException;
import java.net.IDN;
import java.net.URLDecoder;
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

    /**
     * Get ranking of url when search on google
     *
     * @param keyword
     * @param url
     * @param type
     * @return
     * @throws UnsupportedEncodingException
     */
    public String getRanking(String keyword, String url, String type) throws UnsupportedEncodingException {

        Document document;
        String keywordEndcode = URLEncoder.encode(keyword, "UTF-8").replaceAll(" ", "+");

        try {

            String googleUrl = "https://www.google.com/search?q=" + keywordEndcode + "&num=65";
            document = Jsoup.connect(googleUrl)
                    .userAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:25.0) Gecko/20100101 Firefox/25.0")
                    .referrer("http://www.google.com")
                    .get();

            String domain = getUrlDomainName(url);

            Elements elements = document.select("div[class=rc]").select("h3[class=r]").select("a");
            int size = (elements.size() < 50) ? elements.size() : 50;
            for (int i = 0; i < size; i++) {
                Element element = elements.get(i);
                String link = element.attr("href");
                link = URLDecoder.decode(getUrlFromHref(link), "UTF-8");

                if (type.equals("1")) {
                    link = getUrlDomainName(link);
                    String domainEncode = IDN.toASCII(domain);

                    if (link.contains(domain) || link.contains(domainEncode)) {
                        return String.valueOf(++i);
                    }
                } else {
                    String urlEncode = "";

                    try {
                        urlEncode = IDN.toASCII(url);

                    } catch (Exception ex) {
                        System.out.println("error to Ascii");
                    }
                    if (link.equals(url) || link.equals(urlEncode)) {
                        return String.valueOf(++i);
                    }
                }

            }

        } catch (Exception e) {
            System.out.println("error");
        }

        return "50+";
    }

    /**
     * Get domain from a url
     *
     * @param url
     * @return
     */
    private String getUrlDomainName(String url) {

        String domainName = url;

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

        domainName = domainName.replaceFirst("^www.*?\\.", "");

        return domainName;
    }

    /**
     * Get url from attribute href
     *
     * @param url
     * @return
     */
    private String getUrlFromHref(String href) {

        String[] parts = href.split("url=");
        if (parts.length < 2) {
            return href;
        }

        String result = parts[1].split("&usg")[0];
        return result;
    }

}
