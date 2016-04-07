package DTO;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author root
 */
public class Course {
    private int id;
    private String strYearBegin;
    private String strYearEnd;

    /**
     * @return the Id
     */
    public int getId() {
        return id;
    }

    /**
     * @param Id the Id to set
     */
    public void setId(int Id) {
        this.id = Id;
    }

    /**
     * @return the YearBegin
     */
    public String getYearBegin() {
        return strYearBegin;
    }

    /**
     * @param YearBegin the YearBegin to set
     */
    public void setYearBegin(String strYearBegin) {
        this.strYearBegin = strYearBegin;
    }

    /**
     * @return the YearEnd
     */
    public String getYearEnd() {
        return strYearEnd;
    }

    /**
     * @param YearEnd the YearEnd to set
     */
    public void setYearEnd(String strYearEnd) {
        this.strYearEnd = strYearEnd;
    }
}
