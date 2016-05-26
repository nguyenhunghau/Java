package Faber.DTO;

import java.sql.Date;

/**
 *
 * @author Nguyen Hung Hau
 */
public class UrlDTO {

    private int id;
    private int idUser;
    private String linkUrl;
    private Date timeSave;
    private int frequent;
    private String pc;
    private String tablet;
    private String mobile;
    private String html;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getLinkUrl() {
        return linkUrl;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }

    public Date getTimeSave() {
        return timeSave;
    }

    public void setTimeSave(Date timeSave) {
        this.timeSave = timeSave;
    }

    public int getFrequent() {
        return frequent;
    }

    public void setFrequent(int frequent) {
        this.frequent = frequent;
    }

    public String getPc() {
        return pc;
    }

    public void setPc(String pc) {
        this.pc = pc;
    }

    public String getTablet() {
        return tablet;
    }

    public void setTablet(String tablet) {
        this.tablet = tablet;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getHtml() {
        return html;
    }

    public void setHtml(String html) {
        this.html = html;
    }
}
