package Faber.Business;

//<editor-fold defaultstate="collapsed" desc="IMPORT">
import Faber.DAO.HandleUrlDAO;
import Faber.DTO.UrlDTO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import static jdk.nashorn.internal.objects.ArrayBufferView.buffer;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
//</editor-fold>

/**
 *
 * @author Nguyen Hung Hau
 */
public class HanldeUrl {

    HandleUrlDAO urlDao = new HandleUrlDAO();

    public String saveWebsite(String url, boolean isIndex) {
        Document doc;
        String htmlItem, htmlNew = null, nameFile, destinationFile;
        String currentTime = getCurrentTime();
        String website = "save/" + currentTime;
        String absolute = getClass().getProtectionDomain().getCodeSource().getLocation().toExternalForm();
        absolute = absolute.replace("build/web/WEB-INF/classes/", "web/");
        absolute = absolute.substring(5, absolute.length());
        //Create directory
        String folder = absolute + website;
        File file = new File(folder);
        file.mkdir();

        try {
            doc = Jsoup.connect(url)
                    .userAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:25.0) Gecko/20100101 Firefox/25.0")
                    .referrer("http://www.google.com")
                    .get();

            Elements links = doc.select("[href]");
            Elements medias = doc.select("[src]");
            Element htmlTag = doc.select("html").first();
            String html = htmlTag.html();

            //<editor-fold defaultstate="collapsed" desc="Save file css and change <a> element">
            for (Element element : links) {

                String link = element.attr("abs:href");
                htmlNew = htmlItem = element.outerHtml();

                if (element.tagName().equals("link")) {
                    nameFile = link.substring(link.lastIndexOf("/") + 1).toLowerCase();
                    String type = element.attr("type");
                    if (nameFile.lastIndexOf("?") > nameFile.indexOf(".")) {
                        nameFile = nameFile.split("\\?")[0];
                    }
                    destinationFile = folder + "/" + nameFile;

                    if (nameFile.endsWith(".ico") || nameFile.endsWith(".png")
                            || nameFile.endsWith(".jpg") || nameFile.endsWith(".jpeg")
                            || nameFile.endsWith(".tiff") || nameFile.endsWith(".gif")) {
                        saveImage(link, destinationFile);
                    } else if (type.equals("text/css")) {
                        nameFile = copyFileCss(link, destinationFile);
                    }

                    if (nameFile.contains(".")) {
                        htmlNew = htmlItem.replace(element.attr("href"), website + "/" + nameFile);
                    }
                } else {
                    if (link.equals("")) {
                        continue;
                    }

                    htmlItem = htmlItem.replace("&amp;", "&");
                    htmlNew = htmlItem.replaceFirst(element.attr("href"), "showcontent.htm?url=" + website + "/" + link);

                }
                html = html.replace(htmlItem, htmlNew);
            }
            //</editor-fold>

            //<editor-fold defaultstate="collapsed" desc="save file img and js">
            for (Element element : medias) {
                htmlItem = element.outerHtml();
                String src = element.attr("abs:src");
                nameFile = src.substring(src.lastIndexOf("/") + 1);

                if (nameFile.lastIndexOf("?") > nameFile.indexOf(".")) {
                    nameFile = nameFile.split("\\?")[0];
                }

                destinationFile = folder + "/" + nameFile;

                if (element.tagName().equals("img")) {
                    saveImage(src, destinationFile);

                } else if (nameFile.indexOf("jquery") == 0) {
                    copyFileStream(src, destinationFile);
                } else {
                    nameFile = copyFile(src, destinationFile, website);
                }
                htmlNew = htmlItem.replace(element.attr("src"), website + "/" + nameFile);
                html = html.replace(htmlItem, htmlNew);
            }
            //</editor-fold>

            //<editor-fold defaultstate="collapsed" desc="change html and save file">
            html = html.replace("“", "&#8220");
            String linkSave = folder + "/" + (url.replaceAll("/", "**")).replace("?", "++") + ".html";
            String linkSite = website + "/" + url;
            saveFile(html, linkSave);
            //Save into database
            String[] partsTime = currentTime.split("_");
            String timeSave = partsTime[0] + " " + partsTime[1].substring(0, 8);
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            Date parsed = format.parse(timeSave);
            Timestamp time = new Timestamp(parsed.getTime());
            urlDao.addUrl(linkSite.substring(5), time);
            //</editor-fold>

            return linkSite;

        } catch (IOException | ParseException e) {
        }
        return "";
    }

    public String getListWebsite(String url) {

        try {
            List<UrlDTO> listUrl = urlDao.getListUrl(url);
            Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
            String result = gson.toJson(listUrl);
            return result;
        } catch (Exception e) {
            return "Can not get list url";
        }

    }

    public String checkUrl(String url) throws IOException {

        String result = "false";

        try {
            String absolute = getClass().getProtectionDomain().getCodeSource().getLocation().toExternalForm();
            absolute = absolute.replace("build/web/WEB-INF/classes/", "web/");
            absolute = absolute.substring(5, absolute.length());
            String head = url.substring(0, 28);
            String tailUrl = url.substring(29);
            result = urlDao.checkUrl(tailUrl);
            if (!result.equals("false")) {
                String tail = tailUrl.replace("/", "**") + ".html";
                String namefolder = absolute + head;
                File forder = new File(namefolder);
                File[] listOfFiles = forder.listFiles();
                Path path = Paths.get(namefolder + "/" + tail);
                Charset charset = StandardCharsets.UTF_8;
                String content = new String(Files.readAllBytes(path), charset);

                for (File file : listOfFiles) {
                    if (file.getName().contains(".js") && !file.getName().contains("_faber.js")) {
                        String wholeFilename = file.getName();
                        String filename = wholeFilename.substring(0, wholeFilename.lastIndexOf(".js"));
                        String oldFile = namefolder + "/" + filename + ".js";
                        String newFile = namefolder + "/" + filename + "_faber.js";
                        renameFile(oldFile, newFile);
                        content = content.replace(filename + ".js", filename + "_faber.js");
                    }
                    if (file.getName().contains(".css") && !file.getName().contains("_faber.css")) {
                        String wholeFilename = file.getName();
                        String filename = wholeFilename.substring(0, wholeFilename.lastIndexOf(".css"));
                        String oldFile = namefolder + "/" + filename + ".css";
                        String newFile = namefolder + "/" + filename + "_faber.css";
                        renameFile(oldFile, newFile);
                        content = content.replace(filename + ".css", filename + "_faber.css");
                    }
                }

                Files.write(path, content.getBytes(charset));
            }
        } catch (Exception e) {

        }

        return result;
    }

    private void renameFile(String oldFile, String newFile) {

        File file = new File(oldFile);
        File fileNew = new File(newFile);
        file.renameTo(fileNew);
    }

    /**
     * If link doesn't begin by http/https, plus domain of url
     *
     * @param url
     * @return
     */
    private String createLink(String url, String attribute) throws IOException {

        String result = "";
        String realAttribute = attribute;
        if (attribute.length() <= 3) {
            return "";
        }
        if (attribute.indexOf("http") == 0) {
            return attribute;
        }
        if (attribute.indexOf("//") == 0) {
            return "http:" + attribute;
        }

        char beginLetter = realAttribute.charAt(0);
        if (beginLetter == '\'' || beginLetter == '\"') {
            realAttribute = realAttribute.substring(1, realAttribute.lastIndexOf(beginLetter));
        }

        result = getWebsite(url) + "/" + realAttribute;
        if (!existUrl(result)) {
            String headUrl = url;
            int counter = realAttribute.split("../").length - 1;
            if (counter > 0) {
                realAttribute = realAttribute.substring(realAttribute.lastIndexOf("../") + 3);
            }
            for (int i = 0; i < counter; i++) {
                headUrl = headUrl.substring(0, headUrl.lastIndexOf("/"));

            }
            result = headUrl + "/" + realAttribute;
        }
        return result;
    }

    /**
     * Get domain from a url
     *
     * @param url
     * @return
     */
    private String getWebsite(String url) {

        String domainName = url;

        int index = domainName.indexOf("://");
        String http = domainName.split("://")[0] + "://";
        if (index != -1) {
            // keep everything after the "://"
            domainName = domainName.substring(index + 3);
        }

        index = domainName.indexOf('/');

        if (index != -1) {
            // keep everything before the '/'
            domainName = domainName.substring(0, index);
        }

        return http + domainName;
    }

    private String getCurrentTime() {

        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss.SSS");//dd/MM/yyyy
        Date now = new Date();
        String strDate = sdfDate.format(now);
        return strDate;
    }

    /**
     * Read content of fileUrl and write into destinationFile
     *
     * @param fileUrl
     * @param destinationFile
     * @param linkWeb
     * @return
     */
    private String copyFile(String fileUrl, String destinationFile, String linkWeb) {

        String root = destinationFile.substring(0, destinationFile.lastIndexOf("/") + 1);
        String fileName = destinationFile.substring(destinationFile.lastIndexOf("/") + 1);
        String tailName = "", newName = "";
        HashMap<String, String> mapString = new HashMap();
        if (fileName.contains(".")) {
            tailName = fileName.substring(fileName.lastIndexOf("."));
            fileName = fileName.substring(0, fileName.lastIndexOf("."));
        }

        try {
            try (PrintWriter writer = new PrintWriter(root + fileName + tailName, "UTF-8");
                    BufferedReader in = new BufferedReader(new InputStreamReader(new URL(fileUrl).openStream(),"UTF-8"))) {
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    inputLine =URLDecoder.decode(inputLine, "utf-8");
                    if (inputLine.contains("href=\"")) {
                        String source = inputLine.substring(inputLine.lastIndexOf("href=\"") + 6);
                        source = source.substring(0, source.indexOf("\""));
                        inputLine = inputLine.replace(source, "showcontent.htm?url=" + linkWeb + source);
                    }

                    if (inputLine.contains("<script") && inputLine.contains("src=\\\"")) {
                        String source = inputLine.substring(inputLine.lastIndexOf("src=\\\"") + 6);
                        source = source.substring(0, source.indexOf("\\\""));
                        String nameFile = source.substring(source.lastIndexOf("/"));
                        nameFile = nameFile.substring(0, nameFile.lastIndexOf(".")) + "_faber.js";
                        String newLink = createLink(fileUrl, source);
                        inputLine = inputLine.replace(source, linkWeb + nameFile);
                        String linkSave = destinationFile.substring(0, destinationFile.lastIndexOf("/") + 1) + nameFile;
                        copyFile(newLink, linkSave, linkWeb);
                        mapString.put(source, nameFile);
                    }
                    writer.println(URLDecoder.decode(inputLine, "utf8") );
                }
                in.close();
                writer.close();

                if (mapString.size() > 0) {
                    String text = new String(Files.readAllBytes(Paths.get(root + fileName + tailName)), StandardCharsets.UTF_8);
                    PrintWriter write = new PrintWriter(root + fileName + tailName, "UTF-8");
                    for (Map.Entry<String, String> entry : mapString.entrySet()) {
                        text = text.replace(entry.getKey(), entry.getValue());
                    }
                    write.println(text);
                    write.close();
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(HanldeUrl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return fileName + tailName;
    }

    private void copyFileStream(String fileUrl, String destinationFile) {

        InputStream input = null;
        FileOutputStream output = null;

        try {
            URL url = new URL(fileUrl);
            input = url.openStream();
            output = new FileOutputStream(destinationFile);
            byte[] buffer = new byte[2048];
            int data = 0;
            while ((data = input.read(buffer)) != -1) {
                output.write(buffer, 0, data);
            }

            input.close();
            output.close();
        } catch (Exception ex) {
            Logger.getLogger(HanldeUrl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Read content of file css fileUrl and write into destinationFile
     *
     * @param fileUrl
     * @param destinationFile
     * @return
     */
    private String copyFileCss(String fileUrl, String destinationFile) {

        String root = destinationFile.substring(0, destinationFile.lastIndexOf("/") + 1);
        String fileName = destinationFile.substring(destinationFile.lastIndexOf("/") + 1);
        String tailName = "";

        if (fileName.contains(".")) {
            tailName = fileName.substring(fileName.lastIndexOf("."));
            fileName = fileName.substring(0, fileName.lastIndexOf("."));
        }
        try {

            URL url = new URL(fileUrl);
            File file = new File(root + fileName + tailName);
            while (file.exists()) {
                fileName += "c";
                file = new File(root + fileName + tailName);
            }

            try (
                    PrintWriter writer = new PrintWriter(root + fileName + tailName, "UTF-8");
                    BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()))) {
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    if (inputLine.contains("url(")) {
                        String urlImage = inputLine.substring(inputLine.lastIndexOf("url(") + 4);
                        urlImage = urlImage.substring(0, urlImage.indexOf(")"));
                        String newLink = createLink(fileUrl, urlImage);
                        String nameImage = urlImage.substring(urlImage.lastIndexOf("/") + 1);
                        if (nameImage.lastIndexOf("?") > nameImage.lastIndexOf(".")) {
                            nameImage = nameImage.split("\\?")[0];
                        }
                        String linkSave = destinationFile.substring(0, destinationFile.lastIndexOf("/"))
                                + "/" + nameImage;
                        inputLine = inputLine.replace(urlImage, nameImage);
                        saveImage(newLink, linkSave);
                    }
                    writer.println(inputLine);
                }
                in.close();
                writer.close();
            }
        } catch (Exception ex) {
            Logger.getLogger(HanldeUrl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return fileName + tailName;
    }

    private void saveImage(String imageUrl, String destinationFile) throws IOException {

        File file = new File(destinationFile);
        while (file.exists()) {
            return;
        }

        URL url = new URL(imageUrl);
        OutputStream os = null;

        try {
            InputStream is = url.openStream();
            os = new FileOutputStream(destinationFile);
            byte[] b = new byte[2048];
            int length;
            while ((length = is.read(b)) != -1) {
                os.write(b, 0, length);
            }
        } catch (Exception e) {
        }
        if (os != null) {
            os.close();
        }
    }

    private void saveFile(String content, String destinationFile) {
        try (PrintWriter writer = new PrintWriter(destinationFile, "UTF-8")) {
            writer.println(content);
        } catch (FileNotFoundException | UnsupportedEncodingException ex) {
            Logger.getLogger(HanldeUrl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Check url exist or not
     *
     * @param url
     * @return
     * @throws IOException
     */
    private boolean existUrl(String url) throws IOException {

        try {
            HttpURLConnection.setFollowRedirects(false);
            HttpURLConnection con
                    = (HttpURLConnection) new URL(url).openConnection();
            con.setRequestMethod("HEAD");
            return (con.getResponseCode() == HttpURLConnection.HTTP_OK);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
