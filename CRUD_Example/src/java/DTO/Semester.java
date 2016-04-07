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
public class Semester {
    private int id;
    private int courseId;
    private int monthBegin;
    private int monthEnd;
    private String strName;

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
     * @return the CourseId
     */
    public int getCourseId() {
        return courseId;
    }

    /**
     * @param CourseId the CourseId to set
     */
    public void setCourseId(int CourseId) {
        this.courseId = CourseId;
    }

    /**
     * @return the MonthBegin
     */
    public int getMonthBegin() {
        return monthBegin;
    }

    /**
     * @param MonthBegin the MonthBegin to set
     */
    public void setMonthBegin(int MonthBegin) {
        this.monthBegin = MonthBegin;
    }

    /**
     * @return the MonthEnd
     */
    public int getMonthEnd() {
        return monthEnd;
    }

    /**
     * @param MonthEnd the MonthEnd to set
     */
    public void setMonthEnd(int MonthEnd) {
        this.monthEnd = MonthEnd;
    }

    /**
     * @return the Name
     */
    public String getName() {
        return strName;
    }

    /**
     * @param Name the Name to set
     */
    public void setName(String Name) {
        this.strName = Name;
    }
}
