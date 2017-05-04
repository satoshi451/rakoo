package ru.votrin.doctordata.DAO;

import ru.votrin.doctordata.model.PatientDiagnos;
import ru.votrin.doctordata.model.Patient;

import java.util.List;

/**
 * Created by wiseman on 08.04.17.
 */
public interface PatientDAO {
    List<Patient> findAll();

    List<Patient> findWithFilter(String filterText);

    void createPatient(String fname,
                       String sname,
                       String lname,
                       String birth,
                       String sex,
                       Long loc_id,
                       String diag,
                       String incDate,
                       String oucDate,
                       String operDate);

    List<PatientDiagnos> getDiagnosByPtntId(Long ptnt_id);
}

