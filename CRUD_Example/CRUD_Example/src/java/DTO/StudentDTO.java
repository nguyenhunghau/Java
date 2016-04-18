package DTO;

//<editor-fold defaultstate="collapsed" desc="import">
import java.sql.Date;
//</editor-fold>

/**
 *
 * @author Nguyen Hung Hau
 */
public class StudentDTO {

    private String strId;
    private String strName;
    private Date birthday;
    private String strGender;
    private String strAddress;
    private Date receiveDay;
    private int classId;

    public String getStrId() {
        return strId;
    }

    public void setStrId(String strId) {
        this.strId = strId;
    }

    public String getStrName() {
        return strName;
    }

    public void setStrName(String strName) {
        this.strName = strName;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getStrGender() {
        return strGender;
    }

    public void setStrGender(String strGender) {
        this.strGender = strGender;
    }

    public String getStrAddress() {
        return strAddress;
    }

    public void setStrAddress(String strAddress) {
        this.strAddress = strAddress;
    }

    public Date getReceiveDay() {
        return receiveDay;
    }

    public void setReceiveDay(Date receiveDay) {
        this.receiveDay = receiveDay;
    }

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

}
