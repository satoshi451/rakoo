package ru.votrin.doctordata.model;

import java.sql.Date;

/**
 * Created by wiseman on 08.04.17.

 ptnt_id serial NOT NULL,
 first_name text NOT NULL,
 second_name text NOT NULL,
 patronic text NOT NULL,
 birth date,
 sex text,
 */
public class Patient {

    private Long ptnt_id;
    private String first_name;
    private String second_name;
    private String patronic;
    private Date birth;
    private String sex;

    public Patient(Long ptnt_id,
                   String first_name,
                   String second_name,
                   String patronic,
                   Date birth,
                   String sex) {
        this.ptnt_id = ptnt_id;
        this.first_name = first_name;
        this.second_name = second_name;
        this.patronic = patronic;
        this.birth = birth;
        this.sex = sex;
    }

    public Long getPtnt_id() {
        return ptnt_id;
    }

    public void setPtnt_id(Long ptnt_id) {
        this.ptnt_id = ptnt_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getSecond_name() {
        return second_name;
    }

    public void setSecond_name(String second_name) {
        this.second_name = second_name;
    }

    public String getPatronic() {
        return patronic;
    }

    public void setPatronic(String patronic) {
        this.patronic = patronic;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
