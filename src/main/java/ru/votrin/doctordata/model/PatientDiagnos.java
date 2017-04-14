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
}
