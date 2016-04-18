package DTO;

/**
 *
 * @author Nguyen Hung Hau
 */
public class SemesterDTO {

    private int id;
    private int courseId;
    private int monthBegin;
    private int monthEnd;
    private String strName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public int getMonthBegin() {
        return monthBegin;
    }

    public void setMonthBegin(int monthBegin) {
        this.monthBegin = monthBegin;
    }

    public int getMonthEnd() {
        return monthEnd;
    }

    public void setMonthEnd(int monthEnd) {
        this.monthEnd = monthEnd;
    }

    public String getStrName() {
        return strName;
    }

    public void setStrName(String strName) {
        this.strName = strName;
    }

}
