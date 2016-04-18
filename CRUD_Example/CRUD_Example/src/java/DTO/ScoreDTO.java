package DTO;

/**
 *
 * @author Nguyen Hung Hau
 */
public class ScoreDTO {

    private int Id;
    private String strStudentId;
    private int semesterId;
    private String strNameSubject;
    private String strNameSemester;
    private int subjectId;
    private float score_1;     //Score with coefficient 1
    private float score_2;     //Score with coefficient 2
    private float score_3;     //Score with coefficient 3
    private float average;

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getStrStudentId() {
        return strStudentId;
    }

    public void setStrStudentId(String strStudentId) {
        this.strStudentId = strStudentId;
    }

    public int getSemesterId() {
        return semesterId;
    }

    public void setSemesterId(int semesterId) {
        this.semesterId = semesterId;
    }

    public String getStrNameSubject() {
        return strNameSubject;
    }

    public void setStrNameSubject(String strNameSubject) {
        this.strNameSubject = strNameSubject;
    }

    public String getStrNameSemester() {
        return strNameSemester;
    }

    public void setStrNameSemester(String strNameSemester) {
        this.strNameSemester = strNameSemester;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public float getScrore_1() {
        return score_1;
    }

    public void setScrore_1(float scrore_1) {
        this.score_1 = scrore_1;
    }

    public float getScrore_2() {
        return score_2;
    }

    public void setScrore_2(float scrore_2) {
        this.score_2 = scrore_2;
    }

    public float getScrore_3() {
        return score_3;
    }

    public void setScrore_3(float scrore_3) {
        this.score_3 = scrore_3;
    }

    public float getAverage() {
        return average;
    }

    public void setAverage(float average) {
        this.average = average;
    }

}
