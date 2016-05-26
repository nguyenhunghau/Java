package Faber.Business;

//<editor-fold defaultstate="collapsed" desc="IMPORT">
import Faber.Cronjob.SchedulerJob;
import Faber.DAO.UrlDAO;
import Faber.DTO.UrlDTO;
import com.google.gson.Gson;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.IDN;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletResponse;
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
import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
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

    //<editor-fold defaultstate="collapsed" desc="SAVE A WEBSITE">
    /**
     * Save a website with url, type and user
     *
     * @param url
     * @param type: capture or html or both
     * @param user: id of user
     * @return
     * @throws SchedulerException
     * @throws InterruptedException
     */
    public String saveWebsite(String url, String type, String user) throws SchedulerException, InterruptedException {
        String currentTime = getCurrentTime();
        UrlDTO userDto = new UrlDTO();
        Gson gson = new Gson();
        String result = "";
        try {
            //Create directory
            String data = user + "/" + currentTime;
            String folderSave = "/usr/local/follow-website/" + data;
            File file = new File(folderSave);
            boolean a = file.mkdirs();
            file = new File(folderSave + "/capture");
            file.mkdir();
            if (type.equals("html") || type.equals("both")) {
                String linkSaveHtml = (url.split("://")[1].replaceAll("/", "_"))
                        .replace("?", "+").replace("&", "+") + ".html";

                getHtml(url, folderSave, data, linkSaveHtml);
                userDto.setHtml(user + "/" + currentTime + "/" + linkSaveHtml);
            }

            if (type.equals("capture") || type.equals("both")) {
                WebDriver driver = new PhantomJSDriver();
                driver.get(url);
                capturePC(driver, url, folderSave + "/capture/capturepc.jpg");
                captureMobile(driver, url, folderSave + "/capture/capturemobile.jpg");
                captureTablet(driver, url, folderSave + "/capture/capturetablet.jpg");
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
        } catch (ParseException ex) {
            Logger.getLogger(HanldeUrl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="GET PARAMETER">
    public Map<String, String> getParameter(String queryString) {
        Map<String, String> parameter = new HashMap<String, String>();
        String value = null, name = null;
        int i = 0;
        try {
            String[] pares = queryString.split("&");
            for (String pare : pares) {
                String[] nameAndValue = pare.split("=");
                if (i % 2 == 0) {
                    name = nameAndValue[1];
                } else {
                    value = nameAndValue[1];
                }
                if (value != null && name != null) {
                    parameter.put(name, value);
                    name = null;
                    value = null;
                }
                i++;
            }
        } catch (Exception e) {
        }
        return parameter;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="LOAD FILE">
    public void loadFile(String fileName, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        int i;
        FileInputStream file = null;
        try {
            file = new FileInputStream(fileName);
            while ((i = file.read()) != -1) {
                out.write(i);
            }
        } catch (Exception ex) {
            Logger.getLogger(HanldeUrl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            file.close();
            out.close();
        }
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="GET HTML FROM WEBSITE">
    /**
     * Get all html, css, js of website and save into folder
     *
     * @param url
     * @param folder
     * @param website
     */
    private void getHtml(String url, String folder, String data, String linkSaveHtml) {

        Document doc;
        String htmlItem, nameFile, destinationFile, htmlNew = null;

        try {

            doc = Jsoup.connect(url)
                    .userAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:25.0) Gecko/20100101 Firefox/25.0")
                    .referrer("http://www.google.com")
                    .get();

            Elements links = doc.select("link[href]");
            Elements medias = doc.select("script[src]");
            Element htmlTag = doc.select("html").first();
            String html = htmlTag.html();

            for (Element element : links) {

                String link = element.attr("abs:href");
                htmlNew = htmlItem = element.outerHtml();
                nameFile = link.substring(link.lastIndexOf("/") + 1).toLowerCase();
                if (nameFile.lastIndexOf("?") > nameFile.indexOf(".")) {
                    nameFile = nameFile.split("\\?")[0];
                }
                String type = element.attr("type");
                if (type.equals("text/css") || nameFile.endsWith(".css")) {

                    destinationFile = folder + "/" + nameFile;
                    nameFile = copyFileStream(link, destinationFile);
                    htmlNew = htmlItem.replace(element.attr("href"),
                            "loadFile?url=" + data + "/" + nameFile);
                    html = html.replace(htmlItem, htmlNew);
                }
            }

            for (Element element : medias) {
                htmlItem = element.outerHtml();
                String src = element.attr("abs:src");
                nameFile = src.substring(src.lastIndexOf("/") + 1);

                if (nameFile.lastIndexOf("?") > nameFile.indexOf(".")) {
                    nameFile = nameFile.split("\\?")[0];
                }

                destinationFile = folder + "/" + nameFile;
                nameFile = copyFileStream(src, destinationFile);
                htmlNew = htmlItem.replace(element.attr("src"),
                        "loadFile?url=" + data + "/" + nameFile);
                html = html.replace(htmlItem, htmlNew);
            }
            html = html.replace("”", "&quot;").replace("“", "&quot;");
            saveFile(html, folder + "/" + linkSaveHtml);
        } catch (IOException e) {
        }
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="GET LIST WEBSITE FROM DATABASE">
    /**
     * Get list UrlDTO with url in database
     *
     * @param url
     * @param user
     * @return
     */
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
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="CHECK URL EXIST OR NOT IN DATABASE">
    public String checkUrl(String url, String user) {
        return urlDao.checkUrl(url, user);
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="CHECK URL IS SAVED ON CURRENT DATE">
    /**
     * Check url is saved or not on current date
     *
     * @param url
     * @param dateSave
     * @param user
     * @return
     */
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
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="DELETE URL">
    /**
     * Delete a url in database with time and user
     *
     * @param url
     * @param time
     * @param user
     * @return
     */
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
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="CREATE CRONJOB">
    /**
     * Create a cronjob on server
     *
     * @param url
     * @param type: html or capture or both
     * @param user: id of user
     * @throws InterruptedException
     */
    public void createCronjob(String url, String type, String user) throws InterruptedException {

        Scheduler scheduler;
        try {
            scheduler = new StdSchedulerFactory().getScheduler();
            if (existCronjob(scheduler)) {
                return;
            }
            JobDetail job = JobBuilder.newJob(SchedulerJob.class)
                    .withIdentity("cronjob").build();
            Trigger trigger = TriggerBuilder
                    .newTrigger()
                    .withIdentity("cronjob")
                    .withSchedule(
                            CronScheduleBuilder.cronSchedule("0 0 1-5 * * ?"))
                    .build();

            //schedule it
            scheduler.start();
            scheduler.scheduleJob(job, trigger);
        } catch (SchedulerException ex) {
            Logger.getLogger(HanldeUrl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="CHECK CRONJOB EXIST OR NOT">
    /**
     * Check a cronjob
     *
     * @param scheduler
     */
    private boolean existCronjob(Scheduler scheduler) {

        boolean result = false;
        try {
            Trigger oldTrigger = scheduler.getTrigger(triggerKey("cronjob"));
            if (oldTrigger != null) {
                result = true;
            }
            //TriggerBuilder tb = oldTrigger.getTriggerBuilder();

        } catch (SchedulerException ex) {
            Logger.getLogger(HanldeUrl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="CAPTURE WEBSITE ON PC">
    /**
     * Capture a website with UI on PC
     *
     * @param driver
     * @param url
     * @param destinationFile
     */
    private void capturePC(WebDriver driver, String url, String destinationFile) {

        try {
            driver.manage().window().setSize(new Dimension(1280, 1024));
            File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile, new File(destinationFile), true);
        } catch (IOException ex) {
            Logger.getLogger(HanldeUrl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="CAPTURE WEBSITE ON MOBILE">
    /**
     * Capture a website with UI on Mobile
     *
     * @param driver
     * @param url
     * @param destinationFile
     */
    private void captureMobile(WebDriver driver, String url, String destinationFile) {

        try {
            driver.manage().window().setSize(new Dimension(470, 320));
            File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile, new File(destinationFile), true);
        } catch (IOException ex) {
            Logger.getLogger(HanldeUrl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="CAPTURE WEBSITE ON TABLET">
    /**
     * Capture a website with UI on Tablet
     *
     * @param driver
     * @param url
     * @param destinationFile
     */
    private void captureTablet(WebDriver driver, String url, String destinationFile) {

        try {
            driver.manage().window().setSize(new Dimension(1280, 800));
            File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile, new File(destinationFile), true);
        } catch (IOException ex) {
            Logger.getLogger(HanldeUrl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="GET CURRENT TIME">
    /**
     * Get current time
     *
     * @return
     */
    private String getCurrentTime() {

        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss.SSS");
        Date now = new Date();
        String strDate = sdfDate.format(now);
        return strDate;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="COPY FILE TYPE STREAM FROM URL">
    /**
     * Copy file by stream
     *
     * @param fileUrl
     * @param destinationFile
     * @return
     */
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
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="SAVE HTML INTO FILE IN LOCAL">
    private void saveFile(String content, String destinationFile) {
        try (PrintWriter writer = new PrintWriter(destinationFile, "UTF-8")) {
            writer.println(content);
        } catch (FileNotFoundException | UnsupportedEncodingException ex) {
            Logger.getLogger(HanldeUrl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="FORMAT URL">
    /**
     * Change url with correct format
     *
     * @param url
     * @return
     */
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
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="CHANGE SCHEDULE">
    public void changeSchedule(String url, String user, String freequency) {
        try {
            urlDao.updateFrequency(url, user, freequency);
        } catch (Exception e) {
        }
    }
   //</editor-fold>
}
