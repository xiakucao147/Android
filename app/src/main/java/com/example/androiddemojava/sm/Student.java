package com.example.androiddemojava.sm;

import kotlin.Metadata;

public class Student {
    private Integer no;
    private String name;
    private String age;
    private String major;
    private String sex;
    private String enrollment_date;
    private String faculty;
    private String tel;
    private String head_icon;
    private String password;
    private String student_num;


    public Student(String name, String age, String major, String sex, String enrollment_date, String faculty, String tel, String head_icon, String password, String student_num) {
        this.name   = name;
        this.age    = age;
        this.major  = major;
        this.sex    = sex;
        this.enrollment_date = enrollment_date;
        this.faculty = faculty;
        this.tel    = tel;
        this.head_icon = head_icon;
        this.password = password;
        this.student_num = student_num;
    }

    public Student() {
    }



    @Override
    public String toString() {
        return "Student{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", age='" + age + '\'' +
                ", major='" + major + '\'' +
                ", sex='" + sex + '\'' +
                ", enrollment_date='" + enrollment_date + '\'' +
                ", faculty='" + faculty + '\'' +
                ", tel='" + tel + '\'' +
                ", head_icon='" + head_icon + '\'' +
                ", password='" + password + '\'' +
                ", student_num='" + student_num + '\'' +
                '}';
    }

    public Integer getNo() {
        return no;
    }

    public void setNo(Integer no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getEnrollment_date() {
        return enrollment_date;
    }

    public void setEnrollment_date(String enrollment_date) {
        this.enrollment_date = enrollment_date;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getHead_icon() {
        return head_icon;
    }

    public void setHead_icon(String head_icon) {
        this.head_icon = head_icon;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStudent_num() {
        return student_num;
    }

    public void setStudent_num(String student_num) {
        this.student_num = student_num;
    }


}
