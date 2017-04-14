package ru.votrin.doctordata.model;

import java.sql.Date;

/**
 * Created by wiseman on 15.04.17.
 */
public class PatientDiagnos {

    private Long ptnt_ptnt_id;
    private Date incoming_date;
    private Date outcoming_date;
    private String diagnos;
    private Date operation_date;
    private String localisation;
    private Integer hist_num;

    public PatientDiagnos(Long ptnt_ptnt_id,
                          Date incoming_date,
                          Date outcoming_date,
                          String diagnos,
                          Date operation_date,
                          String localisation,
                          Integer hist_num) {
        this.ptnt_ptnt_id = ptnt_ptnt_id;
        this.incoming_date = incoming_date;
        this.outcoming_date = outcoming_date;
        this.diagnos = diagnos;
        this.operation_date = operation_date;
        this.localisation = localisation;
        this.hist_num = hist_num;
    }

    public Long getPtnt_ptnt_id() {
        return ptnt_ptnt_id;
    }

    public void setPtnt_ptnt_id(Long ptnt_ptnt_id) {
        this.ptnt_ptnt_id = ptnt_ptnt_id;
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

    public String getLocalisation() {
        return localisation;
    }

    public void setLocalisation(String localisation) {
        this.localisation = localisation;
    }

    public Integer getHist_num() {
        return hist_num;
    }

    public void setHist_num(Integer hist_num) {
        this.hist_num = hist_num;
    }
}
