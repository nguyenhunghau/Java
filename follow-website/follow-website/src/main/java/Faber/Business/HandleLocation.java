package Faber.Business;

//<editor-fold defaultstate="collapsed" desc="IMPORT">
import Faber.DAO.LocationDAO;
import com.google.gson.Gson;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
//</editor-fold>

/**
 *
 * @author Nguyen Hung Hau
 */
public class HandleLocation {

    //<editor-fold defaultstate="collapsed" desc="GET CANONICAL NAME">
    /**
     * get canonical name
     * @return 
     */
    public String getCanonicalName() {
        LocationDAO locationDao = new LocationDAO();
        return new Gson().toJson(locationDao.getListLocation());
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="CAPTURE GOOGLE SEARCH BY LOCATION">
    /**
     * get special key
     * @param location
     * @return 
     */
    private Character getSpecialKey(String location) {
        Character[] arrLetters = new Character[90];
        int index = 0;
        for (char c = 'A'; c <= 'Z'; c++) {
            arrLetters[index++] = c;
        }

        for (char c = 'a'; c <= 'z'; c++) {
            arrLetters[index++] = c;
        }

        for (char c = '0'; c <= '9'; c++) {
            arrLetters[index++] = c;
        }
        arrLetters[index++] = '-';
        arrLetters[index++] = ' ';

        for (char c = 'A'; c <= 'Y'; c++) {
            arrLetters[index++] = c;
        }

        arrLetters[index++] = 'L';

        Integer len = location.length();
        return arrLetters[len];
    }

    /**
     * encode base64
     * @param input
     * @return 
     */
    private String encodeBase64(String input) {
        byte[] encodedBytes = Base64.encodeBase64(input.getBytes());
        return new String(encodedBytes);
    }

    /**
     * get Random string
     * @param count
     * @return 
     */
    private String getRandomString(Integer count) {
        final String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        Random rnd = new Random();
        StringBuilder buf = new StringBuilder();
        for (int i = 0; i < count; i++) {
            int val = rnd.nextInt(chars.length());
            buf.append(chars.charAt(val));
        }
        return buf.toString();
    }

    /**
     * capture google search
     * @param keyword
     * @param location
     * @param usename
     * @return
     * @throws UnsupportedEncodingException 
     */
    public String captureGoogleSearch(String keyword, String location, String usename) throws UnsupportedEncodingException {
        try {
            String uule = "w+CAIQICI" + getSpecialKey(location) + encodeBase64(location);
            String urlSearch = "https://www.google.co.jp/search?q=" + URLEncoder.encode(keyword, "utf-8") + "&uule=" + uule + "&num=50";
            //Create webdriver to capture
            WebDriver driver = new PhantomJSDriver();
            driver.get(urlSearch);
            driver.manage().window().setSize(new Dimension(1280, 1024));
            String fileName = getRandomString(8) + ".jpg";
            String destinationFile = "/usr/local/follow-website/" + usename + "/google-search/" + fileName;
            File file = new File(destinationFile).getParentFile();
            if(!file.exists())
                file.mkdirs();
            File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile, new File(destinationFile), true);
            
            return fileName;
        } catch (IOException ex) {
            Logger.getLogger(HandleLocation.class.getName()).log(Level.SEVERE, null, ex);
            return "error";
        }
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="LOAD FILE">
    /**
     * load file
     * @param fileName
     * @param response
     * @throws IOException 
     */
    public void showCapture(String fileName, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        int i;
        try (FileInputStream file = new FileInputStream(fileName)) {
            while ((i = file.read()) != -1) {
                out.write(i);
            }
        } catch (Exception ex) {
            Logger.getLogger(HanldeUrl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            out.close();
        }
    }
    //</editor-fold>
}
