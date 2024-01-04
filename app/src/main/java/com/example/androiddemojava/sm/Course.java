package com.example.androiddemojava.sm;

public class Course {
    private String no;
    private String name;

    @Override
    public String toString() {
        return "Course{" +
                "no='" + no + '\'' +
                ", name='" + name + '\'' +
                ", score='" + score + '\'' +
                '}';
    }

    public Course(String name, String score) {
        this.name = name;
        this.score = score;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    private String score;
}
