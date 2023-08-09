package entity;

import java.io.Serializable;

public class Student implements Serializable {
    private static final long serialVersionUID = 2343251L;

    private int sid;
    private String name;
    private String major;
    private String gender;
    private int batch;

    public Student(){
    }
    public Student(int sid, String name, String major, String gender, int batch) {
        this.sid = sid;
        this.name = name;
        this.major = major;
        this.gender = gender;
        this.batch = batch;
    }

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getBatch() {
        return batch;
    }

    public void setBatch(int batch) {
        this.batch = batch;
    }

    @Override
    public String toString() {
        return "Student{" +
                "sid=" + sid +
                ", name='" + name + '\'' +
                ", major='" + major + '\'' +
                ", gender='" + gender + '\'' +
                ", batch=" + batch +
                '}';
    }
}
