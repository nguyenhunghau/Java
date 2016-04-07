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
public class Score {
    private int Id;
    private String strStudentId;
    private int semesterId;
    private String strNameSubject;
    private String strNameSemester;
    private int subjectId;
    private float scrore_1;     //Score with coefficient 1
    private float scrore_2;     //Score with coefficient 2
    private float scrore_3;     //Score with coefficient 3
    private float average;
    /**
     * @return the StudentId
     */
    public String getStudentId() {
        return strStudentId;
    }

    /**
     * @param StudentId the StudentId to set
     */
    public void setStudentId(String StudentId) {
        this.strStudentId = StudentId;
    }

    /**
     * @return the SemesterId
     */
    public int getSemesterId() {
        return semesterId;
    }

    /**
     * @param SemesterId the SemesterId to set
     */
    public void setSemesterId(int SemesterId) {
        this.semesterId = SemesterId;
    }

    /**
     * @return the SubjectId
     */
    public int getSubjectId() {
        return subjectId;
    }

    /**
     * @param SubjectId the SubjectId to set
     */
    public void setSubjectId(int SubjectId) {
        this.subjectId = SubjectId;
    }

    /**
     * @return the Scrore_1
     */
    public float getScrore_1() {
        return scrore_1;
    }

    /**
     * @param Scrore_1 the Scrore_1 to set
     */
    public void setScrore_1(float Scrore_1) {
        this.scrore_1 = Scrore_1;
    }

    /**
     * @return the Scrore_2
     */
    public float getScrore_2() {
        return scrore_2;
    }

    /**
     * @param Scrore_2 the Scrore_2 to set
     */
    public void setScrore_2(float Scrore_2) {
        this.scrore_2 = Scrore_2;
    }

    /**
     * @return the Scrore_3
     */
    public float getScrore_3() {
        return scrore_3;
    }

    /**
     * @param Scrore_3 the Scrore_3 to set
     */
    public void setScrore_3(float Scrore_3) {
        this.scrore_3 = Scrore_3;
    }

    /**
     * @return the NameSemester
     */
    public String getNameSubject() {
        return strNameSubject;
    }

    /**
     * @param NameSemester the NameSemester to set
     */
    public void setNameSubject(String NameSubject) {
        this.strNameSubject = NameSubject;
    }

    /**
     * @return the average
     */
    public float getAverage() {
        return average;
    }

    /**
     * @param average the average to set
     */
    public void setAverage(float average) {
        this.average = average;
    }

    /**
     * @return the strNameSemester
     */
    public String getStrNameSemester() {
        return strNameSemester;
    }

    /**
     * @param strNameSemester the strNameSemester to set
     */
    public void setStrNameSemester(String strNameSemester) {
        this.strNameSemester = strNameSemester;
    }

    /**
     * @return the Id
     */
    public int getId() {
        return Id;
    }

    /**
     * @param Id the Id to set
     */
    public void setId(int Id) {
        this.Id = Id;
    }
}
