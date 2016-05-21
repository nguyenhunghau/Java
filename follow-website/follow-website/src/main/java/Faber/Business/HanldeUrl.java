package Faber.Business;

//<editor-fold defaultstate="collapsed" desc="IMPORT">
import Faber.DAO.UrlDAO;
import Faber.DTO.UrlDTO;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.IDN;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import static org.quartz.TriggerKey.triggerKey;
import org.quartz.impl.StdSchedulerFactory;
//</editor-fold>

/**
 *
 * @author Nguyen Hung Hau
 */
public class HanldeUrl {

    UrlDAO urlDao = new UrlDAO();

    public String saveWebsite(String url, String type, String user) throws SchedulerException, InterruptedException {
        String currentTime = getCurrentTime();
        UrlDTO userDto = new UrlDTO();
        Gson gson = new Gson();
        String result = "";
        try {

            String website = "save/" + user + "/" + currentTime;
            String absolute = getClass().getProtectionDomain().getCodeSource().getLocation().toExternalForm();
            absolute = absolute.replace("target/follow-website-1.0-SNAPSHOT/WEB-INF/classes/", "src/main/webapp/");
            absolute = absolute.substring(5, absolute.length());
            //Create directory
            String folder = absolute + website;
            File file = new File(folder);
            file.mkdir();
            file = new File(folder + "/capture");
            file.mkdir();
            if (type.equals("html") || type.equals("both")) {
                getHtml(url, folder, website);
                userDto.setHtml(user + "/" + currentTime + "/" + url);
            }

            if (type.equals("capture") || type.equals("both")) {
                WebDriver driver = new PhantomJSDriver();
                driver.get(url);
                capturePC(driver, url, folder + "/capture/capturepc.jpg");
                captureMobile(driver, url, folder + "/capture/capturemobile.jpg");
                captureTablet(driver, url, folder + "/capture/capturetablet.jpg");
                driver.close();
                userDto.setPc(user + "/" + currentTime + "/capture/capturepc.jpg");
                userDto.setTablet(user + "/" + currentTime + "/capture/capturetablet.jpg");
                userDto.setMobile(user + "/" + currentTime + "/capture/capturemobile.jpg");
            }

            //Save into database
            userDto.setLinkUrl(url);
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date parsed = format.parse(currentTime.split("_")[0]);
            userDto.setTimeSave(new java.sql.Date(parsed.getTime()));
            userDto.setIdUser(Integer.valueOf(user));
            userDto.setFrequent(1);
            if (urlDao.addUrl(userDto)) {
                result = gson.toJson(userDto);
            }
            renameFile(absolute + "save/" + user, absolute + "save/abc");
            Thread.sleep(5000);
            renameFile(absolute + "save/abc", absolute + "save/" + user);
        } catch (ParseException ex) {
            Logger.getLogger(HanldeUrl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    private void getHtml(String url, String folder, String website) {

        Document doc;
        String htmlItem, htmlNew = null, nameFile, destinationFile;

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
                if (link.equals("http://stackoverflow.com/questions/1066453/mysql-group-by-and-order-by?lastactivity")) {
                    String afds = "link";
                }

                htmlNew = htmlItem = element.outerHtml();

                if (element.tagName().equals("link")) {
                    nameFile = link.substring(link.lastIndexOf("/") + 1).toLowerCase();
                    String type = element.attr("type");
                    if (nameFile.lastIndexOf("?") > nameFile.indexOf(".")) {
                        nameFile = nameFile.split("\\?")[0];
                    }
                    destinationFile = folder + "/" + nameFile;
                    if (type.equals("text/css")) {
                        nameFile = copyFileStream(link, destinationFile);
                    }

                    if (nameFile.contains(".")) {
                        htmlNew = htmlItem.replaceFirst(element.attr("href"), website + "/" + nameFile);
                    }
                } else {
                    if (link.equals("")) {
                        continue;
                    }

                    htmlItem = htmlItem.replace("&amp;", "&");
                    htmlNew = htmlItem.replace(element.attr("href"), "showcontent.htm?url=" + website + "/" + link);

                }
                html = html.replace(htmlItem, htmlNew);
            }
            //</editor-fold>

            //<editor-fold defaultstate="collapsed" desc="save file js">
            for (Element element : medias) {
                if (!element.tagName().equals("img")) {
                    htmlItem = element.outerHtml();
                    String src = element.attr("abs:src");
                    nameFile = src.substring(src.lastIndexOf("/") + 1);

                    if (nameFile.lastIndexOf("?") > nameFile.indexOf(".")) {
                        nameFile = nameFile.split("\\?")[0];
                    }

                    destinationFile = folder + "/" + nameFile;

                    if (nameFile.indexOf("jquery") == 0) {
                        nameFile = copyFileStream(src, destinationFile);
                    } else {
                        nameFile = copyFile(src, destinationFile, website);
                    }
                    htmlNew = htmlItem.replace(element.attr("src"), website + "/" + nameFile);
                    html = html.replace(htmlItem, htmlNew);
                }
            }
            //</editor-fold>

            //Change html and save file
            html = html.replace("document.location", "").replace("“", "&#8220");
            String linkSave = folder + "/" + (url.replaceAll("/", "**")).replace("?", "++") + ".html";
            saveFile(html, linkSave);
        } catch (IOException e) {
        }
    }

    public String getListWebsite(String url, String user) {

        try {
            List<UrlDTO> listUrl = urlDao.getListUrl(url, user);
            Gson gson = new Gson();
            String result = gson.toJson(listUrl);
            return result;
        } catch (Exception e) {
            return "Can not get list url";
        }

    }

    public String checkUrl(String url, String user) {
        return urlDao.checkUrl(url, user);
    }

    public String checkUrl(String url, java.sql.Date dateSave, String user) {

        String result = "false";
        Gson gson = new Gson();

        try {
            UrlDTO urlDto = urlDao.checkUrl(url, dateSave, user);
            if (urlDto.getLinkUrl() != null && !urlDto.getLinkUrl().equals("")) {
                result = gson.toJson(urlDto);
            }
        } catch (Exception e) {

        }
        return result;
    }

    public boolean deleteUrl(String url, String time, String user) {

        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date parsed = format.parse(time);
            java.sql.Date date = new java.sql.Date(parsed.getTime());
            return urlDao.deleteUrl(url, date, user);
        } catch (ParseException ex) {
            Logger.getLogger(HanldeUrl.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public void createCronjob(String url, String type, String user) throws InterruptedException {

        Scheduler scheduler;
        try {
            String result = urlDao.checkUrl(url, user);
            if (!result.equals("false")) {
                return;
            }
            scheduler = new StdSchedulerFactory().getScheduler();
            JobDetail job = JobBuilder.newJob(SchedulerJob.class)
                    .withIdentity(url, user).build();
            Trigger trigger = TriggerBuilder.newTrigger().withIdentity(url, user)
                    .startAt(new Date(Calendar.getInstance().getTimeInMillis() + 1000 * 60 * 60 * 24))
                    .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInHours(24)
                            .repeatForever()).build();

            //schedule it
            scheduler.start();
            job.getJobDataMap().put("url", url);
            job.getJobDataMap().put("type", type);
            job.getJobDataMap().put("user", user);
            scheduler.scheduleJob(job, trigger);
        } catch (SchedulerException ex) {
            Logger.getLogger(HanldeUrl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updateCronjob(String url, String time, String user) {

        Scheduler scheduler;
        try {
            //Update database
            urlDao.updateFrequency(url, user, time);
            scheduler = new StdSchedulerFactory().getScheduler();
            Trigger oldTrigger = scheduler.getTrigger(triggerKey(url, user));
            TriggerBuilder tb = oldTrigger.getTriggerBuilder();

            Trigger newTrigger = tb.startAt(new Date(Calendar.getInstance().getTimeInMillis() + 1000 * 60 * 60 * 24)).withSchedule(simpleSchedule()
                    .withIntervalInHours(Integer.parseInt(time) * 24)
                    .repeatForever())
                    .build();
            scheduler.rescheduleJob(oldTrigger.getKey(), newTrigger);
        } catch (SchedulerException ex) {
            Logger.getLogger(HanldeUrl.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void renameFile(String oldFile, String newFile) {

        File file = new File(oldFile);
        File fileNew = new File(newFile);
        file.renameTo(fileNew);
    }

    private void capturePC(WebDriver driver, String url, String destinationFile) {

        try {
            driver.manage().window().setSize(new Dimension(1280, 1024));
            File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile, new File(destinationFile), true);
        } catch (IOException ex) {
            Logger.getLogger(HanldeUrl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void captureMobile(WebDriver driver, String url, String destinationFile) {

        try {
            driver.manage().window().setSize(new Dimension(470, 320));
            File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile, new File(destinationFile), true);
        } catch (IOException ex) {
            Logger.getLogger(HanldeUrl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void captureTablet(WebDriver driver, String url, String destinationFile) {

        try {
            driver.manage().window().setSize(new Dimension(1280, 800));
            File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile, new File(destinationFile), true);
        } catch (IOException ex) {
            Logger.getLogger(HanldeUrl.class.getName()).log(Level.SEVERE, null, ex);
        }
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

        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss.SSS");
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
                    BufferedReader in = new BufferedReader(new InputStreamReader(new URL(fileUrl).openStream(), "UTF-8"))) {
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    inputLine = URLDecoder.decode(inputLine, "utf-8");
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
                    writer.println(URLDecoder.decode(inputLine, "utf8"));
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

    private String copyFileStream(String fileUrl, String destinationFile) {

        String root = destinationFile.substring(0, destinationFile.lastIndexOf("/") + 1);
        String fileName = destinationFile.substring(destinationFile.lastIndexOf("/") + 1);
        String tailName = "";
        InputStream input = null;
        FileOutputStream output = null;

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

            input = url.openStream();
            output = new FileOutputStream(root + fileName + tailName);
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
        return fileName + tailName;
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
        InputStream input = null;
        FileOutputStream output = null;

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

    public String formatUrl(String url) {
        if (url.equals("") || url == null || url.length() > 63) {
            return url;
        }
        String result = IDN.toASCII(url);
        while (result.matches(".*[#/?]$")) {
            result = result.substring(0, result.length() - 1);
        }
        return result;

    }

}
