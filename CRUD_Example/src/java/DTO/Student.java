package DTO;

import java.sql.Date;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author root
 */
public class Student {
    private String strId ; 
    private String strName ; 
    private Date birthday ; 
    private String strGender ; 
    private String strAddress; 
    private Date receiveDay ; 
    private int classId;

    /**
     * @return the strId
     */
    public String getId() {
        return strId;
    }

    /**
     * @param strId the strId to set
     */
    public void setId(String strId) {
        this.strId = strId;
    }

    /**
     * @return the strName
     */
    public String getName() {
        return strName;
    }

    /**
     * @param strName the strName to set
     */
    public void setName(String strName) {
        this.strName = strName;
    }

    /**
     * @return the birthday
     */
    public Date getBirthday() {
        return birthday;
    }

    /**
     * @param birthday the birthday to set
     */
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    /**
     * @return the strGender
     */
    public String getGender() {
        return strGender;
    }

    /**
     * @param strGender the strGender to set
     */
    public void setGender(String strGender) {
        this.strGender = strGender;
    }

    /**
     * @return the strAddress
     */
    public String getAddress() {
        return strAddress;
    }

    /**
     * @param strAddress the strAddress to set
     */
    public void setAddress(String strAddress) {
        this.strAddress = strAddress;
    }

    /**
     * @return the receiveDay
     */
    public Date getReceiveDay() {
        return receiveDay;
    }

    /**
     * @param receiveDay the receiveDay to set
     */
    public void setReceiveDay(Date receiveDay) {
        this.receiveDay = receiveDay;
    }

    /**
     * @return the classId
     */
    public int getClassId() {
        return classId;
    }

    /**
     * @param classId the classId to set
     */
    public void setClassId(int classId) {
        this.classId = classId;
    }

}
