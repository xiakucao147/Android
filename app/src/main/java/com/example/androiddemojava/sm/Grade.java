package com.example.androiddemojava.sm;

public class Grade {
    private Integer no;
    private String stu_no;
    private String cou_no;

    public Integer getNo() {
        return no;
    }

    @Override
    public String toString() {
        return "Grade{" +
                "no=" + no +
                ", stu_no='" + stu_no + '\'' +
                ", cou_no='" + cou_no + '\'' +
                ", grade='" + grade + '\'' +
                '}';
    }

    public Grade(String stu_no, String cou_no, String grade) {
        this.stu_no = stu_no;
        this.cou_no = cou_no;
        this.grade = grade;
    }

    public void setNo(Integer no) {
        this.no = no;
    }

    public String getStu_no() {
        return stu_no;
    }

    public void setStu_no(String stu_no) {
        this.stu_no = stu_no;
    }

    public String getCou_no() {
        return cou_no;
    }

    public void setCou_no(String cou_no) {
        this.cou_no = cou_no;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    private String grade;
}
