package Faber.DTO;

import java.sql.Date;
import java.sql.Timestamp;

/**
 *
 * @author Nguyen Hung Hau
 */
public class UrlDTO {

    private int id;
    private String linkUrl;
    private Timestamp timeSave;

    public Timestamp getTimeSave() {
        return timeSave;
    }

    public void setTimeSave(Timestamp timeSave) {
        this.timeSave = timeSave;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLinkUrl() {
        return linkUrl;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }

}
