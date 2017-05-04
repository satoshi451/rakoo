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
 incoming_date date,
 outcoming_date date,
 diagnos text,
 operation_date date,
 hist_num integer,
 loc_loc_id integer,

 */
public class Patient {

    private Long ptnt_id;
    private String first_name;
    private String second_name;
    private String patronic;
    private Date birth;
    private String sex;
    private Date incoming_date;
    private Date outcoming_date;
    private String diagnos;
    private Date operation_date;
    private Integer hist_num;
    private Long loc_loc_id;

    @Override
    public String toString() {
        return "Patient{" +
                "ptnt_id=" + ptnt_id +
                ", first_name='" + first_name + '\'' +
                ", second_name='" + second_name + '\'' +
                ", patronic='" + patronic + '\'' +
                ", birth=" + birth +
                ", sex='" + sex + '\'' +
                ", incoming_date=" + incoming_date +
                ", outcoming_date=" + outcoming_date +
                ", diagnos='" + diagnos + '\'' +
                ", operation_date=" + operation_date +
                ", hist_num=" + hist_num +
                ", loc_loc_id=" + loc_loc_id +
                '}';
    }

    public Patient(Long ptnt_id,
                   String first_name,
                   String second_name,
                   String patronic,
                   Date birth,
                   String sex,
                   Date incoming_date,
                   Date outcoming_date,
                   String diagnos,
                   Date operation_date,
                   Integer hist_num,
                   Long loc_loc_id) {
        this.ptnt_id = ptnt_id;
        this.first_name = first_name;
        this.second_name = second_name;
        this.patronic = patronic;
        this.birth = birth;
        this.sex = sex;
        this.incoming_date = incoming_date;
        this.outcoming_date = outcoming_date;
        this.diagnos = diagnos;
        this.operation_date = operation_date;
        this.hist_num = hist_num;
        this.loc_loc_id = loc_loc_id;
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

    public Date getIncoming_date() {
        return incoming_date;
    }

    public void setIncoming_date(Date incoming_date) {
        this.incoming_date = incoming_date;
    }

    public Date getOutcoming_date() {
        return outcoming_date;
    }

    public void setOutcoming_date(Date outcoming_date) {
        this.outcoming_date = outcoming_date;
    }

    public String getDiagnos() {
        return diagnos;
    }

    public void setDiagnos(String diagnos) {
        this.diagnos = diagnos;
    }

    public Date getOperation_date() {
        return operation_date;
    }

    public void setOperation_date(Date operation_date) {
        this.operation_date = operation_date;
    }

    public Integer getHist_num() {
        return hist_num;
    }

    public void setHist_num(Integer hist_num) {
        this.hist_num = hist_num;
    }

    public Long getLoc_loc_id() {
        return loc_loc_id;
    }

    public void setLoc_loc_id(Long loc_loc_id) {
        this.loc_loc_id = loc_loc_id;
    }
}
