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
public class ClassStudy {
    private int id;
    private String strName;
    private int courseId;
    /**
     * @return the Id
     */
    public int getId() {
        return id;
    }

    /**
     * @param Id the Id to set
     */
    public void setId(int id) {
        this.id = id;
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
    public void setName(String name) {
        this.strName = name;
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
    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }
}
